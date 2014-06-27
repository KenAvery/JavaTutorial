package org.ken.avery.inheritance.object;

public class CloneTheClown
{
    public static void main(final String[] args)
            throws CloneNotSupportedException
    {
        final Circus circus = new Circus(1, "Ringling Bros.");
        final Clown clown = new Clown(1, "Lou Jacobs", circus);

        //Clone the clown
        final Clown cloned = (Clown) clown.clone();
        cloned.setClowenId(2);
        cloned.setClownName("Demetrius Nock");
        cloned.getCircus().setCircusName("Barnum and Bailey");

        System.out.format("Clown id       : %d\n", clown.getClowenId());
        System.out.format("Clown name     : %s\n", clown.getClownName());
        System.out.format("Clown works for: %s\n", clown.getCircus().getCircusName());
        System.out.println("Clown to String" + clown.toString() + "\n\n");

        System.out.format("Clone id       : %d\n", cloned.getClowenId());
        System.out.format("Clone name     : %s\n", cloned.getClownName());
        System.out.format("Clone works for: %s\n", cloned.getCircus().getCircusName());
        System.out.println("Clone to String" + cloned.toString() + "\n\n");

        System.out.format("Is the Clown object equel to the clone : %s\n", (clown == cloned));
        System.out.format("Is the clown class equal using getClass: %s\n", (clown.getClass() == cloned.getClass()));
        System.out.format("Is the clown class equal using equals: %s\n", (clown.equals(cloned)));

    }
}
