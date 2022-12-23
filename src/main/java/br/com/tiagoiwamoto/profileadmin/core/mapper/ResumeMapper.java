package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.ResumeDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ResumeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ResumeMapper implements IMapper<ResumeDomain, ResumeDto>{

    public ResumeDomain toDomain(ResumeDto resumeDto){
        ResumeDomain resumeDomain = new ResumeDomain();
        BeanUtils.copyProperties(resumeDto, resumeDomain);
        return resumeDomain;
    }

    public ResumeDto toDto(ResumeDomain resumeDomain){
        ResumeDto resumeDto = new ResumeDto();
        BeanUtils.copyProperties(resumeDomain, resumeDto);
        return resumeDto;
    }

}
