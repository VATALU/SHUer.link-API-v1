# SHUer.link-API-v1
    API文档 shuer.link_api_v1.yaml， 用[Swagger Editor](https://editor.swagger.io/)预览。
## Dependency
> Spring MVC 4.3.11.RELEASE

> Sping 4.3.11.RELEASE

> Spring-data-mongodb 1.8.4.RELEASE

## 目录结构

```
    project
    │  pom.xml
    │  README.md
    │  shuer.link_api_v1.yaml
    │  
    └─src
        └─main
            ├─java
            │  └─org
            │      └─shuerlink
            │          ├─client
            │          │      ServiceClient.java
            │          │      SHUHelperClient.java
            │          │      
            │          ├─controller
            │          │      SettingController.java
            │          │      UserController.java
            │          │      
            │          ├─dao
            │          │      SettingDao.java
            │          │      UserDao.java
            │          │      
            │          ├─daoImpl
            │          │      SettingDaoImpl.java
            │          │      UserDaoImpl.java
            │          │      
            │          ├─filter
            │          │      JWTCheckInterceptor.java
            │          │      
            │          ├─model
            │          │  ├─contains
            │          │  ├─dto
            │          │  │      MongoSequence.java
            │          │  │      UserInfo.java
            │          │  │      WallpaperCategory.java
            │          │  │      
            │          │  ├─entity
            │          │  │      JWTInfo.java
            │          │  │      Navigation.java
            │          │  │      SearchEngineCollection.java
            │          │  │      SearchEngineSource.java
            │          │  │      SHUHelperInfo.java
            │          │  │      
            │          │  ├─request
            │          │  │      AutoChangeWallpaper.java
            │          │  │      AutoComplete.java
            │          │  │      ChangeWallpaperTime.java
            │          │  │      DefaultBackgroundImage.java
            │          │  │      DefaultSearchEngine.java
            │          │  │      DefaultWikiLanguage.java
            │          │  │      LoginRequest.java
            │          │  │      Theme.java
            │          │  │      
            │          │  └─response
            │          │          CollectionResponse.java
            │          │          GeneralSettingResponse.java
            │          │          NavigationResponse.java
            │          │          SearchEngineResponse.java
            │          │          
            │          ├─service
            │          │      SettingService.java
            │          │      SHULoginService.java
            │          │      
            │          ├─serviceImpl
            │          │      SettingServiceImpl.java
            │          │      SHULoginServiceImpl.java
            │          │      
            │          └─util
            │                  BeanUtil.java
            │                  JwtUtil.java
            │                  MongoAutoidUtil.java
            │                  
            ├─resources
            │      ApplicationContext.xml
            │      executor.properties
            │      log4j.properties
            │      mongodb-context.xml
            │      mongodb.properties
            │      searchEngineCoefficient.properties
            │      springmvc-servlet.xml
            │      
            └─webapp
                └─WEB-INF
                        web.xml
```