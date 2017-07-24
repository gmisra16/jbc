package com.ubs.opsit.interviews;

/*
* Test class :BerlinClockFixture
* */
public class StringToBerlinClock implements TimeConverter {
    private BerlinClock berlinClock;
    @Override
    public String convertTime(String aTime){
        berlinClock = new BerlinClock(aTime);
        return berlinClock.toString();
    }
}
