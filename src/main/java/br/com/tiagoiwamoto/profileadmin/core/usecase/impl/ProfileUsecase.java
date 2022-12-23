package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProfileAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProfileMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class ProfileUsecase extends AbstractUsecase implements IUsecaseCommon<AbstractDto> {

    public ProfileUsecase(ProfileAdapter adapter, ProfileMapper mapper) {
        super(adapter, mapper);
    }
    //    private final ProfileAdapter profileAdapter;
//    private final ProfileMapper profileMapper;
//
//    public ProfileDto createOrUpdate(ProfileDto profile){
//        var profileDomain = this.profileMapper.toDomain(profile);
//        var response = this.profileAdapter.save(profileDomain);
//        return this.profileMapper.toDto(response);
//    }
//
//    public List<ProfileDto> recoveryRecords(){
//        var response = this.profileAdapter.all();
//        var listOfProfilesDtos = response
//                .stream()
//                .map(
//                        profile ->
//                                this.profileMapper.toDto(profile))
//                .collect(Collectors.toList());
//
//        return listOfProfilesDtos;
//    }
//
//    public ProfileDto recoveryRecord(UUID uuid){
//        var response = this.profileAdapter.recoveryByUuid(uuid);
//
//        return this.profileMapper.toDto(response);
//    }
//
//    public void removeRecord(UUID uuid){
//        this.profileAdapter.delete(uuid);
//    }

}
