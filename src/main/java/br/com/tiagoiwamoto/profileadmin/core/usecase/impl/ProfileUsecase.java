package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProfileAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProfileMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileUsecase implements IUsecaseCommon<ProfileDto> {

    private final ProfileAdapter profileAdapter;
    private final ProfileMapper profileMapper;

    public ProfileDto createOrUpdate(ProfileDto profile){
        var profileDomain = this.profileMapper.toProfileDomain(profile);
        var response = this.profileAdapter.save(profileDomain);
        return this.profileMapper.toProfileDto(response);
    }

    public List<ProfileDto> recoveryRecords(){
        var response = this.profileAdapter.all();
        var listOfProfilesDtos = response
                .stream()
                .map(
                        profile ->
                                this.profileMapper.toProfileDto(profile))
                .collect(Collectors.toList());

        return listOfProfilesDtos;
    }

    public ProfileDto recoveryRecord(UUID uuid){
        var response = this.profileAdapter.recoveryByUuid(uuid);

        return this.profileMapper.toProfileDto(response);
    }

    public void removeRecord(UUID uuid){
        this.profileAdapter.delete(uuid);
    }

}
