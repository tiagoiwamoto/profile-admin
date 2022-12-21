package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.CertificationDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CertificationDto;
import org.springframework.beans.BeanUtils;

public class CertificationMapper {

    public CertificationDomain toDomain(CertificationDto certificationDto){
        CertificationDomain certificationDomain = new CertificationDomain();
        BeanUtils.copyProperties(certificationDto, certificationDomain);
        return certificationDomain;
    }

    public CertificationDto toDto(CertificationDomain certificationDomain){
        CertificationDto certificationDto = new CertificationDto();
        BeanUtils.copyProperties(certificationDomain, certificationDto);
        return certificationDto;
    }

}
