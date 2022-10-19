package Uppgift2;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Uppgift2 {

    public Uppgift2() {

        LocalDate todaysDate = LocalDate.now();

        Scanner sc = new Scanner(System.in);

        System.out.println("skriv in personens namn eller personnummer: ");
        String id = sc.nextLine();

        String filePath = "src/Uppgift2/customers.txt";
        String outFilePathString = "src/Uppgift2/PayingCustomers.txt";

        List<Person> customerList = ReadAndWrite.readDataFromFile(filePath);

        List <Person> matchingCustomer = Logic.getMatchingCustomer(customerList, id);

        List <Person> validCustomer = Logic.getValidCustomer(customerList, id, todaysDate);

        String loyality = Logic.getLoyality(matchingCustomer, id, todaysDate);

        ReadAndWrite.writeDataToFile(outFilePathString, validCustomer, todaysDate);

        System.out.println(loyality);


    }

    public static void main(String[] args){
        Uppgift2 ö = new Uppgift2();
    }

}
