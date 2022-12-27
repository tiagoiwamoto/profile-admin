package br.com.tiagoiwamoto.profileadmin.core.exceptions;

public class ImageStoreException extends RuntimeException{

    public ImageStoreException(Exception e) {
        super("Falha ao armazenar imagem, tente novamente mais tarde", e);
    }
}
