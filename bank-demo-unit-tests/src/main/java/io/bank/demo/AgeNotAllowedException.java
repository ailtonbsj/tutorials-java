package io.bank.demo;

public class AgeNotAllowedException extends Exception {

    public static String MESSAGE = "Age not allowed!";

    public AgeNotAllowedException(String errorMessage){
        super(errorMessage);
    }
}
