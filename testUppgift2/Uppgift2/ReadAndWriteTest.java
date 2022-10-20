package Uppgift2;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReadAndWriteTest {

    ReadAndWrite rw = new ReadAndWrite();

    String outFile = "testUppgift2/Uppgift2/PayingCustomers.txt";
    String inFile = "testUppgift2/Uppgift2/customers.txt";

    @Test
    public final void readDataFromFile() {

        List<Person> persons = rw.readDataFromFile(inFile);
        assertTrue(persons.size() == 14);
        assertFalse(persons.size() == 0);

    }

    @Test
    public final void writeDataToFile() {
        //LocalDate todaysDate = LocalDate.now();
        LocalDate testDate = LocalDate.of(2022, 10, 20);

        Person p1 = new Person("7703021234", "Alhambra Aromes",
                "2022-07-01");

        List<Person> testPersons = Arrays.asList(p1);

        rw.writeDataToFile(outFile, testPersons, testDate);

        Scanner fs = null;
        try {
            fs = new Scanner(new FileReader(outFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String temp = "";
        while(fs.hasNextLine()){
            temp = fs.nextLine();
        }

        assertTrue(temp.equals("namn: Alhambra Aromes pnr: 7703021234 gymmade senast: 2022-10-20"));
        assertFalse(temp.equals("namn: Alhambra Aromes pnr: 7703021234 gymmade senast: 2022-07-01"));

    }
}