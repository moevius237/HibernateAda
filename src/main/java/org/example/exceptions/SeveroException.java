package org.example.exceptions;

public class SeveroException extends Exception{
    //private long errorCode;
    private ErrorCode errorCode;
    public SeveroException(ErrorCode errorCode) {
        this.errorCode=errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public SeveroException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode=errorCode;
    }
    public String getDetailMessage(){
        return errorCode + " " + this.getMessage();
    }
}
