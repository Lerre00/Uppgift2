package Uppgift2;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    Logic logic = new Logic();
    Person p1 = new Person("7703021234", "Alhambra Aromes","2022-07-01");
    Person p2 = new Person("8204021234", "Bear Belle", "2019-12-02");
    Person p3 = new Person("7608021234", "Diamanda Djedi", "2022-01-30");
    Person p4 = new Person("0009188236", "Leroy Collazo","2012-01-01");

    List<Person> testCustomers = new ArrayList<>();

    LocalDate testDate = LocalDate.of(2022, 10, 20);

    @Test
    public final void getMatchingCustomerTest() {
        List<Person> listWithValidName = new ArrayList<>();
        List<Person> listWithInvalidName = new ArrayList<>();
        testCustomers = Arrays.asList(p1, p2, p3);

        //testar så att den skickar ut alla
        listWithValidName = logic.getMatchingCustomer(testCustomers, "Alhambra Aromes");

        //testar så att listan bara innehåller 1 person när den hittat en matching
        assertTrue(listWithValidName.size() == 1);
        assertFalse(listWithValidName.size() == 0);

        //testar så att ID på personen stämmer efter matchning
        assertTrue(listWithValidName.get(0).getName().equals("Alhambra Aromes"));
        assertTrue(listWithValidName.get(0).getSsn() == "7703021234");

        //gör ett likadant test som ovan med en annan person
        listWithValidName = logic.getMatchingCustomer(testCustomers, "Bear Belle");

        assertTrue(listWithValidName.size() == 1);
        assertFalse(listWithValidName.size() == 0);

        assertTrue(listWithValidName.get(0).getName().equals("Bear Belle"));
        assertTrue(listWithValidName.get(0).getSsn() == "8204021234");

        //testar så att listan är tom ifall man skickar in ett namn som inte finns
        listWithInvalidName = logic.getMatchingCustomer(testCustomers, "Leroy Collazo");

        assertTrue(listWithInvalidName.size() == 0);
        assertFalse(listWithInvalidName.size() == 1);
    }

    @Test
    public final void getValidCustomersTest() {

        List<Person> listWithValidName = new ArrayList<>();
        List<Person> listWithInvalidName = new ArrayList<>();
        testCustomers = Arrays.asList(p1, p2, p3, p4);


        //testar så att listan skickar iväg rätt person ifall den hittar en matching
        listWithValidName = logic.getValidCustomer(testCustomers, "Alhambra Aromes", testDate);

        //testar så att listan bara innehåller 1 person när den hittat en matching
        assertTrue(listWithValidName.size() == 1);
        assertFalse(listWithValidName.size() == 0);

        //testar så att ID på personen stämmer efter matchning
        assertTrue(listWithValidName.get(0).getName().equals("Alhambra Aromes"));
        assertTrue(listWithValidName.get(0).getSsn() == "7703021234");


        //testar så att listan är tom ifall man matar in en person vars namn eller pnr finns i filen men datumet inte stämmer
        listWithInvalidName = logic.getValidCustomer(testCustomers, "Bear Belle", testDate);
        assertTrue(listWithInvalidName.size() == 0);
        assertFalse(listWithInvalidName.size() == 1);


        //testar så att listan är tom ifall man skickar in en person som inte finns i filen
        listWithInvalidName = logic.getValidCustomer(testCustomers, "Leroy Collazo", testDate);
        assertTrue(listWithInvalidName.size() == 0);
        assertFalse(listWithInvalidName.size() == 1);

    }

    @Test
    public final void getLoyalityTest() {

        List<Person> testCustomer = new ArrayList<>();

        //testar så att när en nuvarande medlem matas in så skriver programmet ut rätt meddelande
        testCustomer = Arrays.asList(p1);
        assertTrue(logic.getLoyality(testCustomer, "Alhambra Aromes", testDate)
                .equals(p1.getName() + " är en nuvarande medlem"));

        //testar så att när en före detta medlem matas in så skriver programmet ut rätt meddelande
        testCustomer = Arrays.asList(p2);
        assertTrue(logic.getLoyality(testCustomer, "Bear Belle", testDate)
                .equals(p2.getName() + " är en före detta medlem"));

        //testar så att när en obehörig matas in så skriver programmet ut rätt meddelande
        testCustomer = Arrays.asList();
        assertTrue(logic.getLoyality(testCustomer, "Leroy Collazo", testDate)
                .equals(p4.getName() + " är obehörig"));

        //testar så att när inget namn matas in så skriver programmet ut rätt meddelande
        testCustomer = Arrays.asList();
        assertTrue(logic.getLoyality(testCustomer, "", testDate)
                .equals("Du skrev inte in ett namn"));



    }

    @Test
    void splitForSsnTest() {
        String firstRow = "pnr, namn";
        assertTrue(logic.splitForSsn(firstRow).equalsIgnoreCase("pnr"));
        assertFalse(logic.splitForSsn(firstRow).equalsIgnoreCase("namn"));
    }

    @Test
    void splitForNameTest() {
        String firstRow = "pnr, namn";
        assertTrue(logic.splitForName(firstRow).equalsIgnoreCase("namn"));
        assertFalse(logic.splitForName(firstRow).equalsIgnoreCase("pnr"));
    }

}