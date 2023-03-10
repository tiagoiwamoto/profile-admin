package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.SoftwareDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SoftwareDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SoftwareMapper implements IMapper<SoftwareDomain, SoftwareDto>{

    public SoftwareDomain toDomain(SoftwareDto softwareDto){
        SoftwareDomain softwareDomain = new SoftwareDomain();
        BeanUtils.copyProperties(softwareDto, softwareDomain);
        return softwareDomain;
    }

    public SoftwareDto toDto(SoftwareDomain certificationDomain){
        SoftwareDto softwareDto = new SoftwareDto();
        BeanUtils.copyProperties(certificationDomain, softwareDto);
        return softwareDto;
    }

}
