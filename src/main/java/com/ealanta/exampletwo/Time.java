package com.ealanta.exampletwo;

import java.time.Instant;

public class Time {
    private Instant time;

    public Time() {
    }
    public Time(Instant time){
        this.time = time;
    }
    public Instant getTime(){
        return time;
    }
    public void setTime(Instant time){
        this.time = time;
    }

}
