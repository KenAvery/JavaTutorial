package org.ken.avery.zmq;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZThread;
import org.zeromq.ZThread.IAttachedRunnable;

/**
 * Clone server Model Two
 *
 * @author Danish Shrestha <dshrestha06@gmail.com>
 *
 */
public class clonesrv2
{

    public void run()
    {
        final ZContext ctx = new ZContext();
        final Socket publisher = ctx.createSocket(ZMQ.PUB);
        publisher.bind("tcp://*:5557");

        final Socket updates = ZThread.fork(ctx, new StateManager());

        final Random random = new Random();
        long sequence = 0;
        while (!Thread.currentThread().isInterrupted())
        {
            final long currentSequenceNumber = ++sequence;
            final int key = random.nextInt(10000);
            final int body = random.nextInt(1000000);

            final ByteBuffer b = ByteBuffer.allocate(4);
            b.asIntBuffer().put(body);

            final kvsimple kvMsg = new kvsimple(key + "", currentSequenceNumber, b.array());
            kvMsg.send(publisher);
            kvMsg.send(updates); // send a message to State Manager thead.

            try
            {
                Thread.sleep(1000);
            }
            catch (final InterruptedException e)
            {
            }

        }
        System.out.printf(" Interrupted\n%d messages out\n", sequence);

        ctx.destroy();
    }

    public static class StateManager implements IAttachedRunnable
    {
        private static Map<String, kvsimple> kvMap = new LinkedHashMap<String, kvsimple>();

        @Override
        public void run(final Object[] args, final ZContext ctx, final Socket pipe)
        {
            pipe.send("READY"); // optional

            final Socket snapshot = ctx.createSocket(ZMQ.ROUTER);
            snapshot.bind("tcp://*:5556");

            final Poller poller = new ZMQ.Poller(2);
            poller.register(pipe, ZMQ.Poller.POLLIN);
            poller.register(snapshot, ZMQ.Poller.POLLIN);

            long stateSequence = 0;
            while (!Thread.currentThread().isInterrupted())
            {
                if (poller.poll() < 0)
                {
                    break; //  Context has been shut down
                }

                // apply state updates from main thread
                if (poller.pollin(0))
                {
                    final kvsimple kvMsg = kvsimple.recv(pipe);
                    if (kvMsg == null)
                    {
                        break;
                    }
                    StateManager.kvMap.put(kvMsg.getKey(), kvMsg);
                    stateSequence = kvMsg.getSequence();
                }

                // execute state snapshot request
                if (poller.pollin(1))
                {
                    final byte[] identity = snapshot.recv(0);
                    if (identity == null)
                    {
                        break;
                    }
                    final String request = new String(snapshot.recv(0));

                    if (!request.equals("ICANHAZ?"))
                    {
                        System.out.println("E: bad request, aborting");
                        break;
                    }

                    final Iterator<Entry<String, kvsimple>> iter = kvMap.entrySet().iterator();
                    while (iter.hasNext())
                    {
                        final Entry<String, kvsimple> entry = iter.next();
                        final kvsimple msg = entry.getValue();
                        System.out.println("Sending message " + entry.getValue().getSequence());
                        this.sendMessage(msg, identity, snapshot);
                    }

                    // now send end message with sequence number
                    System.out.println("Sending state snapshot = " + stateSequence);
                    snapshot.send(identity, ZMQ.SNDMORE);
                    final kvsimple message = new kvsimple("KTHXBAI", stateSequence, "".getBytes());
                    message.send(snapshot);
                }
            }
        }

        private void sendMessage(final kvsimple msg, final byte[] identity, final Socket snapshot)
        {
            snapshot.send(identity, ZMQ.SNDMORE);
            msg.send(snapshot);
        }
    }

    public static void main(final String[] args)
    {
        new clonesrv2().run();
    }
}