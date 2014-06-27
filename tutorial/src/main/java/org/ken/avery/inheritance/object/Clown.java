package org.ken.avery.inheritance.object;

public class Clown implements Cloneable
{
    private int clowenId;
    private String clownName;
    private Circus circus;

    public Clown(final int id, final String name, final Circus circus)
    {
        this.clowenId = id;
        this.clownName = name;
        this.circus = circus;
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException
    {
        final Clown clown = (Clown) super.clone();
        clown.setCircus((Circus) clown.getCircus().clone());
        return clown;
    }

    public int getClowenId()
    {
        return clowenId;
    }

    public void setClowenId(final int clowenId)
    {
        this.clowenId = clowenId;
    }

    public String getClownName()
    {
        return clownName;
    }

    public void setClownName(final String clownName)
    {
        this.clownName = clownName;
    }

    public Circus getCircus()
    {
        return circus;
    }

    public void setCircus(final Circus circus)
    {
        this.circus = circus;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((circus == null) ? 0 : circus.hashCode());
        result = prime * result + clowenId;
        result = prime * result + ((clownName == null) ? 0 : clownName.hashCode());
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
        final Clown other = (Clown) obj;
        if (circus == null)
        {
            if (other.circus != null)
            {
                return false;
            }
        }
        else if (!circus.equals(other.circus))
        {
            return false;
        }
        if (clowenId != other.clowenId)
        {
            return false;
        }
        if (clownName == null)
        {
            if (other.clownName != null)
            {
                return false;
            }
        }
        else if (!clownName.equals(other.clownName))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Clown [clowenId="
                + clowenId
                + ", clownName="
                + clownName
                + ", circus="
                + circus
                + ", getClowenId()="
                + getClowenId()
                + ", getClownName()="
                + getClownName()
                + ", getCircus()="
                + getCircus()
                + ", hashCode()="
                + hashCode()
                + ", getClass()="
                + getClass()
                + ", toString()="
                + super.toString()
                + "]";
    }

}
