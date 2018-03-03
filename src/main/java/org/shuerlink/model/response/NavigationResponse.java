package org.shuerlink.model.response;

import org.shuerlink.model.entity.Navigation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class NavigationResponse extends Navigation {
    private String navigationId;

    public NavigationResponse init(Navigation navigation) {
        title = navigation.getTitle();
        description = navigation.getDescription();
        url = navigation.getUrl();
        return this;
    }

    public String getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(String navigationId) {
        this.navigationId = navigationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NavigationResponse that = (NavigationResponse) o;

        return navigationId != null ? navigationId.equals(that.navigationId) : that.navigationId == null;
    }

    @Override
    public int hashCode() {
        return navigationId != null ? navigationId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NavigationResponse{" +
                "navigationId='" + navigationId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
