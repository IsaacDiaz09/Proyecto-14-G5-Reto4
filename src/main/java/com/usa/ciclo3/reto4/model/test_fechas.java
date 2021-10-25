package com.usa.ciclo3.reto4.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class test_fechas {
    public static void main(String[] args) throws ParseException {

    	Date d = new Date();
    	LocalDate lDate =d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(lDate);
    }
}
