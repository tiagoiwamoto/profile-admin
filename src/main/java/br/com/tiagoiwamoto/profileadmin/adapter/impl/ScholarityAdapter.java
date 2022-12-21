package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.core.domain.ScholarityDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
import br.com.tiagoiwamoto.profileadmin.core.repository.ScholarityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScholarityAdapter {

    private final ScholarityRepository scholarityRepository;

    public List<ScholarityDomain> all(){
        try{
            return this.scholarityRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ScholarityDomain save(ScholarityDomain scholarityDomain){
        try{
            if(Objects.isNull(scholarityDomain.getId())){
                scholarityDomain.domainToSave();
            }else{
                var oldDomain = this.recoveryByUuid(scholarityDomain.getUuid());
                scholarityDomain.domainToUpdate(oldDomain);
            }
            return this.scholarityRepository.save(scholarityDomain);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ScholarityDomain recoveryByUuid(UUID uuid){
        try{
            var optionalRecord = this.scholarityRepository.findByUuid(uuid);
            return optionalRecord.orElseThrow(() -> new RecordNotFoundException());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID uuid){
        try{
            var record = this.recoveryByUuid(uuid);
            this.scholarityRepository.delete(record);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
