package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.core.domain.ResumeDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
import br.com.tiagoiwamoto.profileadmin.core.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResumeAdapter{

    private final ResumeRepository resumeRepository;

    public List<ResumeDomain> all(){
        try{
            return this.resumeRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ResumeDomain save(ResumeDomain resumeDomain){
        try{
            if(Objects.isNull(resumeDomain.getId())){
                resumeDomain.domainToSave();
            }else{
                var oldDomain = this.recoveryByUuid(resumeDomain.getUuid());
                resumeDomain.domainToUpdate(oldDomain);
            }
            return this.resumeRepository.save(resumeDomain);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ResumeDomain recoveryByUuid(UUID uuid){
        try{
            var optionalRecord = this.resumeRepository.findByUuid(uuid);
            return optionalRecord.orElseThrow(() -> new RecordNotFoundException());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID uuid){
        try{
            var record = this.recoveryByUuid(uuid);
            this.resumeRepository.delete(record);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
