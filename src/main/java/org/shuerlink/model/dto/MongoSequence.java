package org.shuerlink.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mongo_sequence")
public class MongoSequence {
    @Id
    private String id;
    private int collectionId ;
    private int navigationId;
    private int searchEngineId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(int navigationId) {
        this.navigationId = navigationId;
    }

    public int getSearchEngineId() {
        return searchEngineId;
    }

    public void setSearchEngineId(int searchEngineId) {
        this.searchEngineId = searchEngineId;
    }
}
