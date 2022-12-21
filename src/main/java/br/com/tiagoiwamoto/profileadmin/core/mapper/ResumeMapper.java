package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.ResumeDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ResumeDto;
import org.springframework.beans.BeanUtils;

public class ResumeMapper {

    public ResumeDomain toResumeDomain(ResumeDto resumeDto){
        ResumeDomain resumeDomain = new ResumeDomain();
        BeanUtils.copyProperties(resumeDto, resumeDomain);
        return resumeDomain;
    }

    public ResumeDto toResumeDto(ResumeDomain resumeDomain){
        ResumeDto resumeDto = new ResumeDto();
        BeanUtils.copyProperties(resumeDomain, resumeDto);
        return resumeDto;
    }

}
