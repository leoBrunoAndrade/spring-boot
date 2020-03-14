package br.com.leobruno.erros;

public class EntityConflictException extends RuntimeException {

    private static final long serialVersionUID = -3415627180003178312L;

    public EntityConflictException(String message) {
        super(message);
    }
}
