package org.shuerlink.service;

import org.shuerlink.model.entity.Navigation;
import org.shuerlink.model.entity.SearchEngineCollection;
import org.shuerlink.model.entity.SearchEngineSource;
import org.shuerlink.model.response.CollectionResponse;
import org.shuerlink.model.response.GeneralSettingResponse;
import org.shuerlink.model.response.NavigationResponse;
import org.shuerlink.model.response.SearchEngineResponse;
import org.shuerlink.model.dto.WallpaperCategory;

import java.util.List;

public interface SettingService {
    public GeneralSettingResponse getGeneralSetting(Integer cardId);
    public String updateChangeWallpaperTime(Integer cardId, String changeWallpaperTime);
    public boolean updateAutoComplete(Integer cardId, boolean autoComplete);
    public boolean updateAutoChangeWallpaper(Integer cardId, boolean autoChangeWallpaper);
    public String updateDefaultWikiLanguage(Integer cardId, String defaultWikiLanguage);
    public String updateDefaultSearchEngine(Integer cardId, String defaultSearchEngine);
    public String updateTheme(Integer cardId, String theme);
    public WallpaperCategory updateWallpaperCategory(Integer cardId, WallpaperCategory wallpaperCategory);
    public String updateDefaultBackgroundImage(Integer cardId, String defaultBackgroundImage);

    public List<CollectionResponse> getCollections(Integer cardId, Integer page, Integer size);
    public CollectionResponse setCollection(Integer cardId, SearchEngineCollection searchEngineCollection);
    public CollectionResponse updateCollection(Integer cardId, String collectionId, SearchEngineCollection searchEngineCollection);
    public boolean deleteCollection(Integer cardId, String collectionId);

    public List<NavigationResponse> getNavigations(Integer cardId, Integer page, Integer size);
    public NavigationResponse setNavigation(Integer cardId, Navigation navigation);
    public NavigationResponse updateNavigation(Integer cardId, String navigationId, Navigation navigation);
    public boolean deleteNavigation(Integer cardId, String navigationId);

    public List<SearchEngineResponse> getSearchEngines(Integer cardId, Integer page, Integer size);
    public SearchEngineResponse setSearchEngine(Integer cardId, SearchEngineSource searchEngineSource);
    public SearchEngineResponse updateSearchEngine(Integer cardId, String searchEngineId, SearchEngineSource searchEngineSource);
    public boolean deleteSearchEngine(Integer cardId, String searchEngineId);

}
