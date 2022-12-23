package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProjectAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProjectMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class ProjectUsecase extends AbstractUsecase implements IUsecaseCommon<AbstractDto> {
    public ProjectUsecase(ProjectAdapter adapter, ProjectMapper mapper) {
        super(adapter, mapper);
    }

    //    private final ProjectAdapter projectAdapter;
//    private final ProjectMapper projectMapper;
//
//    public ProjectDto createOrUpdate(ProjectDto projectDto){
//        var resumeDomain = this.projectMapper.toDomain(projectDto);
//        if(Objects.isNull(projectDto.getId())){
//            resumeDomain.domainToSave();
//        }else{
//            var oldDomain = this.projectAdapter.recoveryByUuid(projectDto.getUuid());
//            resumeDomain.domainToUpdate(oldDomain);
//        }
//
//        var response = this.projectAdapter.save(resumeDomain);
//        return this.projectMapper.toDto(response);
//    }
//
//    public List<ProjectDto> recoveryRecords(){
//        var response = this.projectAdapter.all();
//        var listOfRecordsDtos = response
//                .stream()
//                .map(
//                        record ->
//                                this.projectMapper.toDto(record))
//                .collect(Collectors.toList());
//
//        return listOfRecordsDtos;
//    }
//
//    public ProjectDto recoveryRecord(UUID uuid){
//        var response = this.projectAdapter.recoveryByUuid(uuid);
//
//        return this.projectMapper.toDto(response);
//    }
//
//    public void removeRecord(UUID uuid){
//        this.projectAdapter.delete(uuid);
//    }

}
