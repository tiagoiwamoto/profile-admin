package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseCategoryAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CourseCategoryMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class CourseCategoryUsecase extends AbstractUsecase implements IUsecaseCommon<AbstractDto> {

    public CourseCategoryUsecase(CourseCategoryAdapter adapter, CourseCategoryMapper mapper) {
        super(adapter, mapper);
    }

    //    private final CourseCategoryAdapter courseCategoryAdapter;
//    private final CourseCategoryMapper courseCategoryMapper;
//
//    public CourseCategoryDto createOrUpdate(CourseCategoryDto courseCategoryDto){
//        var resumeDomain = this.courseCategoryMapper.toDomain(courseCategoryDto);
//        if(Objects.isNull(courseCategoryDto.getId())){
//            resumeDomain.domainToSave();
//        }else{
//            var oldDomain = this.courseCategoryAdapter.recoveryByUuid(courseCategoryDto.getUuid());
//            resumeDomain.domainToUpdate(oldDomain);
//        }
//
//        var response = this.courseCategoryAdapter.save(resumeDomain);
//        return this.courseCategoryMapper.toDto(response);
//    }
//
//    public List<CourseCategoryDto> recoveryRecords(){
//        var response = this.courseCategoryAdapter.all();
//        var listOfRecordsDtos = response
//                .stream()
//                .map(
//                        record ->
//                                this.courseCategoryMapper.toDto(record))
//                .collect(Collectors.toList());
//
//        return listOfRecordsDtos;
//    }
//
//    public CourseCategoryDto recoveryRecord(UUID uuid){
//        var response = this.courseCategoryAdapter.recoveryByUuid(uuid);
//
//        return this.courseCategoryMapper.toDto(response);
//    }
//
//    public void removeRecord(UUID uuid){
//        var record = this.courseCategoryAdapter.recoveryByUuid(uuid);
//        this.courseCategoryAdapter.delete(uuid);
//    }

}
