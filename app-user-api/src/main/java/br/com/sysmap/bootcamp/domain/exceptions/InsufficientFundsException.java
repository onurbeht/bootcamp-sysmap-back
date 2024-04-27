package br.com.sysmap.bootcamp.domain.exceptions;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException() {
        super("Insufficient founds! Update your balance.");
    }
}
