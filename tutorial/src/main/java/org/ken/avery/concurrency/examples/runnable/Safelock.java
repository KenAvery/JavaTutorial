package org.ken.avery.concurrency.examples.runnable;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Safelock
{
    static class Friend
    {
        private final String name;
        private final Lock lock = new ReentrantLock();

        public Friend(final String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }

        public boolean impendingBow(final Friend bower)
        {
            Boolean myLock = false;
            Boolean yourLock = false;
            try
            {
                myLock = lock.tryLock();
                yourLock = bower.lock.tryLock();
            }
            finally
            {
                if (!(myLock && yourLock))
                {
                    if (myLock)
                    {
                        lock.unlock();
                    }

                    if (yourLock)
                    {
                        bower.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }

        public void bow(final Friend bower)
        {
            if (impendingBow(bower))
            {
                try
                {
                    System.out.format("%s: %s has"
                            + " bowed to me!%n",
                            this.name, bower.getName());
                    bower.bowBack(this);
                }
                finally
                {
                    lock.unlock();
                    bower.lock.unlock();
                }
            }
            else
            {
                System.out.format("%s: %s started"
                        + " to bow to me, but saw that"
                        + " I was already bowing to"
                        + " him.%n",
                        this.name, bower.getName());
            }
        }

        public void bowBack(final Friend bower)
        {
            System.out.format("%s: %s has" +
                    " bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    static class BowLoop implements Runnable
    {
        private final Friend bower;
        private final Friend bowee;

        public BowLoop(final Friend bower, final Friend bowee)
        {
            this.bower = bower;
            this.bowee = bowee;
        }

        @Override
        public void run()
        {
            final Random random = new Random();
            for (;;)
            {
                try
                {
                    Thread.sleep(random.nextInt(10));
                }
                catch (final InterruptedException e)
                {
                }
                bowee.bow(bower);
            }
        }
    }

    public static void main(final String[] args)
    {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        new Thread(new BowLoop(alphonse, gaston)).start();
        new Thread(new BowLoop(gaston, alphonse)).start();
    }
}
