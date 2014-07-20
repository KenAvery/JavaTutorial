package org.ken.avery.collections;

public class RaceHorse implements Comparable<RaceHorse>
{
    private String raceHorse = null;
    private Integer racesWon = 0;

    public RaceHorse(final String raceHorse, final Integer racesWon)
    {
        this.setRaceHorse(raceHorse);
        this.setRacesWon(racesWon);
    }

    @Override
    public int compareTo(final RaceHorse raceHorse)
    {
        if (this.racesWon > raceHorse.getRacesWon())
        {
            return 1;
        }
        else if (this.racesWon < raceHorse.getRacesWon())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    public String getRaceHorse()
    {
        return raceHorse;
    }

    public void setRaceHorse(final String raceHorse)
    {
        this.raceHorse = raceHorse;
    }

    public Integer getRacesWon()
    {
        return racesWon;
    }

    public void setRacesWon(final Integer racesWon)
    {
        this.racesWon = racesWon;
    }

}
