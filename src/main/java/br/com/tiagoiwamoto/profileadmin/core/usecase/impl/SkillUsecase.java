package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.SkillAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.SkillMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class SkillUsecase extends AbstractUsecase implements IUsecaseCommon<AbstractDto> {

    public SkillUsecase(SkillAdapter adapter, SkillMapper mapper) {
        super(adapter, mapper);
    }

    //    private final SkillAdapter skillAdapter;
//    private final SkillMapper skillMapper;
//
//    public SkillDto createOrUpdate(SkillDto resumeDto){
//        var resumeDomain = this.skillMapper.toDomain(resumeDto);
//        if(Objects.isNull(resumeDto.getId())){
//            resumeDomain.domainToSave();
//        }else{
//            var oldDomain = this.skillAdapter.recoveryByUuid(resumeDto.getUuid());
//            resumeDomain.domainToUpdate(oldDomain);
//        }
//
//        var response = this.skillAdapter.save(resumeDomain);
//        return this.skillMapper.toDto(response);
//    }
//
//    public List<SkillDto> recoveryRecords(){
//        var response = this.skillAdapter.all();
//        var listOfRecordsDtos = response
//                .stream()
//                .map(
//                        record ->
//                                this.skillMapper.toDto(record))
//                .collect(Collectors.toList());
//
//        return listOfRecordsDtos;
//    }
//
//    public SkillDto recoveryRecord(UUID uuid){
//        var response = this.skillAdapter.recoveryByUuid(uuid);
//
//        return this.skillMapper.toDto(response);
//    }
//
//    public void removeRecord(UUID uuid){
//        var record = this.skillAdapter.recoveryByUuid(uuid);
//        this.skillAdapter.delete(uuid);
//    }

}
