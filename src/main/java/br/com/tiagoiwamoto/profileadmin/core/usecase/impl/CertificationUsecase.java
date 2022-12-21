package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CertificationAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.CertificationDomain;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CertificationMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CertificationDto;
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
public class CertificationUsecase implements IUsecaseWithFile<CertificationDto> {

    private final CertificationAdapter certificationAdapter;

    private final CertificationMapper certificationMapper;
    private final ImageAndThumbAdapter imageAndThumbAdapter;
    private final String PATH = "files/certifications/";

    public CertificationDto createOrUpdate(CertificationDto certificationDto, MultipartFile multipartFile){
        //TODO: Melhorar logs
        Path path;
        ImageDto imageDto;
        CertificationDomain certificationDomain;
        if(Objects.isNull(certificationDto.getId())){
            UUID scholarityUuid = UUID.randomUUID();
            path = Paths.get(PATH.concat(scholarityUuid.toString()));
            imageDto = this.imageAndThumbAdapter.storeImage(multipartFile, path);
            certificationDto.setUuid(scholarityUuid);
        }else{
            path = Paths.get(PATH.concat(certificationDto.getUuid().toString()));
            certificationDomain = this.certificationAdapter.recoveryByUuid(certificationDto.getUuid());
            certificationDomain.setPathOfImageThumb(certificationDomain.getPathOfImageThumb());
            certificationDomain.setPathOfImage(certificationDomain.getPathOfImage());
            certificationDto.setCreatedAt(certificationDomain.getCreatedAt());
            certificationDto.setUpdatedAt(certificationDomain.getUpdatedAt());
            if(!Objects.isNull(multipartFile)){
                imageDto = this.imageAndThumbAdapter.replaceImage(
                        multipartFile,
                        path,
                        certificationDomain.getPathOfImage(),
                        certificationDomain.getPathOfImageThumb()
                );
            }else{
                imageDto = new ImageDto(certificationDomain.getPathOfImage(), certificationDomain.getPathOfImageThumb());
            }
        }
        certificationDto.setPathOfImage(imageDto.getPathOfImage());
        certificationDto.setPathOfImageThumb(imageDto.getPathOfThumb());
        certificationDomain = this.certificationMapper.toDomain(certificationDto);
        certificationDomain.createOrUpdate();
        var response = this.certificationAdapter.save(certificationDomain);
        return this.certificationMapper.toDto(response);
    }

    public List<CertificationDto> recoveryRecords(){
        var response = this.certificationAdapter.all();
        var listOfScholarities = response
                .stream()
                .map(certification -> this.certificationMapper.toDto(certification))
                .collect(Collectors.toList());

        return listOfScholarities;
    }

    public CertificationDto recoveryRecord(UUID uuid){
        var response = this.certificationAdapter.recoveryByUuid(uuid);
        return this.certificationMapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        Path path = Paths.get(PATH.concat(uuid.toString()));
        var scholarity = this.certificationAdapter.recoveryByUuid(uuid);
        this.certificationAdapter.delete(uuid);
        this.imageAndThumbAdapter.removeFiles(path, scholarity.getPathOfImage(), scholarity.getPathOfImageThumb());
    }

}
