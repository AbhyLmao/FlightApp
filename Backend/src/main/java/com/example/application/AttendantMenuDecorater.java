 package com.example.application;

 public class AttendantMenuDecorater implements Menu{


     private final ReadPassengersQuery readPassengersQuery;

     public AttendantMenuDecorater() {

         this.readPassengersQuery = new ReadPassengersQuery();
     }
     @Override
     public String showMenu() {
         return "Show Passenger List";
     }

 }

