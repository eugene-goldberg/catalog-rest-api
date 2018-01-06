package com.dfs.ace.catalog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class Loaders {
//    @Autowired
//    private DatasetRepository repository;

    @PostConstruct
    @Transactional
    public void loadAll(){
 //       DatasetRepository cr = repository;
//         this.repository.deleteAll();
//          saveDatasetMetadata();

    }

    private void saveDatasetMetadata() {
    }
}
