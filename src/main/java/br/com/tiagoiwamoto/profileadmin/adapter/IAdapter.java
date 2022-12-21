package br.com.tiagoiwamoto.profileadmin.adapter;

import java.util.List;
import java.util.UUID;

public interface IAdapter<T> {

    List<T> all();
    T save(T record);

    T recoveryByUuid(UUID uuid);

    void delete(UUID uuid);

}
