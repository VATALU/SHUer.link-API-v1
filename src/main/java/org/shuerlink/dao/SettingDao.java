package org.shuerlink.dao;

import org.shuerlink.model.dto.UserInfo;
import org.shuerlink.model.response.CollectionResponse;
import org.shuerlink.model.response.GeneralSettingResponse;
import org.shuerlink.model.response.NavigationResponse;
import org.shuerlink.model.response.SearchEngineResponse;
import org.shuerlink.model.dto.WallpaperCategory;

import java.util.List;

public interface SettingDao {
    public UserInfo findAllSettingsById(String cardId);
    public GeneralSettingResponse findAllGeneralSettingsById(String cardId);
    public int updateChangeWallpaperTime(String cardId, String changeWallpaperTime);
    public int updateAutoComplete(String cardId, boolean autoComplete);
    public int updateAutoChangeWallpaper(String cardId, boolean autoChangeWallpaper);
    public int updateDefaultWikiLanguage(String cardId, String defaultWikiLanguage);
    public int updateDefaultSearchEngine(String cardId, String defaultSearchEngine);
    public int updateTheme(String cardId, String theme);
    public int updateWallpaperCategory(String cardId, WallpaperCategory wallpaperCategory);
    public int updateDefaultBackgroundImage(String cardId, String defaultBackgroundImage);

    public List<CollectionResponse> findAllCollections(String cardId, Integer page, Integer size);
    public CollectionResponse findCollectionById(String cardId, String collectionId);
    public int addCollection(String cardId, CollectionResponse collectionResponse);
    public int updateCollection(String cardId, CollectionResponse collectionResponse);
    public int deleteCollectionById(String cardId, String collectionId);

    public List<NavigationResponse> findAllNavigations(String cardId, Integer page, Integer size);
    public NavigationResponse findNavigationById(String cardId, String navigationId);
    public int addNavigation(String cardId,NavigationResponse navigationResponse);
    public int updateNavigation(String cardId, NavigationResponse navigationResponse);
    public int deleteNavigationById(String cardId, String navigationId);

    public List<SearchEngineResponse> findAllSearchEngines(String cardId, Integer page, Integer size);
    public SearchEngineResponse findSearchEngineById(String cardId, String searchEngineId);
    public int addSearchEngine(String cardId, SearchEngineResponse searchEngineResponse);
    public int updateSearchEngine(String cardId, SearchEngineResponse searchEngineResponse);
    public int deleteSearchEngineById(String cardId, String searchEngineId);
}
