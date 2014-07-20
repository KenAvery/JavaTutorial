package org.ken.avery.collections.interfaces;

import java.util.Comparator;

public class CompareRaceHorses implements Comparator<RaceHorse>
{

    @Override
    public int compare(final RaceHorse raceHorseOne, final RaceHorse raceHorseTwo)
    {
        if (raceHorseOne.getRacesWon() < raceHorseTwo.getRacesWon())
        {
            return 1;
        }
        else if (raceHorseOne.getRacesWon() > raceHorseTwo.getRacesWon())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

}
