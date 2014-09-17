package org.ken.avery.zmq;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMQ.Socket;

/**
 * Clone server Model Four
 */
public class clonesrv4
{
    private static Map<String, kvsimple> kvMap = new LinkedHashMap<String, kvsimple>();

    public void run()
    {

        final ZContext ctx = new ZContext();

        final Socket snapshot = ctx.createSocket(ZMQ.ROUTER);
        snapshot.bind("tcp://*:5556");

        final Socket publisher = ctx.createSocket(ZMQ.PUB);
        publisher.bind("tcp://*:5557");

        final Socket collector = ctx.createSocket(ZMQ.PULL);
        collector.bind("tcp://*:5558");

        final Poller poller = new Poller(2);
        poller.register(collector, Poller.POLLIN);
        poller.register(snapshot, Poller.POLLIN);

        long sequence = 0;
        while (!Thread.currentThread().isInterrupted())
        {
            if (poller.poll(1000) < 0)
            {
                break; //  Context has been shut down
            }

            // apply state updates from main thread
            if (poller.pollin(0))
            {
                final kvsimple kvMsg = kvsimple.recv(collector);
                if (kvMsg == null)
                {
                    break;
                }
                kvMsg.setSequence(++sequence);
                kvMsg.send(publisher);
                clonesrv4.kvMap.put(kvMsg.getKey(), kvMsg);
                System.out.printf("I: publishing update %5d\n", sequence);
            }

            // execute state snapshot request
            if (poller.pollin(1))
            {
                final byte[] identity = snapshot.recv(0);
                if (identity == null)
                {
                    break; //  Interrupted
                }

                //  .until
                //  Request is in second frame of message
                final String request = snapshot.recvStr();

                if (!request.equals("ICANHAZ?"))
                {
                    System.out.println("E: bad request, aborting");
                    break;
                }

                final String subtree = snapshot.recvStr();

                final Iterator<Entry<String, kvsimple>> iter = kvMap.entrySet().iterator();
                while (iter.hasNext())
                {
                    final Entry<String, kvsimple> entry = iter.next();
                    final kvsimple msg = entry.getValue();
                    System.out.println("Sending message " + entry.getValue().getSequence());
                    this.sendMessage(msg, identity, subtree, snapshot);
                }

                // now send end message with sequence number
                System.out.println("Sending state snapshot = " + sequence);
                snapshot.send(identity, ZMQ.SNDMORE);
                final kvsimple message = new kvsimple("KTHXBAI", sequence, "".getBytes());
                message.send(snapshot);
            }
        }
        System.out.printf(" Interrupted\n%d messages handled\n", sequence);
        ctx.destroy();
    }

    private void sendMessage(final kvsimple msg, final byte[] identity, final String subtree, final Socket snapshot)
    {
        snapshot.send(identity, ZMQ.SNDMORE);
        snapshot.send(subtree, ZMQ.SNDMORE);
        msg.send(snapshot);
    }

    public static void main(final String[] args)
    {
        new clonesrv4().run();
    }
}
