package br.com.tiagoiwamoto.profileadmin.core.exceptions;

public class RecordRecoveryException extends RuntimeException{

    public RecordRecoveryException() {
        super("Falha ao recuperar registro, tente novamente mais tarde");
    }
}
