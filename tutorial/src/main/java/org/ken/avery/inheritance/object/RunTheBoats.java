package org.ken.avery.inheritance.object;

public class RunTheBoats
{
    public static void main(final String[] args)
    {
        RowBoat rowBoat = new RowBoat();
        MotorBoat motorBoat = new MotorBoat();

        rowBoat.operation();
        motorBoat.operation();

        rowBoat = new RowBoat("Strider", 6);
        motorBoat = new MotorBoat("Rocket", 4);

        rowBoat.operation();
        motorBoat.operation();
    }

}
