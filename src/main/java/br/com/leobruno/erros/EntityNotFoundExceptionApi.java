package br.com.leobruno.erros;

public class EntityNotFoundExceptionApi extends RuntimeException {

    private static final long serialVersionUID = -9114562132338775600L;

    public EntityNotFoundExceptionApi(String message) {
        super(message);
    }
}
