package br.com.tiagoiwamoto.profileadmin.core.exceptions;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException() {
        super("Registro não foi localizado!");
    }
}
