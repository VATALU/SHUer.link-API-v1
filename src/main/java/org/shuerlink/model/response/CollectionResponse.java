package org.shuerlink.model.response;

import org.shuerlink.model.entity.SearchEngineCollection;

public class CollectionResponse extends SearchEngineCollection{
    private String collectionId;

    public CollectionResponse init(SearchEngineCollection searchEngineCollection) {
        collectionName=searchEngineCollection.getCollectionName();
        collectionDescription=searchEngineCollection.getCollectionDescription();
        defaultSearchInfo =searchEngineCollection.getDefaultSearchInfo();
        searchEngine=searchEngineCollection.getSearchEngine();
        return this;
    }
    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionResponse that = (CollectionResponse) o;

        return collectionId != null ? collectionId.equals(that.collectionId) : that.collectionId == null;
    }

    @Override
    public int hashCode() {
        return collectionId != null ? collectionId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CollectionResponse{" +
                "collectionId='" + collectionId + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", collectionDescription='" + collectionDescription + '\'' +
                ", defaultSearchInfo='" + defaultSearchInfo + '\'' +
                ", searchEngine=" + searchEngine +
                '}';
    }
}
