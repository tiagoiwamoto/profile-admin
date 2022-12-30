package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.ProfileDomain;
import br.com.tiagoiwamoto.profileadmin.core.exceptions.RecordSaveException;
import br.com.tiagoiwamoto.profileadmin.core.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProfileAdapter extends AbstractAdapter<ProfileDomain> {

    private ProfileRepository repository;
    private String domain;
    public ProfileAdapter(ProfileRepository repository) {
        super(repository, ProfileDomain.class.getSimpleName());
        this.repository = repository;
        this.domain = ProfileDomain.class.getSimpleName();
    }

    @Override
    public ProfileDomain save(ProfileDomain profile) {
        log.info(String.format("iniciando gravação do registro %s", domain));
        try {
//            if (Objects.isNull(profile.getId())) {
//                log.info(String.format("registro novo a ser gravado para o dominio %s", domain));
//                profile.domainToSave();
//            } else {
//                log.info(String.format("registro a ser atualizado para o dominio %s", domain));
//                var oldProfile = this.recoveryByUuid(profile.getUuid());
//                log.info(String.format("registro a ser atualizado encontrado para o dominio %s", domain));
//                profile.domainToUpdate(oldProfile);
//            }

            if (profile.getIsActive()) {
                log.info(String.format("validação de registro ativo para o dominio %s", domain));
                List<ProfileDomain> profilesToUpdate = new ArrayList<>();
                this.repository.findAll().forEach(profileDomain -> {
                    profileDomain.setIsActive(false);
                    profilesToUpdate.add(profileDomain);
                });
                this.repository.saveAll(profilesToUpdate);
            }

            var response = this.repository.save(profile);
            log.info(String.format("registro gravado para o dominio %s", domain));
            return response;
        } catch (Exception e) {
            log.error(
                    String.format(
                            "Falha ao gravar o dominio %s",
                            domain),
                    e);
            throw new RecordSaveException();
        }
    }

}
