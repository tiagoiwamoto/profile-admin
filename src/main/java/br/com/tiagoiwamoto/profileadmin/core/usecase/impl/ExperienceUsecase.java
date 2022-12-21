package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ExperienceAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ExperienceMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ExperienceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceUsecase implements IUsecaseCommon<ExperienceDto> {

    private final ExperienceAdapter experienceAdapter;
    private final ExperienceMapper experienceMapper;

    public ExperienceDto createOrUpdate(ExperienceDto experienceDto){
        var resumeDomain = this.experienceMapper.toDomain(experienceDto);
        if(Objects.isNull(experienceDto.getId())){
            resumeDomain.domainToSave();
        }else{
            var oldDomain = this.experienceAdapter.recoveryByUuid(experienceDto.getUuid());
            resumeDomain.domainToUpdate(oldDomain);
        }

        var response = this.experienceAdapter.save(resumeDomain);
        return this.experienceMapper.toDto(response);
    }

    public List<ExperienceDto> recoveryRecords(){
        var response = this.experienceAdapter.all();
        var listOfRecordsDtos = response
                .stream()
                .map(
                        record ->
                                this.experienceMapper.toDto(record))
                .collect(Collectors.toList());

        return listOfRecordsDtos;
    }

    public ExperienceDto recoveryRecord(UUID uuid){
        var response = this.experienceAdapter.recoveryByUuid(uuid);

        return this.experienceMapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        this.experienceAdapter.delete(uuid);
    }

}
