package ddd.base;

import ddd.base.domain.BaseEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * ApplicationContextCache
 * author  wenhe
 * date 2019/3/21
 */
public class ApplicationContextCache {

  /**
   * ApplicationContextCache
   * key ownerCode
   */
  private static Map<String,ApplicationContextCache> UNITIZE_APPLICATION_CACHE_MAP = new ConcurrentHashMap<>();

  public static void registerApplication(String ownerCode, ApplicationContextCache applicationContextCache) {
    UNITIZE_APPLICATION_CACHE_MAP.put(ownerCode, applicationContextCache);
  }

  public static final ApplicationContextCache getApplicationContextCache(String ownerCode){
    return UNITIZE_APPLICATION_CACHE_MAP.get(ownerCode);
  }

  /**
   * 业务 Map
   * key：ownerCode
   */
  private static final Map<String, BaseEntity> BUSINESS_MAP = new HashMap<>();

}
