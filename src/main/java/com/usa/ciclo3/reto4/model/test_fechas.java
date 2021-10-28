package com.usa.ciclo3.reto4.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class test_fechas {
    public static void main(String[] args) throws ParseException {

        Date d = new Date();
        LocalDate lDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(lDate);
        System.out.println();

        // Pattern -> "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = format.parse("2020-12-20T00:00:00.000+00:00");
        System.out.println(date);

        System.out.println("-------------------");

        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String fechaTexto = formatter.format(fecha);
        System.out.println(fechaTexto);

        System.out.println();

        final String OLD_FORMAT = "YYYY-MM-dd"; 
        final String NEW_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";


        String oldDateString = "2020-12-20"; 
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT); 
        Date d5 = sdf.parse(oldDateString); 
        sdf.applyPattern(NEW_FORMAT); 
        String newDateString = sdf.format(d5);
        System.out.println(newDateString);


    }
}
