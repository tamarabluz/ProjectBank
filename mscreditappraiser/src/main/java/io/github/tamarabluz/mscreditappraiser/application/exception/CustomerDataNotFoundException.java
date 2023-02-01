package io.github.tamarabluz.mscreditappraiser.application.exception;

public class CustomerDataNotFoundException extends Exception{
    public CustomerDataNotFoundException(){
        super("Customer data not found.");
    }
}
