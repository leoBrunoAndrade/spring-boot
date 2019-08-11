package br.com.leobruno.erros;



public class ObjectError {

    private final String message;
    private final String field;

    public ObjectError(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }



}
