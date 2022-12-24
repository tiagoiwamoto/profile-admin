package br.com.tiagoiwamoto.profileadmin.core.usecase;

public interface IUsecaseCreateUpdate<T> extends IUsecase<T> {

    T createOrUpdate(T record);

}
