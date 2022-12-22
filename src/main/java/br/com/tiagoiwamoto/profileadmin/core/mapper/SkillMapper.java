package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.SkillDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SkillDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    public SkillDomain toDomain(SkillDto skillDto){
        SkillDomain skillDomain = new SkillDomain();
        BeanUtils.copyProperties(skillDto, skillDomain);
        return skillDomain;
    }

    public SkillDto toDto(SkillDomain skillDomain){
        SkillDto skillDto = new SkillDto();
        BeanUtils.copyProperties(skillDomain, skillDto);
        return skillDto;
    }

}
