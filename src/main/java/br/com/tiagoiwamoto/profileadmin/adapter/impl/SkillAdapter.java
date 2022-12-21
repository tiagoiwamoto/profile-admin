package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.core.domain.SkillDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
import br.com.tiagoiwamoto.profileadmin.core.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillAdapter{

    private final SkillRepository skillRepository;

    public List<SkillDomain> all(){
        try{
            return this.skillRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public SkillDomain save(SkillDomain resumeDomain){
        try{
            if(Objects.isNull(resumeDomain.getId())){
                resumeDomain.domainToSave();
            }else{
                var oldDomain = this.recoveryByUuid(resumeDomain.getUuid());
                resumeDomain.domainToUpdate(oldDomain);
            }
            return this.skillRepository.save(resumeDomain);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public SkillDomain recoveryByUuid(UUID uuid){
        try{
            var optionalRecord = this.skillRepository.findByUuid(uuid);
            return optionalRecord.orElseThrow(() -> new RecordNotFoundException());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID uuid){
        try{
            var record = this.recoveryByUuid(uuid);
            this.skillRepository.delete(record);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
