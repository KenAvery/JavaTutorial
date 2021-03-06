package org.ken.avery.zmq;

import java.nio.ByteBuffer;
import java.util.Arrays;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

/**
 *
 * A simple key value message class
 * 
 * @author Danish Shrestha <dshrestha06@gmail.com>
 *
 */
public class kvsimple
{
    private final String key;
    private long sequence;
    private final byte[] body;

    public kvsimple(final String key, final long sequence, final byte[] body)
    {
        this.key = key;
        this.sequence = sequence;
        this.body = body; // clone if needed
    }

    public String getKey()
    {
        return key;
    }

    public long getSequence()
    {
        return sequence;
    }

    public void setSequence(final long sequence)
    {
        this.sequence = sequence;
    }

    public byte[] getBody()
    {
        return body;
    }

    public void send(final Socket publisher)
    {

        publisher.send(key.getBytes(), ZMQ.SNDMORE);

        final ByteBuffer bb = ByteBuffer.allocate(8);
        bb.asLongBuffer().put(sequence);
        publisher.send(bb.array(), ZMQ.SNDMORE);

        publisher.send(body, 0);
    }

    public static kvsimple recv(final Socket updates)
    {
        byte[] data = updates.recv(0);
        if (data == null || !updates.hasReceiveMore())
        {
            return null;
        }
        final String key = new String(data);
        data = updates.recv(0);
        if (data == null || !updates.hasReceiveMore())
        {
            return null;
        }
        final Long sequence = ByteBuffer.wrap(data).getLong();
        final byte[] body = updates.recv(0);
        if (body == null || updates.hasReceiveMore())
        {
            return null;
        }

        return new kvsimple(key, sequence, body);
    }

    @Override
    public String toString()
    {
        return "kvsimple [key=" + key + ", sequence=" + sequence + ", body=" + Arrays.toString(body) + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(body);
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + (int) (sequence ^ (sequence >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final kvsimple other = (kvsimple) obj;
        if (!Arrays.equals(body, other.body))
        {
            return false;
        }
        if (key == null)
        {
            if (other.key != null)
            {
                return false;
            }
        }
        else if (!key.equals(other.key))
        {
            return false;
        }
        if (sequence != other.sequence)
        {
            return false;
        }
        return true;
    }

}
