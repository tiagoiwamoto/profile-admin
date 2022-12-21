package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ScholarityAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.ScholarityDomain;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ScholarityMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ScholarityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScholarityUsecase implements IUsecaseWithFile<ScholarityDto> {

    private final ScholarityAdapter scholarityAdapter;

    private final ScholarityMapper scholarityMapper;
    private final ImageAndThumbAdapter imageAndThumbAdapter;
    private final String PATH = "files/scholarity/";

    public ScholarityDto createOrUpdate(ScholarityDto scholarityDto, MultipartFile multipartFile){
        //TODO: Melhorar logs
        Path path;
        ImageDto imageDto;
        if(Objects.isNull(scholarityDto.getId())){
            UUID scholarityUuid = UUID.randomUUID();
            path = Paths.get(PATH.concat(scholarityUuid.toString()));
            imageDto = this.imageAndThumbAdapter.storeImage(multipartFile, path);
            scholarityDto.setUuid(scholarityUuid);
        }else{
            path = Paths.get(PATH.concat(scholarityDto.getUuid().toString()));
            var scholarityDomain = this.scholarityAdapter.recoveryByUuid(scholarityDto.getUuid());
            scholarityDomain.setPathOfImageThumb(scholarityDomain.getPathOfImageThumb());
            scholarityDomain.setPathOfImage(scholarityDomain.getPathOfImage());
            scholarityDto.setCreatedAt(scholarityDomain.getCreatedAt());
            scholarityDto.setUpdatedAt(scholarityDomain.getUpdatedAt());
            imageDto = this.imageAndThumbAdapter.validUpdateOfImage(path, multipartFile, scholarityDomain);
        }
        scholarityDto.setPathOfImage(imageDto.getPathOfImage());
        scholarityDto.setPathOfImageThumb(imageDto.getPathOfThumb());
        var scholarityDomain = this.scholarityMapper.toProfileDomain(scholarityDto);
        scholarityDomain.createOrUpdate();
        var response = this.scholarityAdapter.save(scholarityDomain);
        return this.scholarityMapper.toProfileDto(response);
    }

    public List<ScholarityDto> recoveryRecords(){
        var response = this.scholarityAdapter.all();
        var listOfScholarities = response
                .stream()
                .map(scholarity -> this.scholarityMapper.toProfileDto(scholarity))
                .collect(Collectors.toList());

        return listOfScholarities;
    }

    public ScholarityDto recoveryRecord(UUID uuid){
        var response = this.scholarityAdapter.recoveryByUuid(uuid);
        return this.scholarityMapper.toProfileDto(response);
    }

    public void removeRecord(UUID uuid){
        Path path = Paths.get(PATH.concat(uuid.toString()));
        var scholarity = this.scholarityAdapter.recoveryByUuid(uuid);
        this.scholarityAdapter.delete(uuid);
        this.imageAndThumbAdapter.removeFiles(path, scholarity.getPathOfImage(), scholarity.getPathOfImageThumb());
    }

}
