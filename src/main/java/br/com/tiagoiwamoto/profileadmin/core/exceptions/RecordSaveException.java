package br.com.tiagoiwamoto.profileadmin.core.exceptions;

public class RecordSaveException extends RuntimeException{

    public RecordSaveException() {
        super("Falha ao gravar registro, tente novamente mais tarde");
    }
}
