package org.ken.avery.inheritance.object;

public class RowBoat extends BoatType
{
    private final int numberOfOars;

    public RowBoat()
    {
        super();
        this.name = "Santa Maria";
        this.type = "Row Boat";
        this.propulsion = "Oars";
        this.numberOfOars = 2;
    }

    public RowBoat(final String name, final int oars)
    {
        super();
        this.name = name;
        this.type = "Row Boat";
        this.propulsion = "Oars";
        this.numberOfOars = oars;
    }

    @Override
    public void operation()
    {
        System.out.format("The %s is a %s propelled by %d %s\n", name, type, numberOfOars, propulsion);
    }
}
