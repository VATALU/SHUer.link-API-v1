package org.shuerlink.model.dto;

import org.shuerlink.model.entity.SHUHelperInfo;
import org.shuerlink.model.response.CollectionResponse;
import org.shuerlink.model.response.GeneralSettingResponse;
import org.shuerlink.model.response.NavigationResponse;
import org.shuerlink.model.response.SearchEngineResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
public class UserInfo {
    @Id
    protected String cardId;
    @Transient
    protected String avatar = "avatar_default.jpg";
    @Transient
    protected String name;
    @Transient
    protected String nickname;

    protected String changeWallpaperTime = "everyDay";
    protected boolean autoComplete = true;
    protected boolean autoChangeWallpaper = true;
    protected String defaultWikiLanguage = "zh";
    protected String defaultSearchEngine = "Google";
    protected String theme = "transparent";
    protected WallpaperCategory wallpaperCategory = new WallpaperCategory();
    protected String defaultBackgroundImage = "";
    protected List<CollectionResponse> collection = new ArrayList<>();
    protected List<NavigationResponse> navigation = new ArrayList<>();
    protected List<SearchEngineResponse> searchEngine = new ArrayList<>();
    @Transient
    protected String token;

    public UserInfo setBySHUhelperInfo(SHUHelperInfo shuHelperInfo) {
        name=shuHelperInfo.getName();
        nickname=shuHelperInfo.getNickname();
        avatar=shuHelperInfo.getAvatar();
        return this;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChangeWallpaperTime() {
        return changeWallpaperTime;
    }

    public void setChangeWallpaperTime(String changeWallpaperTime) {
        this.changeWallpaperTime = changeWallpaperTime;
    }

    public boolean isAutoComplete() {
        return autoComplete;
    }

    public void setAutoComplete(boolean autoComplete) {
        this.autoComplete = autoComplete;
    }

    public boolean isAutoChangeWallpaper() {
        return autoChangeWallpaper;
    }

    public void setAutoChangeWallpaper(boolean autoChangeWallpaper) {
        this.autoChangeWallpaper = autoChangeWallpaper;
    }

    public String getDefaultWikiLanguage() {
        return defaultWikiLanguage;
    }

    public void setDefaultWikiLanguage(String defaultWikiLanguage) {
        this.defaultWikiLanguage = defaultWikiLanguage;
    }

    public String getDefaultSearchEngine() {
        return defaultSearchEngine;
    }

    public void setDefaultSearchEngine(String defaultSearchEngine) {
        this.defaultSearchEngine = defaultSearchEngine;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public WallpaperCategory getWallpaperCategory() {
        return wallpaperCategory;
    }

    public void setWallpaperCategory(WallpaperCategory wallpaperCategory) {
        this.wallpaperCategory = wallpaperCategory;
    }

    public String getDefaultBackgroundImage() {
        return defaultBackgroundImage;
    }

    public void setDefaultBackgroundImage(String defaultBackgroundImage) {
        this.defaultBackgroundImage = defaultBackgroundImage;
    }

    public List<CollectionResponse> getCollection() {
        return collection;
    }

    public void setCollection(List<CollectionResponse> collection) {
        this.collection = collection;
    }

    public List<NavigationResponse> getNavigation() {
        return navigation;
    }

    public void setNavigation(List<NavigationResponse> navigation) {
        this.navigation = navigation;
    }

    public List<SearchEngineResponse> getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(List<SearchEngineResponse> searchEngine) {
        this.searchEngine = searchEngine;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
