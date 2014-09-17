package org.ken.avery.zmq;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * Synchronized subscriber.
 */
public class syncsub
{

    public static void main(final String[] args)
    {
        final Context context = ZMQ.context(1);

        //  First, connect our subscriber socket
        final Socket subscriber = context.socket(ZMQ.SUB);
        subscriber.connect("tcp://localhost:5561");
        subscriber.subscribe("".getBytes());

        //  Second, synchronize with publisher
        final Socket syncclient = context.socket(ZMQ.REQ);
        syncclient.connect("tcp://localhost:5562");

        //  - send a synchronization request
        syncclient.send("".getBytes(), 0);

        //  - wait for synchronization reply
        syncclient.recv(0);

        //  Third, get our updates and report how many we got
        int update_nbr = 0;
        while (true)
        {
            final String string = subscriber.recvStr(0);
            if (string.equals("END"))
            {
                break;
            }
            update_nbr++;
        }
        System.out.println("Received " + update_nbr + " updates.");

        subscriber.close();
        syncclient.close();
        context.term();
    }
}
