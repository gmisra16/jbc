package com.ubs.opsit.interviews;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


/**
 * BerlinClock
 */
public class BerlinClock {
    private static final Logger LOG = LoggerFactory.getLogger(BerlinClock.class);
    private char sec = 'O';
    private final char[] hr_5 = new char[4];
    private final char[] hr_1 = new char[4];
    private final char[] min_5 = new char[11];
    private final char[] min_1 = new char[4];

    /**
     * @param time - The 24 hour time in the format of HH:MM:SS
     */
    BerlinClock(String time) {
        reset();
        validateAndInit(time);
        LOG.debug("Creation done");
    }

    /**
     * @param hours   - an int representing Hours
     * @param minutes - an int representing Minutes
     * @param seconds - an int representing Seconds
     */
    private void init(int hours, int minutes, int seconds) {
        this.sec = (seconds % 2 == 0) ? 'Y' : 'O';
        switchOn(hr_5, hours / 5, 'R');
        switchOn(hr_1, hours % 5, 'R');
        switchOn(min_5, minutes / 5, 'Y');
        yellowToRed(min_5);
        switchOn(min_1, minutes % 5, 'Y');
    }

    private void reset() {
        Arrays.fill(hr_5, 'O');
        Arrays.fill(hr_1, 'O');
        Arrays.fill(min_5, 'O');
        Arrays.fill(min_1, 'O');
        LOG.debug("reset done");
    }

    private void validateAndInit(String time) {
        if (time == null) throw new NullPointerException("Cannot convert null time");
        String[] times = time.split(":", 3);
        if (times.length != 3) throw new IllegalArgumentException("The 24 hour time in the format of HH:MM:SS");
        int hours;
        int minutes;
        int seconds;
        try {
            hours = Integer.parseInt(times[0]);
            minutes = Integer.parseInt(times[1]);
            seconds = Integer.parseInt(times[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The 24 hour time in the format of HH:MM:SS");
        }
        //24:00:00 is valid time
        if (!"24:00:00".equals(time)){
            if (hours < 0 || hours > 23) throw new IllegalArgumentException("Valid Hours 0-23. Actual " + hours);
            if (minutes < 0 || minutes > 59) throw new IllegalArgumentException("Valid Minutes 0-59. Actual " + minutes);
            if (seconds < 0 || seconds > 59) throw new IllegalArgumentException("Valid Seconds 0-59. Actual " + seconds);
        }

        LOG.debug("Validation done, calling init");
        init(hours, minutes, seconds);
        LOG.debug("init done");
    }

    /**
     * Creates a string for each row of the berlin clock.
     *
     */
    private void switchOn(char[] arr, int litLights, char lampType) {
        for (int i = 0; i < litLights; i++) {
            arr[i] = lampType;
        }
    }

    private void yellowToRed(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if ((i == 2 || i == 5 || i == 8) && chars[i] == 'Y') chars[i] = 'R';
        }

    }


    @Override
    public String toString() {
        return this.sec+System.lineSeparator()
                +new String(hr_5)+System.lineSeparator()
                +new String(hr_1)+System.lineSeparator()
                +new String(min_5)+System.lineSeparator()
                +new String(min_1);
    }
    /*public static void main(String ... args){
        System.out.println(new BerlinClock("13:17:01").toString());
    }*/

}