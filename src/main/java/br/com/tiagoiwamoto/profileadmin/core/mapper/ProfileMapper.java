package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.ProfileDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProfileDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper implements IMapper<ProfileDomain, ProfileDto>{

    public ProfileDomain toDomain(ProfileDto profileDto){
        ProfileDomain profileDomain = new ProfileDomain();
        BeanUtils.copyProperties(profileDto, profileDomain);
        return profileDomain;
    }

    public ProfileDto toDto(ProfileDomain profileDomain){
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(profileDomain, profileDto);
        return profileDto;
    }

}
