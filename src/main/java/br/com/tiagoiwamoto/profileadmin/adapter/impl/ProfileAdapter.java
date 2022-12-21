package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.core.domain.ProfileDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordNotFoundException;
import br.com.tiagoiwamoto.profileadmin.core.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileAdapter {

    private final ProfileRepository profileRepository;

    public List<ProfileDomain> all(){
        try{
            return this.profileRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ProfileDomain save(ProfileDomain profile){
        try{
            if(Objects.isNull(profile.getId())){
                profile.domainToSave();
            }else{
                var oldProfile = this.recoveryByUuid(profile.getUuid());
                profile.domainToUpdate(oldProfile);
            }

            if(profile.getIsActive()){
                List<ProfileDomain> profilesToUpdate = new ArrayList<>();
                this.profileRepository.findAll().forEach(profileDomain -> {
                    profileDomain.setIsActive(false);
                    profilesToUpdate.add(profileDomain);
                });
                this.profileRepository.saveAll(profilesToUpdate);
            }

            return this.profileRepository.save(profile);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ProfileDomain recoveryByUuid(UUID uuid){
        try{
            var optionalRecord = this.profileRepository.findByUuid(uuid);
            return optionalRecord.orElseThrow(() -> new RecordNotFoundException());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID uuid){
        try{
            var record = this.recoveryByUuid(uuid);
            this.profileRepository.delete(record);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
