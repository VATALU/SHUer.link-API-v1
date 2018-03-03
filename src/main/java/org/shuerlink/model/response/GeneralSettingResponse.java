package org.shuerlink.model.response;

import org.shuerlink.model.dto.UserInfo;
import org.shuerlink.model.dto.WallpaperCategory;

public class GeneralSettingResponse {
    private String changeWallpaperTime = "everyDay";
    private boolean autoComplete = true;
    private boolean autoChangeWallpaper = true;
    private String defaultWikiLanguage = "zh";
    private String defaultSearchEngine = "Google";
    private String theme = "transparent";
    private WallpaperCategory wallpaperCategory = new WallpaperCategory();
    private String defaultBackgroundImage = "";

    public GeneralSettingResponse init(UserInfo userInfo){
        changeWallpaperTime=userInfo.getChangeWallpaperTime();
        autoChangeWallpaper=userInfo.isAutoChangeWallpaper();
        autoComplete=userInfo.isAutoComplete();
        defaultBackgroundImage=userInfo.getDefaultBackgroundImage();
        defaultSearchEngine=userInfo.getDefaultSearchEngine();
        defaultWikiLanguage=userInfo.getDefaultWikiLanguage();
        theme=userInfo.getTheme();
        wallpaperCategory=userInfo.getWallpaperCategory();
        return this;
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
}
