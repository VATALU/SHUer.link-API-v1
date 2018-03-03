package org.shuerlink.model.response;

import org.shuerlink.model.entity.SearchEngineSource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class SearchEngineResponse extends SearchEngineSource {
    private String searchEngineId;

    public SearchEngineResponse init(SearchEngineSource searchEngineSource) {
        name = searchEngineSource.getName();
        description = searchEngineSource.getDescription();
        url = searchEngineSource.getUrl();
        return this;
    }

    public String getSearchEngineId() {
        return searchEngineId;
    }

    public void setSearchEngineId(String searchEngineId) {
        this.searchEngineId = searchEngineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchEngineResponse that = (SearchEngineResponse) o;

        return searchEngineId != null ? searchEngineId.equals(that.searchEngineId) : that.searchEngineId == null;
    }

    @Override
    public int hashCode() {
        return searchEngineId != null ? searchEngineId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SearchEngineResponse{" +
                "searchEngineId='" + searchEngineId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
