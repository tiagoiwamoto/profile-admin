package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.core.domain.SoftwareDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
import br.com.tiagoiwamoto.profileadmin.core.repository.SoftwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SoftwareAdapter  {

    private final SoftwareRepository softwareRepository;

    public List<SoftwareDomain> all(){
        try{
            return this.softwareRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public SoftwareDomain save(SoftwareDomain certificationDomain){
        try{
            if(Objects.isNull(certificationDomain.getId())){
                certificationDomain.domainToSave();
            }else{
                var oldDomain = this.recoveryByUuid(certificationDomain.getUuid());
                certificationDomain.domainToUpdate(oldDomain);
            }
            return this.softwareRepository.save(certificationDomain);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public SoftwareDomain recoveryByUuid(UUID uuid){
        try{
            var optionalRecord = this.softwareRepository.findByUuid(uuid);
            return optionalRecord.orElseThrow(() -> new RecordNotFoundException());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID uuid){
        try{
            var record = this.recoveryByUuid(uuid);
            this.softwareRepository.delete(record);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
