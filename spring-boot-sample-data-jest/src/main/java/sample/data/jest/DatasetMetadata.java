package sample.data.jest;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "metadata", type = "dataset1", shards = 1, replicas = 0, refreshInterval = "-1")
public class DatasetMetadata {

    @Id
    private String id;
    private String datasetSource;
    private String datasetName;
    private String datasetDescription;
    private List<Attribute> datasetAttributes;

    public String getDatasetSource() {
        return this.datasetSource;
    }

    public void setDatasetSource(String datasetSource) {
        this.datasetSource = datasetSource;
    }

    public List<Attribute> getDatasetAttributes() {
        return this.datasetAttributes;
    }

    public void setDatasetAttributes(List<Attribute> datasetAttributes) {
        this.datasetAttributes = datasetAttributes;
    }

    public DatasetMetadata() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatasetName() {
        return this.datasetName;
    }

    public String getDatasetDescription() {
        return this.datasetDescription;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }


    public DatasetMetadata(String datasetId, String datasetName , String datasetDescription,
                           String datasetSource) {

        this.datasetSource = datasetSource;
        this.datasetName = datasetName;
        this.datasetDescription = datasetDescription;
//        this.datasetAttributes = attributes;
    }


    @Override
    public String toString() {
        return String.format("Dataset[id=%s, datasetName='%s']", this.id,
                this.datasetName);
    }
}
