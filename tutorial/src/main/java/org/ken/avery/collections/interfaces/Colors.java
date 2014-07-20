package org.ken.avery.collections.interfaces;

import java.util.HashMap;
import java.util.Map;

public class Colors
{
    public static final String RED = "Red";
    public static final String AERO = "Aero";
    public static final String ALMOND = "Almond";
    public static final String AMBER = "Amber";
    public static final String BEIGE = "Beige";
    public static final String WHITE = "White";
    public static final String BLUE = "Blue";
    public static final String CHARCOAL = "Charcoal";
    public static final String PINK = "Pink";
    public static final String FALLOW = "Fallow";
    public static final String GREEN = "Green";
    public static final String BLACK = "Black";
    public static final String GRAY = "Gray";
    public static final String AZURE = "Azure";

    public static Map<Integer, String> colors()
    {
        final Map<Integer, String> colors = new HashMap<Integer, String>();

        colors.put(1, RED);
        colors.put(2, AERO);
        colors.put(3, ALMOND);
        colors.put(4, AMBER);
        colors.put(5, BEIGE);
        colors.put(6, WHITE);
        colors.put(7, BLUE);
        colors.put(8, CHARCOAL);
        colors.put(9, PINK);
        colors.put(10, FALLOW);
        colors.put(11, GREEN);
        colors.put(12, BLACK);
        colors.put(13, GRAY);
        colors.put(14, AZURE);

        return colors;
    }
}
