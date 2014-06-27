package org.ken.avery.inheritance.object;

public class Circus implements Cloneable
{
    private int circusId;
    private String circusName;

    public Circus(final int id, final String name)
    {
        this.circusId = id;
        this.circusName = name;
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException
    {
        return super.clone();
    }

    public void setCircusId(final int circusId)
    {
        this.circusId = circusId;
    }

    public int getCircusId()
    {
        return circusId;
    }

    public void setCircusName(final String circusName)
    {
        this.circusName = circusName;
    }

    public String getCircusName()
    {
        return circusName;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + circusId;
        result = prime * result + ((circusName == null) ? 0 : circusName.hashCode());
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
        final Circus other = (Circus) obj;
        if (circusId != other.circusId)
        {
            return false;
        }
        if (circusName == null)
        {
            if (other.circusName != null)
            {
                return false;
            }
        }
        else if (!circusName.equals(other.circusName))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Circus [circusId="
                + circusId
                + ", circusName="
                + circusName
                + ", getCircusId()="
                + getCircusId()
                + ", getCircusName()="
                + getCircusName()
                + ", hashCode()="
                + hashCode()
                + ", getClass()="
                + getClass()
                + ", toString()="
                + super.toString()
                + "]";
    }

}
