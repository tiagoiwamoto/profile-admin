package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.SoftwareAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.SoftwareDomain;
import br.com.tiagoiwamoto.profileadmin.core.mapper.SoftwareMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SoftwareDto;
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
public class SoftwareUsecase implements IUsecaseWithFile<SoftwareDto> {

    private final SoftwareAdapter softwareAdapter;

    private final SoftwareMapper softwareMapper;
    private final ImageAndThumbAdapter imageAndThumbAdapter;
    private final String PATH = "files/softwares/";

    public SoftwareDto createOrUpdate(SoftwareDto softwareDto, MultipartFile multipartFile){
        //TODO: Melhorar logs
        Path path;
        ImageDto imageDto;
        if(Objects.isNull(softwareDto.getId())){
            UUID recordUuid = UUID.randomUUID();
            path = Paths.get(PATH.concat(recordUuid.toString()));
            imageDto = this.imageAndThumbAdapter.storeImage(multipartFile, path);
            softwareDto.setUuid(recordUuid);
        }else{
            path = Paths.get(PATH.concat(softwareDto.getUuid().toString()));
            var softwareDomain = this.softwareAdapter.recoveryByUuid(softwareDto.getUuid());
            softwareDomain.setPathOfImageThumb(softwareDomain.getPathOfImageThumb());
            softwareDomain.setPathOfImage(softwareDomain.getPathOfImage());
            softwareDto.setCreatedAt(softwareDomain.getCreatedAt());
            softwareDto.setUpdatedAt(softwareDomain.getUpdatedAt());
            imageDto = this.imageAndThumbAdapter.validUpdateOfImage(path, multipartFile, softwareDomain);
        }
        softwareDto.setPathOfImage(imageDto.getPathOfImage());
        softwareDto.setPathOfImageThumb(imageDto.getPathOfThumb());
        var softwareDomain = this.softwareMapper.toDomain(softwareDto);
        softwareDomain.createOrUpdate();
        var response = this.softwareAdapter.save(softwareDomain);
        return this.softwareMapper.toDto(response);
    }

    public List<SoftwareDto> recoveryRecords(){
        var response = this.softwareAdapter.all();
        var listOfRecords = response
                .stream()
                .map(certification -> this.softwareMapper.toDto(certification))
                .collect(Collectors.toList());

        return listOfRecords;
    }

    public SoftwareDto recoveryRecord(UUID uuid){
        var response = this.softwareAdapter.recoveryByUuid(uuid);
        return this.softwareMapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        Path path = Paths.get(PATH.concat(uuid.toString()));
        var record = this.softwareAdapter.recoveryByUuid(uuid);
        this.softwareAdapter.delete(uuid);
        this.imageAndThumbAdapter.removeFiles(path, record.getPathOfImage(), record.getPathOfImageThumb());
    }

}
