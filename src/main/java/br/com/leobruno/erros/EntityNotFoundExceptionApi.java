package br.com.leobruno.erros;

public class EntityNotFoundExceptionApi extends RuntimeException {

    public EntityNotFoundExceptionApi(String message) {
        super(message);
    }
}
