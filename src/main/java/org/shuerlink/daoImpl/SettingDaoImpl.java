package org.shuerlink.daoImpl;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import org.shuerlink.dao.SettingDao;
import org.shuerlink.model.dto.UserInfo;
import org.shuerlink.model.response.CollectionResponse;
import org.shuerlink.model.response.GeneralSettingResponse;
import org.shuerlink.model.response.NavigationResponse;
import org.shuerlink.model.response.SearchEngineResponse;
import org.shuerlink.model.dto.WallpaperCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SettingDaoImpl implements SettingDao {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public UserInfo findAllSettingsById(String cardId) {
        try {
            return mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId)), UserInfo.class);
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return null;
        }
    }

    @Override
    public GeneralSettingResponse findAllGeneralSettingsById(String cardId) {
        try {
            UserInfo userInfo = mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId)), UserInfo.class);
            if (userInfo != null) {
                return new GeneralSettingResponse().init(userInfo);
            }
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
        }
        return null;
    }

    @Override
    public int updateChangeWallpaperTime(String cardId, String changeWallpaperTime) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().set("changeWallpaperTime", changeWallpaperTime), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateAutoComplete(String cardId, boolean autoComplete) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().set("autoComplete", autoComplete), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateAutoChangeWallpaper(String cardId, boolean autoChangeWallpaper) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().set("autoChangeWallpaper", autoChangeWallpaper), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateDefaultWikiLanguage(String cardId, String defaultWikiLanguage) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().set("defaultWikiLanguage", defaultWikiLanguage), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateDefaultSearchEngine(String cardId, String defaultSearchEngine) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().set("defaultSearchInfo", defaultSearchEngine), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateTheme(String cardId, String theme) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().set("theme", theme), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateWallpaperCategory(String cardId, WallpaperCategory wallpaperCategory) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().set("wallpaperCategory", wallpaperCategory), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateDefaultBackgroundImage(String cardId, String defaultBackgroundImage) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().set("defaultBackgroundImage", defaultBackgroundImage), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public List<CollectionResponse> findAllCollections(String cardId, Integer page, Integer size) {
        try {
            UserInfo userInfo = mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId)), UserInfo.class);
            if (userInfo != null) {
                List<CollectionResponse> collectionResponseList = userInfo.getCollection();
                if (size < 0)
                    size = -size;
                if (page < 0)
                    page = -page;
                int start = page * size;
                int end = page * size + size;
                int collectionSize = collectionResponseList.size();
                if (collectionSize >= end) {
                    collectionResponseList = collectionResponseList.subList(start, end);
                } else if (collectionSize > start) {
                    collectionResponseList = collectionResponseList.subList(start, collectionSize);
                } else {
                    collectionResponseList = null;
                }
                return collectionResponseList;
            }
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
        }
        return null;
    }

    @Override
    public CollectionResponse findCollectionById(String cardId, String collectionId) {
        try {
            UserInfo userInfo = mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId).and("collection.collectionId").is(collectionId)), UserInfo.class);
            if (userInfo != null) {
                CollectionResponse collectionResponse = new CollectionResponse();
                collectionResponse.setCollectionId(collectionId);
                List<CollectionResponse> collectionResponseList = userInfo.getCollection();
                collectionResponse = collectionResponseList.get(collectionResponseList.indexOf(collectionResponse));
                return collectionResponse;
            }
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
        }
        return null;
    }

    @Override
    public int addCollection(String cardId, CollectionResponse collectionResponse) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().addToSet("collection", collectionResponse), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateCollection(String cardId, CollectionResponse collectionResponse) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId).and("collection.collectionId").is(collectionResponse.getCollectionId())),
                    new Update().set("collection.$", collectionResponse), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteCollectionById(String cardId, String collectionId) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId).and("collection.collectionId").is(collectionId)),
                    new Update().pull("collection", new BasicDBObject("collectionId", collectionId)), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public List<NavigationResponse> findAllNavigations(String cardId, Integer page, Integer size) {
        try {
            UserInfo userInfo = mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId)).skip(page * size).limit(page * size + size), UserInfo.class);
            if (userInfo != null) {
                List<NavigationResponse> navigationResponseList = userInfo.getNavigation();
                if (size < 0)
                    size = -size;
                if (page < 0)
                    page = -page;
                int start = page * size;
                int end = page * size + size;
                int navigationSize = navigationResponseList.size();
                if (navigationSize >= end) {
                    navigationResponseList = navigationResponseList.subList(start, end);
                } else if (navigationSize > start) {
                    navigationResponseList = navigationResponseList.subList(start, navigationSize);
                } else {
                    navigationResponseList = null;
                }
                return navigationResponseList;
            }
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
        }
        return null;
    }

    @Override
    public NavigationResponse findNavigationById(String cardId, String navigationId) {
        try {
            UserInfo userInfo = mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId)
                    .and("navigation.navigationId").is(navigationId)), UserInfo.class);
            if (userInfo != null) {
                List<NavigationResponse> navigationResponseList = userInfo.getNavigation();
                NavigationResponse navigationResponse = new NavigationResponse();
                navigationResponse.setNavigationId(navigationId);
                navigationResponse = navigationResponseList.get(navigationResponseList.indexOf(navigationResponse));
                return navigationResponse;
            }
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
        }
        return null;
    }

    @Override
    public int addNavigation(String cardId, NavigationResponse navigationResponse) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().addToSet("navigation", navigationResponse), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateNavigation(String cardId, NavigationResponse navigationResponse) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)
                            .and("navigation.navigationId").is(navigationResponse.getNavigationId())),
                    new Update().set("navigation.$", navigationResponse), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteNavigationById(String cardId, String navigationId) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)
                            .and("navigation.navigationId").is(navigationId)),
                    new Update().pull("navigation", new BasicDBObject("navigationId", navigationId)), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public List<SearchEngineResponse> findAllSearchEngines(String cardId, Integer page, Integer size) {
        try {
            UserInfo userInfo = mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId)).skip(page * size).limit(page * size + size), UserInfo.class);
            if (userInfo != null) {
                if (size < 0)
                    size = -size;
                if (page < 0)
                    page = -page;
                int start = page * size;
                int end = page * size + size;
                List<SearchEngineResponse> searchEngineResponseList = userInfo.getSearchEngine();
                int searchEngineSize = searchEngineResponseList.size();
                if (searchEngineSize >= end) {
                    searchEngineResponseList = searchEngineResponseList.subList(start, end);
                } else if (searchEngineSize > start) {
                    searchEngineResponseList = searchEngineResponseList.subList(start, searchEngineSize);
                } else {
                    searchEngineResponseList = null;
                }
                return searchEngineResponseList;
            }
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
        }
        return null;
    }

    @Override
    public SearchEngineResponse findSearchEngineById(String cardId, String searchEngineId) {
        try {
            UserInfo userInfo = mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId)
                    .and("searchEngine.searchEngineId").is(searchEngineId)), UserInfo.class);
            if (userInfo != null) {
                List<SearchEngineResponse> searchEngineResponseList = userInfo.getSearchEngine();
                SearchEngineResponse searchEngineResponse = new SearchEngineResponse();
                searchEngineResponse.setSearchEngineId(searchEngineId);
                return searchEngineResponseList.get(searchEngineResponseList.indexOf(searchEngineResponse));
            }
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
        }
        return null;
    }

    @Override
    public int addSearchEngine(String cardId, SearchEngineResponse searchEngineResponse) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)),
                    new Update().addToSet("searchEngine", searchEngineResponse), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateSearchEngine(String cardId, SearchEngineResponse searchEngineResponse) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)
                            .and("searchEngine.searchEngineId").is(searchEngineResponse.getSearchEngineId())),
                    new Update().set("searchEngine.$", searchEngineResponse), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteSearchEngineById(String cardId, String searchEngineId) {
        try {
            WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("cardId").is(cardId)
                            .and("searchEngine.searchEngineId").is(searchEngineId)),
                    new Update().pull("searchEngine", new BasicDBObject("searchEngineId", searchEngineId)), UserInfo.class);
            return writeResult.getN();
        } catch (Exception e) {
            LOGGER.error("{}->{}", cardId, e.getMessage());
        }
        return 0;
    }
}
