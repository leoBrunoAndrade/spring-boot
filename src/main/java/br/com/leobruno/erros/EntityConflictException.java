package br.com.leobruno.erros;

public class EntityConflictException extends RuntimeException {

    public EntityConflictException(String message) {
        super(message);
    }
}
