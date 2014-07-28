package org.ken.avery.generics;

public class TreeDemo
{
    public static void main(final String[] args)
    {
        final TreeOne<String> treeOne = addTreesOne();
        printTreeOne(treeOne);

        final TreeTwo<String> treeTwo = addTreesTwo();
        printTreeTwo(treeTwo);

        final TreeThree<Number> treeThreeA = addTreesThree(0);
        printTreeThree(treeThreeA);
        treeThreeGetBranches(treeThreeA);

        final TreeThree<Number> treeThreeB = addTreesThree(10);
        printTreeThree(treeThreeB);
        treeThreeGetBranches(treeThreeB);

        final Number treeThreeASum = TreeThree.boundedWildCardSum(treeThreeA);
        final Number treeThreeBSum = TreeThree.boundedWildCardSum(treeThreeB);

        System.out.println("Tree Three A Bounded Wild Card Sum = " + treeThreeASum);
        System.out.println("Tree Three B Bounded Wild Card Sum = " + treeThreeBSum);

        System.out.println("Max between A/B: " + TreeThree.max(treeThreeA, treeThreeB).toString());

    }

    public static void printTreeOne(final TreeOne<String> treeOne)
    {
        System.out.println("Tree One Value: " + treeOne.getValue());
        for (int i = 0; i < treeOne.getNumBranches(); i++)
        {
            System.out.println("Branch " + i + ": " + treeOne.getBranch(i).getValue());
        }
    }

    public static TreeOne<String> addTreesOne()
    {
        final TreeOne<String> treeOne = new TreeOne<String>("Oak");
        treeOne.setValue("Oak");

        final TreeOne<String> postOak = new TreeOne<String>("Post Oak");
        postOak.setValue("Post Oak");
        treeOne.addBranch(postOak);

        final TreeOne<String> redOak = new TreeOne<String>("Red Oak");
        redOak.setValue("Red Oak");
        treeOne.addBranch(redOak);

        final TreeOne<String> liveOak = new TreeOne<String>("Live Oak");
        liveOak.setValue("Live Oak");
        treeOne.addBranch(liveOak);

        return treeOne;
    }

    public static void printTreeTwo(final TreeTwo<String> treeTwo)
    {
        System.out.println("Tree Two Value: " + treeTwo.getValue());
        for (int i = 0; i < treeTwo.getNumBranches(); i++)
        {
            System.out.println("Branch " + i + ": " + treeTwo.getBranch(i).getValue());
        }
    }

    public static TreeTwo<String> addTreesTwo()
    {
        final TreeTwo<String> treeTwo = new TreeTwo<String>("Pine");
        treeTwo.setValue("Pine");

        final TreeTwo<String> postOak = new TreeTwo<String>("Yellow Pine");
        postOak.setValue("Yellow Pine");
        treeTwo.addBranch(postOak);

        final TreeTwo<String> redOak = new TreeTwo<String>("Pinion Pine");
        redOak.setValue("Pinyon Pine");
        treeTwo.addBranch(redOak);

        final TreeTwo<String> liveOak = new TreeTwo<String>("Torry Pine");
        liveOak.setValue("Torry Pine");
        treeTwo.addBranch(liveOak);

        return treeTwo;
    }

    public static void printTreeThree(final TreeThree<Number> treeThree)
    {
        System.out.println("Tree Three Value: " + treeThree.getValue());
        for (int i = 0; i < treeThree.getNumBranches(); i++)
        {
            System.out.println("Branch " + i + ": " + treeThree.getBranch(i).getValue());
        }
    }

    public static TreeThree<Number> addTreesThree(final Number seed)
    {
        final TreeThree<Number> treeThree = new TreeThree<Number>(seed);

        final TreeThree<Integer> integerOne = new TreeThree<Integer>(seed.intValue() + 1);
        treeThree.addBranch(integerOne);

        final TreeThree<Integer> integerTwo = new TreeThree<Integer>(seed.intValue() + 2);
        treeThree.addBranch(integerTwo);

        final TreeThree<Integer> integerThree = new TreeThree<Integer>(seed.intValue() + 3);
        treeThree.addBranch(integerThree);

        return treeThree;
    }

    public static void treeThreeGetBranches(final TreeThree<Number> treeThree)
    {
        final TreeThree<? extends Number> branchZero = treeThree.getBranch(0);
        System.out.println("Tree Three Branch zero: " + branchZero.getValue());

        final TreeThree<?> branchOne = treeThree.getBranch(1);
        System.out.println("Tree Three Branch One: " + branchOne.getValue());

        final TreeThree<? extends Number> branchTwo = treeThree.getBranch(2);
        final Number branchTwoValue = branchTwo.getValue();
        System.out.println("Tree Three Branch Two: " + branchTwoValue);
    }
}
