package br.com.tiagoiwamoto.profileadmin.core.usecase;

import br.com.tiagoiwamoto.profileadmin.adapter.IAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.dto.ImageDto;
import br.com.tiagoiwamoto.profileadmin.core.domain.AbstractDomainWithImage;
import br.com.tiagoiwamoto.profileadmin.core.mapper.IMapper;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractImageUsecase implements IUsecaseWithFile<AbstractDtoWithImage>{

    private IAdapter<AbstractDomainWithImage> adapter;
    private IMapper<AbstractDomainWithImage, AbstractDtoWithImage> mapper;
    private ImageAndThumbAdapter imageAndThumbAdapter;
    private final String PATH;
    public AbstractImageUsecase(IAdapter adapter, IMapper mapper, ImageAndThumbAdapter imageAndThumbAdapter, String path) {
        this.adapter = adapter;
        this.mapper = mapper;
        this.imageAndThumbAdapter = imageAndThumbAdapter;
        this.PATH = path;
    }

    public AbstractDtoWithImage createOrUpdate(AbstractDtoWithImage dto, MultipartFile multipartFile){
        log.info("iniciando usecase de certification createOrUpdate");
        Path path;
        ImageDto imageDto;
        AbstractDomainWithImage abstractDomain;
        if(Objects.isNull(dto.getId())){
            log.info("certification createOrUpdate -> será criado um novo registro");
            UUID scholarityUuid = UUID.randomUUID();
            path = Paths.get(PATH.concat(scholarityUuid.toString()));
            imageDto = this.imageAndThumbAdapter.storeImage(multipartFile, path);
            dto.setUuid(scholarityUuid);
        }else{
            log.info("certification createOrUpdate -> será atualizado o registro");
            path = Paths.get(PATH.concat(dto.getUuid().toString()));
            abstractDomain = this.adapter.recoveryByUuid(dto.getUuid());
            abstractDomain.setPathOfImageThumb(abstractDomain.getPathOfImageThumb());
            abstractDomain.setPathOfImage(abstractDomain.getPathOfImage());
            dto.setCreatedAt(abstractDomain.getCreatedAt());
            dto.setUpdatedAt(abstractDomain.getUpdatedAt());
            imageDto = this.imageAndThumbAdapter.validUpdateOfImage(path, multipartFile, abstractDomain);
        }
        dto.setPathOfImage(imageDto.getPathOfImage());
        dto.setPathOfImageThumb(imageDto.getPathOfThumb());
        log.info("Convertendo certificationDto para certificationDomain");
        abstractDomain = this.mapper.toDomain(dto);
        abstractDomain.createOrUpdate();
        var response = this.adapter.save(abstractDomain);
        var certificationDtoConverted = this.mapper.toDto(response);
        log.info("Convertendo certificationDomain para certificationDto");
        return certificationDtoConverted;

    }


    public List<AbstractDtoWithImage> recoveryRecords(){
        var response = this.adapter.all();
        var listOfRecordsDtos = response
                .stream()
                .map(
                        record ->
                                this.mapper.toDto(record))
                .collect(Collectors.toList());

        return listOfRecordsDtos;
    }

    public AbstractDtoWithImage recoveryRecord(UUID uuid){
        var response = this.adapter.recoveryByUuid(uuid);

        return this.mapper.toDto(response);
    }

    public void removeRecord(UUID uuid){
        Path path = Paths.get(PATH.concat(uuid.toString()));
        var record = this.adapter.recoveryByUuid(uuid);
        this.adapter.delete(uuid);
        this.imageAndThumbAdapter.removeFiles(path, record.getPathOfImage(), record.getPathOfImageThumb());
    }
}
