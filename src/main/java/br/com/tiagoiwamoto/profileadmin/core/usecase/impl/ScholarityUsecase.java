package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ScholarityAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ScholarityMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractImageUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import org.springframework.stereotype.Service;

@Service
public class ScholarityUsecase extends AbstractImageUsecase implements IUsecaseWithFile<AbstractDtoWithImage> {
    public ScholarityUsecase(ScholarityAdapter adapter, ScholarityMapper mapper, ImageAndThumbAdapter imageAndThumbAdapter) {
        super(adapter, mapper, imageAndThumbAdapter, "files/scholarity/");
    }

    //    private final ScholarityAdapter scholarityAdapter;
//
//    private final ScholarityMapper scholarityMapper;
//    private final ImageAndThumbAdapter imageAndThumbAdapter;
//    private final String PATH = "files/scholarity/";
//
//    public ScholarityDto createOrUpdate(ScholarityDto scholarityDto, MultipartFile multipartFile){
//        //TODO: Melhorar logs
//        Path path;
//        ImageDto imageDto;
//        if(Objects.isNull(scholarityDto.getId())){
//            UUID scholarityUuid = UUID.randomUUID();
//            path = Paths.get(PATH.concat(scholarityUuid.toString()));
//            imageDto = this.imageAndThumbAdapter.storeImage(multipartFile, path);
//            scholarityDto.setUuid(scholarityUuid);
//        }else{
//            path = Paths.get(PATH.concat(scholarityDto.getUuid().toString()));
//            var scholarityDomain = this.scholarityAdapter.recoveryByUuid(scholarityDto.getUuid());
//            scholarityDomain.setPathOfImageThumb(scholarityDomain.getPathOfImageThumb());
//            scholarityDomain.setPathOfImage(scholarityDomain.getPathOfImage());
//            scholarityDto.setCreatedAt(scholarityDomain.getCreatedAt());
//            scholarityDto.setUpdatedAt(scholarityDomain.getUpdatedAt());
//            imageDto = this.imageAndThumbAdapter.validUpdateOfImage(path, multipartFile, scholarityDomain);
//        }
//        scholarityDto.setPathOfImage(imageDto.getPathOfImage());
//        scholarityDto.setPathOfImageThumb(imageDto.getPathOfThumb());
//        var scholarityDomain = this.scholarityMapper.toDomain(scholarityDto);
//        scholarityDomain.createOrUpdate();
//        var response = this.scholarityAdapter.save(scholarityDomain);
//        return this.scholarityMapper.toDto(response);
//    }
//
//    public List<ScholarityDto> recoveryRecords(){
//        var response = this.scholarityAdapter.all();
//        var listOfScholarities = response
//                .stream()
//                .map(scholarity -> this.scholarityMapper.toDto(scholarity))
//                .collect(Collectors.toList());
//
//        return listOfScholarities;
//    }
//
//    public ScholarityDto recoveryRecord(UUID uuid){
//        var response = this.scholarityAdapter.recoveryByUuid(uuid);
//        return this.scholarityMapper.toDto(response);
//    }
//
//    public void removeRecord(UUID uuid){
//        Path path = Paths.get(PATH.concat(uuid.toString()));
//        var scholarity = this.scholarityAdapter.recoveryByUuid(uuid);
//        this.scholarityAdapter.delete(uuid);
//        this.imageAndThumbAdapter.removeFiles(path, scholarity.getPathOfImage(), scholarity.getPathOfImageThumb());
//    }

}
