package br.com.leobruno.erros;

import java.util.List;


public class ErrorDetail {

    private final String message;
    private final int code;
    private final String status;
    private final String object;
    private final List<ObjectError> errors;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getObject() {
        return object;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public static class BuilderErrorDetail {
        private int code;
        private String message;
        private String status;
        private String object;
        private List<ObjectError> errors;

        public BuilderErrorDetail(int code){
            this.code = code;
        }

        public BuilderErrorDetail errors (List<ObjectError> errors){
            this.errors = errors;
            return this;
        }

        public BuilderErrorDetail message(String message){
            this.message = message;
            return this;
        }

        public BuilderErrorDetail status (String status){
            this.status = status;
            return this;
        }

        public BuilderErrorDetail object (String object){
            this.object = object;
            return this;
        }

        public ErrorDetail build(){
            return new ErrorDetail(this);
        }
    }

    private ErrorDetail (BuilderErrorDetail builderErrorDetail){
        this.code = builderErrorDetail.code;
        this.errors = builderErrorDetail.errors;
        this.message = builderErrorDetail.message;
        this.object = builderErrorDetail.object;
        this.status = builderErrorDetail.status;
    }



}
