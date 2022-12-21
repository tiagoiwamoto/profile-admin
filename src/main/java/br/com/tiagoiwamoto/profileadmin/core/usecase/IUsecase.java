package br.com.tiagoiwamoto.profileadmin.core.usecase;

import java.util.List;
import java.util.UUID;

public interface IUsecase<T> {

    List<T> recoveryRecords();
    T recoveryRecord(UUID uuid);
    void removeRecord(UUID uuid);

}
