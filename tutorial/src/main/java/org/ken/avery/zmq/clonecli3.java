package org.ken.avery.zmq;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMQ.Socket;

/**
 * Clone client Model Three
 * 
 * @author Danish Shrestha <dshrestha06@gmail.com>
 *
 */
public class clonecli3
{
    private static Map<String, kvsimple> kvMap = new HashMap<String, kvsimple>();

    public void run()
    {
        final ZContext ctx = new ZContext();
        final Socket snapshot = ctx.createSocket(ZMQ.DEALER);
        snapshot.connect("tcp://localhost:5556");

        final Socket subscriber = ctx.createSocket(ZMQ.SUB);
        subscriber.connect("tcp://localhost:5557");
        subscriber.subscribe("".getBytes());

        final Socket push = ctx.createSocket(ZMQ.PUSH);
        push.connect("tcp://localhost:5558");

        // get state snapshot
        long sequence = 0;
        snapshot.send("ICANHAZ?".getBytes(), 0);
        while (true)
        {
            final kvsimple kvMsg = kvsimple.recv(snapshot);
            if (kvMsg == null)
            {
                break; //  Interrupted
            }

            sequence = kvMsg.getSequence();
            if ("KTHXBAI".equalsIgnoreCase(kvMsg.getKey()))
            {
                System.out.println("Received snapshot = " + kvMsg.getSequence());
                break; // done
            }

            System.out.println("receiving " + kvMsg.getSequence());
            clonecli3.kvMap.put(kvMsg.getKey(), kvMsg);
        }

        final Poller poller = new ZMQ.Poller(1);
        poller.register(subscriber);

        final Random random = new Random();

        // now apply pending updates, discard out-of-sequence messages
        long alarm = System.currentTimeMillis() + 5000;
        while (true)
        {
            final int rc = poller.poll(Math.max(0, alarm - System.currentTimeMillis()));
            if (rc == -1)
            {
                break; //  Context has been shut down
            }

            if (poller.pollin(0))
            {
                final kvsimple kvMsg = kvsimple.recv(subscriber);
                if (kvMsg == null)
                {
                    break; //  Interrupted
                }
                if (kvMsg.getSequence() > sequence)
                {
                    sequence = kvMsg.getSequence();
                    System.out.println("receiving " + sequence);
                    clonecli3.kvMap.put(kvMsg.getKey(), kvMsg);
                }
            }

            if (System.currentTimeMillis() >= alarm)
            {
                final int key = random.nextInt(10000);
                final int body = random.nextInt(1000000);

                final ByteBuffer b = ByteBuffer.allocate(4);
                b.asIntBuffer().put(body);

                final kvsimple kvUpdateMsg = new kvsimple(key + "", 0, b.array());
                kvUpdateMsg.send(push);
                alarm = System.currentTimeMillis() + 1000;
            }
        }
        ctx.destroy();
    }

    public static void main(final String[] args)
    {
        new clonecli3().run();
    }
}
