package br.com.tiagoiwamoto.profileadmin.core.mapper;

public interface IMapper<M, D> {

    public M toDomain(D dto);
    public D toDto(M domain);

}
