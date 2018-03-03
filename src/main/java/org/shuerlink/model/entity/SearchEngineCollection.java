package org.shuerlink.model.entity;

import org.shuerlink.model.response.SearchEngineResponse;

import java.util.List;

public class SearchEngineCollection {
    protected String collectionName;
    protected String collectionDescription;
    protected String defaultSearchInfo;
    protected List<SearchEngineResponse> searchEngine;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionDescription() {
        return collectionDescription;
    }

    public void setCollectionDescription(String collectionDescription) {
        this.collectionDescription = collectionDescription;
    }

    public String getDefaultSearchInfo() {
        return defaultSearchInfo;
    }

    public void setDefaultSearchInfo(String defaultSearchInfo) {
        this.defaultSearchInfo = defaultSearchInfo;
    }

    public List<SearchEngineResponse> getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(List<SearchEngineResponse> searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public String toString() {
        return "SearchEngineCollection{" +
                "collectionName='" + collectionName + '\'' +
                ", collectionDescription='" + collectionDescription + '\'' +
                ", defaultSearchInfo='" + defaultSearchInfo + '\'' +
                ", searchEngine=" + searchEngine +
                '}';
    }
}
