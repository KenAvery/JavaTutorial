package org.ken.avery.zmq;

import org.zeromq.ZMsg;

/**
 * Majordomo Protocol worker example. Uses the mdwrk API to hide all MDP aspects
 *
 */
public class mdworker
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final boolean verbose = (args.length > 0 && "-v".equals(args[0]));
        final mdwrkapi workerSession = new mdwrkapi("tcp://localhost:5555", "echo", verbose);

        ZMsg reply = null;
        while (!Thread.currentThread().isInterrupted())
        {
            final ZMsg request = workerSession.receive(reply);
            if (request == null)
            {
                break; //Interrupted
            }
            reply = request; //  Echo is complex :-)
        }
        workerSession.destroy();
    }
}
