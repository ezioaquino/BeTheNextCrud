package br.com.ada.controller.impl.exception;

public class DAOException extends RuntimeException{
    public DAOException(String message){
        super(message);
    }

    public DAOException(String message, Exception cause){
        super(message, cause);
    }
}
