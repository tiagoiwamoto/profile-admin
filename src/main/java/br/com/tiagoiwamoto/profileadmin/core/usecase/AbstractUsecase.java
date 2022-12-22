package br.com.tiagoiwamoto.profileadmin.core.usecase;

import br.com.tiagoiwamoto.profileadmin.adapter.IAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.AbstractDomain;
import br.com.tiagoiwamoto.profileadmin.core.mapper.IMapper;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractUsecase implements IUsecaseCommon<AbstractDto>{

    private IAdapter<AbstractDomain> adapter;
    private IMapper<AbstractDomain, AbstractDto> mapper;

    public AbstractUsecase(IAdapter adapter, IMapper mapper) {
        this.adapter = adapter;
        this.mapper = mapper;
    }

    public AbstractDto createOrUpdate(AbstractDto dto){
        var resumeDomain = this.mapper.toDomain(dto);
        if(Objects.isNull(dto.getId())){
            resumeDomain.domainToSave();
        }else{
            var oldDomain = this.adapter.recoveryByUuid(dto.getUuid());
            resumeDomain.domainToUpdate(oldDomain);
        }

        var response = this.adapter.save(resumeDomain);
        return this.mapper.toDto(response);
    }

    public List<AbstractDto> recoveryRecords(){
        var response = this.adapter.all();
        var listOfRecordsDtos = response
                .stream()
                .map(
                        record ->
                                this.mapper.toDto(record))
                .collect(Collectors.toList());

        return listOfRecordsDtos;
    }

    public AbstractDto recoveryRecord(UUID uuid){
        var response = this.adapter.recoveryByUuid(uuid);

        return this.mapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        this.adapter.delete(uuid);
    }

}
