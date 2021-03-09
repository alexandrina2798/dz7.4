package main.java;

public class NoSuchCountryException extends Exception {

    public NoSuchCountryException(final String country) {
        System.out.println("Страны " + country + " не существует");
    }
}
