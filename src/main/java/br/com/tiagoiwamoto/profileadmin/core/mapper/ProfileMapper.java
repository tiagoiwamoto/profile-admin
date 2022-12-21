package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.ProfileDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProfileDto;
import org.springframework.beans.BeanUtils;

public class ProfileMapper {

    public ProfileDomain toProfileDomain(ProfileDto profileDto){
        ProfileDomain profileDomain = new ProfileDomain();
        BeanUtils.copyProperties(profileDto, profileDomain);
        return profileDomain;
    }

    public ProfileDto toProfileDto(ProfileDomain profileDomain){
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(profileDomain, profileDto);
        return profileDto;
    }

}
