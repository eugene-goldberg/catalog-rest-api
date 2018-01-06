package sample.data.jest;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DatasetRepository extends ElasticsearchRepository<DatasetMetadata, String> {

//        public DatasetMetadata findByFirstName(String firstName);

    public DatasetMetadata findByDatasetName(String datasetName);

//        public List<DatasetMetadata> findByLastName(String lastName);

}
