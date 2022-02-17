package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

public class LastName {

    private final String value;

    public static LastName from(String value) {
        return new LastName(value);
    }

    private LastName(String value) {
        this.value = value;
    }

    public String display() {
        return value.toString();
    }

}
