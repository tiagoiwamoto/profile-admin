package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CertificationAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.CertificationDomain;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CertificationMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CertificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CertificationUsecase implements IUsecaseWithFile<CertificationDto> {

    private final CertificationAdapter certificationAdapter;

    private final CertificationMapper certificationMapper;
    private final ImageAndThumbAdapter imageAndThumbAdapter;
    private final String PATH = "files/certifications/";

    public CertificationDto createOrUpdate(CertificationDto certificationDto, MultipartFile multipartFile){
        log.info("iniciando usecase de certification createOrUpdate");
        Path path;
        ImageDto imageDto;
        CertificationDomain certificationDomain;
        if(Objects.isNull(certificationDto.getId())){
            log.info("certification createOrUpdate -> será criado um novo registro");
            UUID scholarityUuid = UUID.randomUUID();
            path = Paths.get(PATH.concat(scholarityUuid.toString()));
            imageDto = this.imageAndThumbAdapter.storeImage(multipartFile, path);
            certificationDto.setUuid(scholarityUuid);
        }else{
            log.info("certification createOrUpdate -> será atualizado o registro");
            path = Paths.get(PATH.concat(certificationDto.getUuid().toString()));
            certificationDomain = this.certificationAdapter.recoveryByUuid(certificationDto.getUuid());
            certificationDomain.setPathOfImageThumb(certificationDomain.getPathOfImageThumb());
            certificationDomain.setPathOfImage(certificationDomain.getPathOfImage());
            certificationDto.setCreatedAt(certificationDomain.getCreatedAt());
            certificationDto.setUpdatedAt(certificationDomain.getUpdatedAt());
            imageDto = this.imageAndThumbAdapter.validUpdateOfImage(path, multipartFile, certificationDomain);
        }
        certificationDto.setPathOfImage(imageDto.getPathOfImage());
        certificationDto.setPathOfImageThumb(imageDto.getPathOfThumb());
        log.info("Convertendo certificationDto para certificationDomain");
        certificationDomain = this.certificationMapper.toDomain(certificationDto);
        certificationDomain.createOrUpdate();
        var response = this.certificationAdapter.save(certificationDomain);
        var certificationDtoConverted = this.certificationMapper.toDto(response);
        log.info("Convertendo certificationDomain para certificationDto");
        return certificationDtoConverted;

    }

    public List<CertificationDto> recoveryRecords(){
        log.info(String.format("recuperar registros "));
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
