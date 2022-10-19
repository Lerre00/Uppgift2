package Uppgift2;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static List<Person> getMatchingCustomer(List<Person> allCustomers, String id){

        List<Person> onlyPayingCustomers = new ArrayList<>();

        for (Person p : allCustomers){
            if (id.equalsIgnoreCase(p.getName()) || id.equalsIgnoreCase(p.getSsn())){
                    onlyPayingCustomers.add(p);
                    return onlyPayingCustomers;
            }
        }
        return onlyPayingCustomers;
    }

    public static List<Person> getValidCustomer(List<Person> allCustomers, String id, LocalDate todaysDate){

        List<Person> onlyPayingCustomers = new ArrayList<>();

        for (Person p : allCustomers){

            LocalDate personDate = LocalDate.parse(p.getDate()).plusYears(1);

            if ((id.equalsIgnoreCase(p.getName()) || id.equalsIgnoreCase(p.getSsn())) && (personDate.isEqual(todaysDate) || personDate.isAfter(todaysDate))){
                onlyPayingCustomers.add(p);
                return onlyPayingCustomers;
            }

        }

        return onlyPayingCustomers;
    }

    public static String getLoyality(List<Person> allCustomers, String id, LocalDate todaysDate){
        String s = "";

        for (Person p : allCustomers){

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
        else if(allCustomers.isEmpty()){
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
