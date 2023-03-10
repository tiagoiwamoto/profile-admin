package br.com.tiagoiwamoto.profileadmin.adapter;

import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordRecoveryException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordRemoveException;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordSaveException;
import br.com.tiagoiwamoto.profileadmin.core.repository.IRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public abstract class AbstractAdapter<T> implements IAdapter<T>{

    private IRepository<T, Long> repository;
    private String domain;

    public AbstractAdapter(IRepository repository, String domain) {
        this.repository = repository;
        this.domain = domain;
    }

    @Override
    public List<T> all() {
        log.info(String.format("iniciando busca: metodo: all(), para o dominio: %s", domain));
        try{
            var response = this.repository.findAll();
            log.info(String.format("Resposta da consulta para o domínio %s: %s", domain, response));
            return response;
        }catch (Exception e){
            log.error(
                    String.format(
                    "Falha ao realizar consulta no dominio %s",
                    domain),
                    e);
            throw new RecordRecoveryException();
        }
    }

    @Override
    public T save(T record) {
        log.info(String.format("iniciando gravação do registro %s", domain));
        try{
            var response = this.repository.save(record);
            log.info(String.format("registro gravado com sucesso: %s", response));
            return response;
        }catch (Exception e){
            log.error(
                    String.format(
                            "Falha ao gravar o dominio %s",
                            domain),
                    e);
            throw new RecordSaveException();
        }
    }

    @Override
    public T recoveryByUuid(UUID uuid) {
        log.info(String.format("iniciando a recuperação do registro %s para o domínio %s", uuid, domain));
        try{
            var optionalRecord = this.repository.findByUuid(uuid);
            var optionalRecordResponse = optionalRecord.orElseThrow(() -> new RecordNotFoundException());
            log.info(String.format("registro %s recuperado com sucesso para o domínio %s", uuid, domain));
            return optionalRecordResponse;
        }catch (Exception e){
            log.error(
                    String.format(
                            "Falha ao realizar consulta no dominio %s para o uuid: %s",
                            domain, uuid),
                    e);
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void delete(UUID uuid) {
        log.info(String.format("iniciando a remoção do registro %s para o domínio %s", uuid, domain));
        try{
            var record = this.recoveryByUuid(uuid);
            this.repository.delete(record);
            log.info(String.format("registro %s foi removido com sucesso para o domínio %s", uuid, domain));
        }catch (Exception e){
            log.error(
                    String.format(
                            "Falha ao remover o registro %s do dominio %s",
                            uuid, domain),
                    e);
            throw new RecordRemoveException();
        }
    }
}
