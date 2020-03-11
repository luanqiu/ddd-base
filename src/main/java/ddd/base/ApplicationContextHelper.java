package ddd.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ApplicationContextHelper
 * author  wenhe
 * date 2019/3/21
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

  /**
   * 主 ApplicationContext
   */
  private static ApplicationContext ROOT_APPLICATION_CONTEXT;

  /**
   * 单元化逻辑隔离的 ApplicationContext
   */
  private static Map<String,ApplicationContext> UNITIZE_APPLICATION_CONTEXT_MAP = new ConcurrentHashMap<>();

  public static void registerApplication(String applicationKey, AnnotationConfigApplicationContext applicationContext) {
    UNITIZE_APPLICATION_CONTEXT_MAP.put(applicationKey, applicationContext);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextHelper.ROOT_APPLICATION_CONTEXT = applicationContext;
  }

  public static final ApplicationContext getRootApplication(){
    return ROOT_APPLICATION_CONTEXT;
  }

  public static final ApplicationContext getApplication(String applicationKey){
    return UNITIZE_APPLICATION_CONTEXT_MAP.get(applicationKey);
  }

  /**
   * 判断是否从子容器拿，否则从父容器拿
   * @param targetClz
   * @param <T>
   * @return
   */
  public static <T> T getBean(Class<T> targetClz) {
    // 如果 domainName 为空，直接从父容器拿
    // 如果 domainName 不为空，先从子容器拿，找不到再从父容器拿
    T t = null;
    String domainName = ThreadContext.get(ThreadContext.DOMAIN_NAME);
    ApplicationContext childApplicationContext = UNITIZE_APPLICATION_CONTEXT_MAP.get(domainName);
    if(StringUtils.isEmpty(domainName) || null == childApplicationContext){
      t = getSmallBean(targetClz, ROOT_APPLICATION_CONTEXT);
      if(null != t){
        return t;
      }
    }
    t = getSmallBean(targetClz, childApplicationContext);
    if(null != t){
      return t;
    }
    t = getSmallBean(targetClz, ROOT_APPLICATION_CONTEXT);
    if(null != t){
      return t;
    }
    throw new RuntimeException("找不到 bean"+ targetClz.getSimpleName());
  }

  private static <T> T getSmallBean(Class<T> targetClz,ApplicationContext applicationContext){
    T beanInstance = null;
    //byType
    try {
      beanInstance = (T) applicationContext.getBean(targetClz);
    } catch (Exception e) {
      throw e;
    }
    //byName
    if (beanInstance == null) {
      String simpleName = targetClz.getSimpleName();
      simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
      beanInstance = (T) applicationContext.getBean(simpleName);
    }
    return beanInstance;
  }

  public static <T> T getBean(String domainName,String beanName){
    // 如果 domainName 为空，直接从父容器拿
    // 如果 domainName 不为空，先从子容器拿，找不到再从父容器拿
    T t = null;
    ApplicationContext childApplicationContext = UNITIZE_APPLICATION_CONTEXT_MAP.get(domainName);
    if(StringUtils.isEmpty(domainName) || null == childApplicationContext){
      t = getSmallBean(beanName, ROOT_APPLICATION_CONTEXT);
      if(null != t){
        return t;
      }
    }
    t = getSmallBean(beanName, childApplicationContext);
    if(null != t){
      return t;
    }
    t = getSmallBean(beanName, ROOT_APPLICATION_CONTEXT);
    if(null != t){
      return t;
    }
    throw new RuntimeException("找不到 bean"+beanName);
  }

  public static <T> T getBean(String beanName){
    // 如果 domainName 为空，直接从父容器拿
    // 如果 domainName 不为空，先从子容器拿，找不到再从父容器拿
    T t = null;
    String domainName = ThreadContext.get(ThreadContext.DOMAIN_NAME);
    ApplicationContext childApplicationContext = UNITIZE_APPLICATION_CONTEXT_MAP.get(domainName);
    if(StringUtils.isEmpty(domainName) || null == childApplicationContext){
      t = getSmallBean(beanName, ROOT_APPLICATION_CONTEXT);
      if(null != t){
        return t;
      }
    }
    t = getSmallBean(beanName, childApplicationContext);
    if(null != t){
      return t;
    }
    t = getSmallBean(beanName, ROOT_APPLICATION_CONTEXT);
    if(null != t){
      return t;
    }
    throw new RuntimeException("找不到 bean"+beanName);
  }

  private static <T> T getSmallBean(String beanName,ApplicationContext applicationContext){
    if(null == applicationContext){
      return null;
    }
    String simpleName = Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
    Object bean =  applicationContext.getBean(simpleName);
    return (T) bean;
  }





}
