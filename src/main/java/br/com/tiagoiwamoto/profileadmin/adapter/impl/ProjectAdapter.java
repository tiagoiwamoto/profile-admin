package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.core.domain.ProjectDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
import br.com.tiagoiwamoto.profileadmin.core.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectAdapter  {

    private final ProjectRepository projectRepository;

    public List<ProjectDomain> all(){
        try{
            return this.projectRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ProjectDomain save(ProjectDomain projectDomain){
        try{
            if(Objects.isNull(projectDomain.getId())){
                projectDomain.domainToSave();
            }else{
                var oldDomain = this.recoveryByUuid(projectDomain.getUuid());
                projectDomain.domainToUpdate(oldDomain);
            }
            return this.projectRepository.save(projectDomain);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ProjectDomain recoveryByUuid(UUID uuid){
        try{
            var optionalRecord = this.projectRepository.findByUuid(uuid);
            return optionalRecord.orElseThrow(() -> new RecordNotFoundException());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID uuid){
        try{
            var record = this.recoveryByUuid(uuid);
            this.projectRepository.delete(record);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
