package com.fooddelivery.utils;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
public class TimeDurationFinder {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isStarted;
    private boolean isEnded;

    /**
     * @return {@link TimeDurationFinder} this object to use as builder
     * @throws RuntimeException when call after end() method or already started
     */
    public TimeDurationFinder init() {
        if (isEnded) {
            throw new RuntimeException("You cannot initialize time after calling method end()!");
        }
        if (isStarted) {
            throw new RuntimeException("You cannot start timer twice!");
        }
        this.isStarted = true;
        this.startTime = LocalDateTime.now();
        return this;
    }

    /**
     * @return {@link TimeDurationFinder} this object to use as builder
     * @throws RuntimeException when doesn't started or already ended
     */
    public TimeDurationFinder end() throws RuntimeException {
        if (isEnded) {
            throw new RuntimeException("The timer is already ended!");
        }
        if (!isStarted) {
            throw new RuntimeException("The timer doesn't started!");
        }
        this.isEnded = true;
        this.endTime = LocalDateTime.now();
        return this;
    }

    /**
     * @param chronoUnit {@link ChronoUnit} that uses to identify type of range
     * @return {@link Long} range between {@link #init()} and {@link #end()} methods
     * @throws RuntimeException when doesn't started or doesn't ended
     */
    public long findDifference(ChronoUnit chronoUnit) throws RuntimeException {
        if (!isStarted) {
            throw new RuntimeException("The timer doesn't started!");
        }
        if (!isEnded) {
            throw new RuntimeException("The timer doesn't ended!");
        }
        return chronoUnit.between(this.startTime, this.endTime);
    }

}
