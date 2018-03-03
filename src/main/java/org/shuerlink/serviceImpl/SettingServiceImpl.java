package org.shuerlink.serviceImpl;

import org.shuerlink.daoImpl.SettingDaoImpl;
import org.shuerlink.model.entity.Navigation;
import org.shuerlink.model.entity.SearchEngineCollection;
import org.shuerlink.model.entity.SearchEngineSource;
import org.shuerlink.model.response.CollectionResponse;
import org.shuerlink.model.response.GeneralSettingResponse;
import org.shuerlink.model.response.NavigationResponse;
import org.shuerlink.model.response.SearchEngineResponse;
import org.shuerlink.model.dto.WallpaperCategory;
import org.shuerlink.service.SettingService;
import org.shuerlink.util.MongoAutoidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SettingDaoImpl settingDao;
    @Resource
    private MongoAutoidUtil mongoAutoidUtil;

    @Override
    public GeneralSettingResponse getGeneralSetting(Integer cardId) {
        GeneralSettingResponse generalSettingResponse = settingDao.findAllGeneralSettingsById(cardId.toString());
        if (generalSettingResponse != null) {
            return generalSettingResponse;
        } else {
            LOGGER.error("{}->Method: getGeneralSetting, Error: 获取GeneralSetting失败", cardId);
            return null;
        }
    }

    @Override
    public String updateChangeWallpaperTime(Integer cardId, String changeWallpaperTime) {
        if (settingDao.updateChangeWallpaperTime(cardId.toString(), changeWallpaperTime) == 1) {
            return changeWallpaperTime;
        } else {
            LOGGER.error("{}->Method: updateChangeWallpaperTime, Error: 更新失败", cardId);
            return null;
        }
    }

    @Override
    public boolean updateAutoComplete(Integer cardId, boolean autoComplete) {
        if (settingDao.updateAutoComplete(cardId.toString(), autoComplete) == 1) {
            return autoComplete;
        } else {
            LOGGER.error("{}->Method: updateAutoComplete, Error: 更新失败", cardId);
            return false;
        }
    }

    @Override
    public boolean updateAutoChangeWallpaper(Integer cardId, boolean autoChangeWallpaper) {
        if (settingDao.updateAutoChangeWallpaper(cardId.toString(), autoChangeWallpaper) == 1) {
            return autoChangeWallpaper;
        } else {
            LOGGER.error("{}->Method: updateAutoChangeWallpaper, Error: 更新失败", cardId);
            return false;
        }
    }

    @Override
    public String updateDefaultWikiLanguage(Integer cardId, String defaultWikiLanguage) {
        if (settingDao.updateDefaultWikiLanguage(cardId.toString(), defaultWikiLanguage) == 1) {
            return defaultWikiLanguage;
        } else {
            LOGGER.error("{}->Method: updateDefaultWikiLanguage, Error: 更新失败", cardId);
            return null;
        }
    }

    @Override
    public String updateDefaultSearchEngine(Integer cardId, String defaultSearchEngine) {
        if (settingDao.updateDefaultSearchEngine(cardId.toString(), defaultSearchEngine) == 1) {
            return defaultSearchEngine;
        } else {
            LOGGER.error("{}->Method: updateDefaultSearchEngine, Error: 更新失败", cardId);
            return null;
        }
    }

    @Override
    public String updateTheme(Integer cardId, String theme) {
        if (settingDao.updateTheme(cardId.toString(), theme) == 1) {
            return theme;
        } else {
            LOGGER.error("{}->Method: updateTheme, Error: 更新失败", cardId);
            return null;
        }
    }

    @Override
    public WallpaperCategory updateWallpaperCategory(Integer cardId, WallpaperCategory wallpaperCategory) {
        if (settingDao.updateWallpaperCategory(cardId.toString(), wallpaperCategory) == 1) {
            return wallpaperCategory;
        } else {
            LOGGER.error("{}->Method: updateWallpaperCategory, Error: 更新失败", cardId);
            return null;
        }
    }

    @Override
    public String updateDefaultBackgroundImage(Integer cardId, String defaultBackgroundImage) {
        if (settingDao.updateDefaultBackgroundImage(cardId.toString(), defaultBackgroundImage) == 1) {
            return defaultBackgroundImage;
        } else {
            LOGGER.error("{}->Method: updateDefaultBackgroundImage, Error: 更新失败", cardId);
            return null;
        }
    }


    @Override
    public List<CollectionResponse> getCollections(Integer cardId, Integer page, Integer size) {
        List<CollectionResponse> collectionResponseList = settingDao.findAllCollections(cardId.toString(), page, size);
        if (collectionResponseList != null) {
            return collectionResponseList;
        } else {
            LOGGER.error("{}->Method: getCollections, Error: 获取Collections失败", cardId);
            return null;
        }
    }

    @Override
    public CollectionResponse setCollection(Integer cardId, SearchEngineCollection searchEngineCollection) {
        CollectionResponse collectionResponse = new CollectionResponse().init(searchEngineCollection);
        int id = mongoAutoidUtil.getNextCollectionId(cardId.toString());
        if (id != -1) {
            collectionResponse.setCollectionId(Integer.toString(id));
            if (settingDao.addCollection(cardId.toString(), collectionResponse) == 1) {
                return collectionResponse;
            } else {
                LOGGER.error("{}->Method: setCollection, Error: 添加collection失败", cardId);
            }
        } else {
            LOGGER.error("{}->Method: setCollection, Error: 获取collectionId失败", cardId);
        }
        return null;
    }

    @Override
    public CollectionResponse updateCollection(Integer cardId, String collectionId, SearchEngineCollection searchEngineCollection) {
        CollectionResponse collectionResponse = new CollectionResponse().init(searchEngineCollection);
        collectionResponse.setCollectionId(collectionId);
        if (settingDao.updateCollection(cardId.toString(), collectionResponse) == 1) {
            return collectionResponse;
        } else {
            LOGGER.error("{}->Method: updateCollection, Error: 更新collection:{}失败", cardId, collectionId);
        }
        return null;
    }

    @Override
    public boolean deleteCollection(Integer cardId, String collectionId) {
        if (settingDao.deleteCollectionById(cardId.toString(), collectionId) == 1) {
            return true;
        } else {
            LOGGER.error("{}->Method: deleteCollection, Error: 删除collection:{}失败", cardId, collectionId);
            return false;
        }
    }

    @Override
    public List<NavigationResponse> getNavigations(Integer cardId, Integer page, Integer size) {
        List<NavigationResponse> navigationResponseList = settingDao.findAllNavigations(cardId.toString(), page, size);
        if (navigationResponseList != null) {
            return navigationResponseList;
        } else {
            LOGGER.error("{}->Method: getNavigation, Error: 获取collection失败", cardId);
            return null;
        }
    }

    @Override
    public NavigationResponse setNavigation(Integer cardId, Navigation navigation) {
        NavigationResponse navigationResponse = new NavigationResponse().init(navigation);
        int id = mongoAutoidUtil.getNextNavigationId(cardId.toString());
        if (id != -1) {
            navigationResponse.setNavigationId(Integer.toString(id));
            if (settingDao.addNavigation(cardId.toString(), navigationResponse) == 1) {
                return navigationResponse;
            } else {
                LOGGER.error("{}->Method: setNavigation, Error: 添加Navigation失败", cardId);
            }
        } else {
            LOGGER.error("{}->Method: setNavigation, Error: 获取NavigationId失败", cardId);
        }
        return null;
    }

    @Override
    public NavigationResponse updateNavigation(Integer cardId, String navigationId, Navigation navigation) {
        NavigationResponse navigationResponse = new NavigationResponse().init(navigation);
        navigationResponse.setNavigationId(navigationId);
        if (settingDao.updateNavigation(cardId.toString(), navigationResponse) == 1) {
            return navigationResponse;
        } else {
            LOGGER.error("{}->Method: updateNavigation, Error: 更新Navigation:{}失败", cardId, navigationId);
        }
        return null;
    }

    @Override
    public boolean deleteNavigation(Integer cardId, String navigationId) {
        if (settingDao.deleteNavigationById(cardId.toString(), navigationId) == 1) {
            return true;
        } else {
            LOGGER.error("{}->Method: deleteNavigation, Error: 删除Navigation:{}失败", cardId, navigationId);
            return false;
        }
    }

    @Override
    public List<SearchEngineResponse> getSearchEngines(Integer cardId, Integer page, Integer size) {
        List<SearchEngineResponse> searchEngineResponseList = settingDao.findAllSearchEngines(cardId.toString(), page, size);
        if (searchEngineResponseList != null) {
            return searchEngineResponseList;
        } else {
            LOGGER.error("{}->Method: getSearchEngines, Error: 获取SearchEngines失败", cardId);
            return null;
        }
    }

    @Override
    public SearchEngineResponse setSearchEngine(Integer cardId, SearchEngineSource searchEngineSource) {
        SearchEngineResponse searchEngineResponse = new SearchEngineResponse().init(searchEngineSource);
        int id = mongoAutoidUtil.getNextSearchEngineId(cardId.toString());
        if (id != -1) {
            searchEngineResponse.setSearchEngineId(Integer.toString(id));
            if (settingDao.addSearchEngine(cardId.toString(), searchEngineResponse) == 1) {
                return searchEngineResponse;
            } else {
                LOGGER.error("{}->Method: setSearchEngine, Error: 添加searchengine失败", cardId);
            }
        } else {
            LOGGER.error("{}->Method: setSearchEngine, Error: 获取searchengineId失败", cardId);
        }
        return null;
    }

    @Override
    public SearchEngineResponse updateSearchEngine(Integer cardId, String searchEngineId, SearchEngineSource searchEngineSource) {
        SearchEngineResponse searchEngineResponse = new SearchEngineResponse().init(searchEngineSource);
        searchEngineResponse.setSearchEngineId(searchEngineId);
        if (settingDao.updateSearchEngine(cardId.toString(), searchEngineResponse) == 1) {
            return searchEngineResponse;
        } else {
            LOGGER.error("{}->Method: updateSearchEngine, Error: 更新searchengine:{}失败", cardId, searchEngineId);
        }
        return null;
    }

    @Override
    public boolean deleteSearchEngine(Integer cardId, String searchEngineId) {
        if (settingDao.deleteSearchEngineById(cardId.toString(), searchEngineId) == 1) {
            return true;
        } else {
            LOGGER.error("{}->Method: deleteSearchEngine, Error: 删除searchengine:{}失败", cardId, searchEngineId);
            return false;
        }
    }
}
