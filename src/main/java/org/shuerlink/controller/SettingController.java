package org.shuerlink.controller;

import org.shuerlink.model.entity.Navigation;
import org.shuerlink.model.entity.SearchEngineCollection;
import org.shuerlink.model.entity.SearchEngineSource;
import org.shuerlink.model.request.*;
import org.shuerlink.model.response.CollectionResponse;
import org.shuerlink.model.response.GeneralSettingResponse;
import org.shuerlink.model.response.NavigationResponse;
import org.shuerlink.model.response.SearchEngineResponse;
import org.shuerlink.model.dto.WallpaperCategory;
import org.shuerlink.serviceImpl.SettingServiceImpl;
import org.shuerlink.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/v1/setting")
public class SettingController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SettingServiceImpl settingService;


    @RequestMapping(value = "/{cardId}/generalsetting", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<GeneralSettingResponse> getGeneralsetting(@PathVariable("cardId") Integer cardId) {
        if (cardId != null) {
            GeneralSettingResponse generalSettingResponse = settingService.getGeneralSetting(cardId);
            if (generalSettingResponse != null) {
                //200
                return new ResponseEntity<>(generalSettingResponse, HttpStatus.OK);
            } else {
                //404
                LOGGER.error("{}->用户不存在", cardId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/changeWallpaperTime", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public ResponseEntity<ChangeWallpaperTime> updateChangeWallpaperTime(@PathVariable("cardId") Integer cardId, @RequestBody ChangeWallpaperTime changeWallpaperTime) {
        String param = changeWallpaperTime.getChangeWallpaperTime();
        if (param != null && cardId != null) {
            if (settingService.updateChangeWallpaperTime(cardId, param) != null) {
                //201
                return new ResponseEntity<>(changeWallpaperTime, HttpStatus.CREATED);
            } else {
                //404
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/autoComplete", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public ResponseEntity<AutoComplete> updateAutoComplete(@PathVariable("cardId") Integer cardId, @RequestBody AutoComplete autoComplete) {
        Boolean param = autoComplete.getAutoComplete();
        if (param != null && cardId != null) {
            if (settingService.updateAutoComplete(cardId, param)) {
                //201
                return new ResponseEntity<>(autoComplete, HttpStatus.CREATED);
            } else {
                //404
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/autoChangeWallpaper", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public ResponseEntity<AutoChangeWallpaper> updateAutoChangeWallpaper(@PathVariable("cardId") Integer cardId, @RequestBody AutoChangeWallpaper autoChangeWallpaper) {
        Boolean param = autoChangeWallpaper.getAutoChangeWallpaper();
        if (cardId != null && param != null) {
            if (settingService.updateAutoChangeWallpaper(cardId, param)) {
                //201
                return new ResponseEntity<>(autoChangeWallpaper, HttpStatus.CREATED);
            } else {
                //404
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/defaultWikiLanguage", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public ResponseEntity<DefaultWikiLanguage> updateDefaultWikiLanguage(@PathVariable("cardId") Integer cardId, @RequestBody DefaultWikiLanguage defaultWikiLanguage) {
        String param = defaultWikiLanguage.getDefaultWikiLanguage();
        if (cardId != null && param != null) {
            if (settingService.updateDefaultWikiLanguage(cardId, param) != null) {
                //201
                return new ResponseEntity<>(defaultWikiLanguage, HttpStatus.CREATED);
            } else {
                //404
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/defaultSearchEngine", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public ResponseEntity<DefaultSearchEngine> updateDefaultSearchEngine(@PathVariable("cardId") Integer cardId, @RequestBody DefaultSearchEngine defaultSearchEngine) {
        String param = defaultSearchEngine.getDefaultSearchEngine();
        if (cardId != null && param != null) {
            if (settingService.updateDefaultSearchEngine(cardId, param) != null) {
                //201
                return new ResponseEntity<>(defaultSearchEngine, HttpStatus.CREATED);
            } else {
                //404
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/theme", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public ResponseEntity<Theme> updateTheme(@PathVariable("cardId") Integer cardId, @RequestBody Theme theme) {
        String param = theme.getTheme();
        if (cardId != null && param != null) {
            if (settingService.updateTheme(cardId, param) != null) {
                //201
                return new ResponseEntity<>(theme, HttpStatus.CREATED);
            } else {
                //404
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/wallpaperCategory", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public ResponseEntity<WallpaperCategory> updateWallpaperCategory(@PathVariable("cardId") Integer cardId, @RequestBody WallpaperCategory wallpaperCategory) throws Exception {
        if (cardId != null && BeanUtil.isEveryFieldNotNull(wallpaperCategory)) {
            if (settingService.updateWallpaperCategory(cardId, wallpaperCategory) != null) {
                //201
                return new ResponseEntity<>(wallpaperCategory, HttpStatus.CREATED);
            } else {
                //404
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/defaultBackgroundImage", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public ResponseEntity<DefaultBackgroundImage> updateDefaultBackgroundImage(@PathVariable("cardId") Integer cardId, @RequestBody DefaultBackgroundImage defaultBackgroundImage) {
        if (cardId != null) {
            if (settingService.updateDefaultBackgroundImage(cardId, defaultBackgroundImage.getDefaultBackgroundImage()) != null) {
                //201
                return new ResponseEntity<>(defaultBackgroundImage, HttpStatus.CREATED);
            } else {
                //404
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cardId}/collection", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public ResponseEntity<List<CollectionResponse>> getCollections(@PathVariable("cardId") Integer cardId,
                                                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (cardId == null) {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CollectionResponse> collectionResponseList = settingService.getCollections(cardId, page, size);
        if (collectionResponseList == null) {
            //404
            LOGGER.error("{}->获取collection失败", cardId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //200
            LOGGER.info("{}->获取collection成功", cardId);
            return new ResponseEntity<>(collectionResponseList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{cardId}/collection", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseEntity<CollectionResponse> setCollection(@PathVariable("cardId") Integer cardId, @RequestBody SearchEngineCollection searchEngineCollection) throws Exception {
        if (cardId == null || !BeanUtil.isEveryFieldNotNull(searchEngineCollection)) {
            //400
            LOGGER.error("cardId为null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CollectionResponse collectionResponse = settingService.setCollection(cardId, searchEngineCollection);
        if (collectionResponse == null) {
            //404
            LOGGER.error("{}->创建collection失败", cardId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //201
            LOGGER.error("{}->创建collection:{}成功", cardId, collectionResponse.getCollectionId());
            return new ResponseEntity<>(collectionResponse, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{cardId}/collection/{collectionId}", produces = "application/json; charset=utf-8", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<CollectionResponse> updateCollection(@PathVariable("cardId") Integer cardId, @PathVariable("collectionId") String collectionId, @RequestBody SearchEngineCollection searchEngineCollection) throws Exception {
        System.out.println(searchEngineCollection.getDefaultSearchInfo());
        if (cardId == null || collectionId == null || collectionId.equals("") || !BeanUtil.isEveryFieldNotNull(searchEngineCollection)) {
            //400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CollectionResponse collectionResponse = settingService.updateCollection(cardId, collectionId, searchEngineCollection);
        if (collectionResponse == null) {
            //404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //201
            return new ResponseEntity<>(collectionResponse, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{cardId}/collection/{collectionId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCollection(@PathVariable("cardId") Integer cardId, @PathVariable("collectionId") String collectionId) {
        if (cardId == null || collectionId == null || collectionId.equals("")) {
            //400
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (!settingService.deleteCollection(cardId, collectionId)) {
            //404
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            //204
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{cardId}/navigation", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public ResponseEntity<List<NavigationResponse>> getNavigations(@PathVariable("cardId") Integer cardId,
                                                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (cardId == null) {
            //400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<NavigationResponse> navigationResponseList = settingService.getNavigations(cardId, page, size);
        if (navigationResponseList == null) {
            //404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //200
            return new ResponseEntity<>(navigationResponseList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{cardId}/navigation", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseEntity<NavigationResponse> setNavigation(@PathVariable("cardId") Integer cardId, @RequestBody Navigation navigation) throws Exception {
        if (cardId == null || !BeanUtil.isEveryFieldNotNull(navigation)) {
            //400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        NavigationResponse navigationResponse = settingService.setNavigation(cardId, navigation);
        if (navigationResponse == null) {
            //404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //201
            return new ResponseEntity<>(navigationResponse, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{cardId}/navigation/{navigationId}", produces = "application/json; charset=utf-8", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<NavigationResponse> updateNavigation(@PathVariable("cardId") Integer cardId, @PathVariable("navigationId") String navigationId, @RequestBody Navigation navigation) throws Exception {
        if (cardId == null || navigationId == null || navigationId.equals("") || !BeanUtil.isEveryFieldNotNull(navigation)) {
            //400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        NavigationResponse navigationResponse = settingService.updateNavigation(cardId, navigationId, navigation);
        if (navigationResponse == null) {
            //404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //201
            return new ResponseEntity<>(navigationResponse, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{cardId}/navigation/{navigationId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteNavigation(@PathVariable("cardId") Integer cardId, @PathVariable("navigationId") String navigationId) {
        if (cardId == null || navigationId == null || navigationId.equals("")) {
            //400
            return new ResponseEntity<NavigationResponse>(HttpStatus.BAD_REQUEST);
        }
        if (!settingService.deleteNavigation(cardId, navigationId)) {
            //404
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            //204
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{cardId}/searchengine", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public ResponseEntity<List<SearchEngineResponse>> getSearchEngines(@PathVariable("cardId") Integer cardId,
                                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (cardId == null) {
            //400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<SearchEngineResponse> searchEngineResponseList = settingService.getSearchEngines(cardId, page, size);
        if (searchEngineResponseList == null) {
            //404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //200
            return new ResponseEntity<>(searchEngineResponseList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{cardId}/searchengine", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseEntity<SearchEngineResponse> setSearchEngine(@PathVariable("cardId") Integer cardId,@RequestBody SearchEngineSource searchEngineSource) throws Exception {
        if (cardId == null || !BeanUtil.isEveryFieldNotNull(searchEngineSource)) {
            //400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SearchEngineResponse searchEngineResponse = settingService.setSearchEngine(cardId, searchEngineSource);
        if (searchEngineResponse == null) {
            //404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //201
            return new ResponseEntity<>(searchEngineResponse, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{cardId}/searchengine/{searchengineId}", produces = "application/json; charset=utf-8", method = RequestMethod.PUT)
    public ResponseEntity<SearchEngineResponse> updateSearchEngine(@PathVariable("cardId") Integer cardId, @PathVariable("searchengineId") String searchEngineId,@RequestBody SearchEngineSource searchEngineSource) throws Exception {
        if (cardId == null || searchEngineId == null || searchEngineId.equals("") || !BeanUtil.isEveryFieldNotNull(searchEngineSource)) {
            //400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SearchEngineResponse searchEngineResponse = settingService.updateSearchEngine(cardId, searchEngineId, searchEngineSource);
        if (searchEngineResponse == null) {
            //404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //201
            return new ResponseEntity<>(searchEngineResponse, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{cardId}/searchengine/{searchengineId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSearchEngine(@PathVariable("cardId") Integer cardId, @PathVariable("searchengineId") String searchEngineId) {
        if (cardId == null || searchEngineId == null || searchEngineId.equals("")) {
            //400
            return new ResponseEntity<SearchEngineResponse>(HttpStatus.BAD_REQUEST);
        }
        if (!settingService.deleteSearchEngine(cardId, searchEngineId)) {
            //404
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            //204
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
