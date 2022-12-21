package br.com.tiagoiwamoto.profileadmin.core.usecase;

public interface IUsecaseCommon<T> extends IUsecase<T> {

    T createOrUpdate(T record);

}
