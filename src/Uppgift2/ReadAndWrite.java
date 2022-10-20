package Uppgift2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadAndWrite {

    public static List<Person> readDataFromFile(String readFromFile) {
        String firstRow;
        String secondRow = "";
        Path inFilePath = Paths.get(readFromFile);
        List<Person> customerList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(inFilePath)) {
            while (fileScanner.hasNext()) {
                firstRow = fileScanner.nextLine();
                String ssn = Logic.splitForSsn(firstRow);
                String name = Logic.splitForName(firstRow);
                if (fileScanner.hasNext()) {
                    secondRow = fileScanner.nextLine();
                }
                Person p = new Person(ssn, name, secondRow);
                customerList.add(p);
            }

        } catch (IOException e) {
            System.out.println("Filen lästes in fel");
            e.printStackTrace();
            System.exit(0);
        }

        return customerList;
    }

    public static void writeDataToFile(String writeToFile, List<Person> validCustomer, LocalDate todaysDate){
        Path outFilePath = Paths.get(writeToFile);
        try (PrintWriter w = new PrintWriter(new FileWriter(outFilePath.toFile(), true))) {
            for (Person p : validCustomer){
                w.println("namn: " + p.getName() + " pnr: " + p.getSsn() + " gymmade senast: " + todaysDate);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Det gick inte att skriva in till fil");
            e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
    }

}
