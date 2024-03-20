package com.grupo1.infraestructure.util;


import java.sql.Timestamp;

public class CurrentTime {

    public static Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
