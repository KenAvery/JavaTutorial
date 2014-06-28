package org.ken.avery.inheritance.object;

public class MotorBoat extends BoatType
{
    private final int numberOfMotors;

    public MotorBoat()
    {
        super();
        this.name = "Bass Catcher";
        this.type = "Motor Boat";
        this.propulsion = "Motor";
        this.numberOfMotors = 1;
    }

    public MotorBoat(final String name, final int motors)
    {
        super();
        this.name = name;
        this.type = "Motor Boat";
        this.propulsion = "Motors";
        this.numberOfMotors = motors;
    }

    @Override
    public void operation()
    {
        System.out.format("The %s is a %s propelled by %d %s\n", name, type, numberOfMotors, propulsion);
    }
}
