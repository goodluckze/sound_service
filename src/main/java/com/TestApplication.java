package com;

import com.cmb.startup.service.encode.EventIDBuilder;

import java.security.Timestamp;
import java.util.Date;


public class TestApplication {

    public static void main(String args[]){
        System.out.println(EventIDBuilder.getEventId(1122l));
        Date date = new Date();
        System.out.println(new java.sql.Date(date.getTime()) + " " + new java.sql.Time(date.getTime()) );
    }
}
