package br.com.tiagoiwamoto.profileadmin.core.exceptions;

public class RecordRemoveException extends RuntimeException{

    public RecordRemoveException() {
        super("Falha ao remover registro, tente novamente mais tarde");
    }
}
