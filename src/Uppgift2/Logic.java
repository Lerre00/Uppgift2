package Uppgift2;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static List<Person> getMatchingCustomer(List<Person> customerList, String id){

        List<Person> matchingCustomer = new ArrayList<>();

        for (Person p : customerList){
            if (id.equalsIgnoreCase(p.getName()) || id.equalsIgnoreCase(p.getSsn())){
                matchingCustomer.add(p);
                    return matchingCustomer;
            }
        }
        return matchingCustomer;
    }

    public static List<Person> getValidCustomer(List<Person> customerList, String id, LocalDate todaysDate){

        List<Person> validCustomer = new ArrayList<>();

        for (Person p : customerList){

            LocalDate personDate = LocalDate.parse(p.getDate()).plusYears(1);

            if ((id.equalsIgnoreCase(p.getName()) || id.equalsIgnoreCase(p.getSsn())) && (personDate.isEqual(todaysDate) || personDate.isAfter(todaysDate))){
                validCustomer.add(p);
                return validCustomer;
            }

        }

        return validCustomer;
    }

    public static String getLoyality(List<Person> matchingCustomer, String id, LocalDate todaysDate){
        String s = "";

        for (Person p : matchingCustomer){

            LocalDate personDate = LocalDate.parse(p.getDate()).plusYears(1);

            if ((id.equalsIgnoreCase(p.getName()) || id.equalsIgnoreCase(p.getSsn())) && (personDate.isEqual(todaysDate) || personDate.isAfter(todaysDate))){
                s = p.getName() + " är en nuvarande medlem";
            }
            else if((id.equalsIgnoreCase(p.getName()) || id.equalsIgnoreCase(p.getSsn())) && personDate.isBefore(todaysDate)) {
                s = p.getName() + " är en före detta medlem";
            }

        }

        if(id.isEmpty()){
            s = "Du skrev inte in ett namn";
        }
        else if(matchingCustomer.isEmpty()){
            s = id + " är obehörig";
        }

        return s;
    }

    public static String splitForSsn(String firstRow){

        String[] stringarr = firstRow.split(",");
        return stringarr[0].trim();

    }

    public static String splitForName(String firstRow){
        String[] stringarr = firstRow.split(",");
        return stringarr[1].trim();
    }

}
