package Uppgift2;

public class Person {

    protected String ssn;
    protected String name;
    protected String date;

    public Person (String ssn, String name, String date){
        this.ssn = ssn;
        this.name = name;
        this.date = date;
    }

    public String getSsn(){
        return ssn;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }



}

