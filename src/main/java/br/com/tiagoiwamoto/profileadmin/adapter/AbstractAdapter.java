package br.com.tiagoiwamoto.profileadmin.adapter;

import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
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
        log.info("iniciando busca: metodo: all(), para o dominio: " + domain);
        try{
            var response = this.repository.findAll();
            log.info("Resposta da minha consulta: " + response);
            return response;
        }catch (Exception e){
            log.error(
                    String.format(
                    "Falha ao realizar consulta no dominio %s",
                    domain),
                    e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public T save(T record) {
        log.info("iniciando gravação do registro " + domain);
        try{
            var response = this.repository.save(record);
            log.info("registro gravado com sucesso: " + response);
            return response;
        }catch (Exception e){
            log.error(
                    String.format(
                            "Falha ao gravar o dominio %s",
                            domain),
                    e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public T recoveryByUuid(UUID uuid) {
        try{
            var optionalRecord = this.repository.findByUuid(uuid);
            return optionalRecord.orElseThrow(() -> new RecordNotFoundException());
        }catch (Exception e){
            log.error(
                    String.format(
                            "Falha ao realizar consulta no dominio %s para o uuid: %s",
                            domain, uuid),
                    e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID uuid) {
        try{
            var record = this.recoveryByUuid(uuid);
            this.repository.delete(record);
        }catch (Exception e){
            log.error(
                    String.format(
                            "Falha ao remover o registro %s do dominio %s",
                            uuid, domain),
                    e);
            throw new RuntimeException(e);
        }
    }
}
