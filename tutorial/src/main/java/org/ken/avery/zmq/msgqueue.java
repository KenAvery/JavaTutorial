package org.ken.avery.zmq;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * Simple message queuing broker
 * Same as request-reply broker but using QUEUE device.
 */
public class msgqueue
{

    public static void main(final String[] args)
    {
        //  Prepare our context and sockets
        final Context context = ZMQ.context(1);

        //  Socket facing clients
        final Socket frontend = context.socket(ZMQ.ROUTER);
        frontend.bind("tcp://*:5559");

        //  Socket facing services
        final Socket backend = context.socket(ZMQ.DEALER);
        backend.bind("tcp://*:5560");

        //  Start the proxy
        ZMQ.proxy(frontend, backend, null);

        //  We never get here but clean up anyhow
        frontend.close();
        backend.close();
        context.term();
    }
}
