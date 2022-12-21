package br.com.tiagoiwamoto.profileadmin.core.usecase;

import org.springframework.web.multipart.MultipartFile;

public interface IUsecaseWithFile<T> extends IUsecase<T> {

    T createOrUpdate(T record, MultipartFile multipartFile);

}
