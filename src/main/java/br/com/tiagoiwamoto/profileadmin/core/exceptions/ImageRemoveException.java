package br.com.tiagoiwamoto.profileadmin.core.exceptions;

public class ImageRemoveException extends RuntimeException{

    public ImageRemoveException(Exception e) {
        super("Falha ao remover imagem, tente novamente mais tarde", e);
    }
}
