package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.IResource;
import br.com.tiagoiwamoto.profileadmin.entrypoint.IResourceWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import lombok.Data;

@Data
public class TestsFactory {

    private IUsecase usecase;
    private IUsecaseWithFile usecaseWithFile;
    private IResource resource;
    private IResourceWithFile resourceWithFile;

    private AbstractDtoWithImage response;
    private AbstractDtoWithImage request;
    private Class classToSerialize;
    private String fileName;

    public TestsFactory(IUsecase usecase, IResource resource, String fileName, Class classToSerialize) {
        this.usecase = usecase;
        this.resource = resource;
        this.fileName = fileName;
        this.classToSerialize = classToSerialize;
    }

    public TestsFactory(IUsecaseWithFile usecaseWithFile, IResourceWithFile resourceWithFile, String fileName, Class classToSerialize) {
        this.usecaseWithFile = usecaseWithFile;
        this.resourceWithFile = resourceWithFile;
        this.fileName = fileName;
        this.classToSerialize = classToSerialize;
    }
}