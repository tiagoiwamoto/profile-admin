package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ResumeAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ResumeMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ResumeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeUsecase implements IUsecaseCommon<ResumeDto> {

    private final ResumeAdapter resumeAdapter;
    private final ResumeMapper resumeMapper;

    public ResumeDto createOrUpdate(ResumeDto resumeDto){
        var resumeDomain = this.resumeMapper.toDomain(resumeDto);
        if(Objects.isNull(resumeDto.getId())){
            resumeDomain.domainToSave();
        }else{
            var oldDomain = this.resumeAdapter.recoveryByUuid(resumeDto.getUuid());
            resumeDomain.domainToUpdate(oldDomain);
        }

        var response = this.resumeAdapter.save(resumeDomain);
        return this.resumeMapper.toDto(response);
    }

    public List<ResumeDto> recoveryRecords(){
        var response = this.resumeAdapter.all();
        var listOfRecordsDtos = response
                .stream()
                .map(
                        record ->
                                this.resumeMapper.toDto(record))
                .collect(Collectors.toList());

        return listOfRecordsDtos;
    }

    public ResumeDto recoveryRecord(UUID uuid){
        var response = this.resumeAdapter.recoveryByUuid(uuid);

        return this.resumeMapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        this.resumeAdapter.delete(uuid);
    }

}
