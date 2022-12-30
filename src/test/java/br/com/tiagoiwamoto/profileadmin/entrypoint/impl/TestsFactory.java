package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.IAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.IMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.IResourceCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.IResourceWithFile;
import lombok.Data;

@Data
public class TestsFactory {

    private IUsecaseCreateUpdate usecase;
    private IUsecaseWithFile usecaseWithFile;
    private IResourceCreateUpdate resource;
    private IResourceWithFile resourceWithFile;
    private IAdapter iAdapter;
    private IMapper iMapper;
    private Class classToSerialize;
    private Class classToSerializeDomain;
    private String fileName;

    public TestsFactory(IUsecaseCreateUpdate usecase, IResourceCreateUpdate resource, String fileName, Class classToSerialize) {
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

    public TestsFactory(IUsecaseWithFile usecaseWithFile, IAdapter iAdapter, IMapper iMapper, String fileName, Class classToSerialize, Class classToSerializeDomain) {
        this.iAdapter = iAdapter;
        this.iMapper = iMapper;
        this.usecaseWithFile = usecaseWithFile;
        this.fileName = fileName;
        this.classToSerialize = classToSerialize;
        this.classToSerializeDomain = classToSerializeDomain;
    }

    public TestsFactory(IUsecaseCreateUpdate usecaseCreateUpdate, IAdapter iAdapter, IMapper iMapper, String fileName, Class classToSerialize, Class classToSerializeDomain) {
        this.iAdapter = iAdapter;
        this.iMapper = iMapper;
        this.usecase = usecaseCreateUpdate;
        this.fileName = fileName;
        this.classToSerialize = classToSerialize;
        this.classToSerializeDomain = classToSerializeDomain;
    }
}
