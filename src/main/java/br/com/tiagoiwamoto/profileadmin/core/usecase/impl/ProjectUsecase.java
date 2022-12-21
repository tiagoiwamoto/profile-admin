package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProjectAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProjectMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectUsecase implements IUsecaseCommon<ProjectDto> {

    private final ProjectAdapter projectAdapter;
    private final ProjectMapper projectMapper;

    public ProjectDto createOrUpdate(ProjectDto projectDto){
        var resumeDomain = this.projectMapper.toDomain(projectDto);
        if(Objects.isNull(projectDto.getId())){
            resumeDomain.domainToSave();
        }else{
            var oldDomain = this.projectAdapter.recoveryByUuid(projectDto.getUuid());
            resumeDomain.domainToUpdate(oldDomain);
        }

        var response = this.projectAdapter.save(resumeDomain);
        return this.projectMapper.toDto(response);
    }

    public List<ProjectDto> recoveryRecords(){
        var response = this.projectAdapter.all();
        var listOfRecordsDtos = response
                .stream()
                .map(
                        record ->
                                this.projectMapper.toDto(record))
                .collect(Collectors.toList());

        return listOfRecordsDtos;
    }

    public ProjectDto recoveryRecord(UUID uuid){
        var response = this.projectAdapter.recoveryByUuid(uuid);

        return this.projectMapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        this.projectAdapter.delete(uuid);
    }

}
