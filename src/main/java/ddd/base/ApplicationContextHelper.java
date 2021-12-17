package ddd.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextHelper
 * author  wenhe
 * date 2019/3/21
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

  /**
   * 主 ApplicationContext
   * dmvp-web
   */
  private static ApplicationContext ROOT_APPLICATION_CONTEXT;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextHelper.ROOT_APPLICATION_CONTEXT = applicationContext;
  }

  public static final ApplicationContext getRootApplication(){
    return ROOT_APPLICATION_CONTEXT;
  }

  public static final ApplicationContext getApplication(){
    return JiuWoCache.getJiuWoCache().getApplicationContext();
  }

  /**
   * 先从当前容器取，没有从父容器取，仍然没有从 xiaojiuo、Root 取
   * @param targetClz
   * @param <T>
   * @return
   */
  public static <T> T getBean(Class<T> targetClz) {
    // 先从当前容器取
    JiuWoCache currentJiuWoCache = JiuWoCache.getJiuWoCache();
    T t = getSmallBean(targetClz,currentJiuWoCache.getApplicationContext());
    if(null != t){
      return t;
    }

    // 没有从父容器取
    if(null != JiuWoCache.getParentJiuWoCache()){
      t = getSmallBean(targetClz,JiuWoCache.getParentJiuWoCache().getApplicationContext());
      if(null != t){
        return t;
      }
    }

    t = getSmallBean(targetClz,JiuWoCache.getJiuWoDefaultCache().getApplicationContext());
    if(null != t){
      return t;
    }

    t = getSmallBean(targetClz,getRootApplication());
    if(null != t){
      return t;
    }

    throw new RuntimeException("找不到 bean"+ targetClz.getSimpleName());
  }

  /**
   * 先根据 type 再根据 name
   * @param targetClz
   * @param applicationContext
   * @param <T>
   * @return
   */
  private static <T> T getSmallBean(Class<T> targetClz,ApplicationContext applicationContext){
    if(null == applicationContext){
      return null;
    }
    T beanInstance = null;
    //byType
    try {
      beanInstance = applicationContext.getBean(targetClz);
    } catch (NoSuchBeanDefinitionException e) {
      return beanInstance;
    }
    //byName
    if (beanInstance == null) {
      String simpleName = targetClz.getSimpleName();
      simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
      Object beanInstanceO = null;
      try{
        beanInstanceO = applicationContext.getBean(simpleName);
      }catch(NoSuchBeanDefinitionException e){
        return beanInstance;
      }
      if(null != beanInstanceO){
        return (T)beanInstanceO;
      }
    }
    return beanInstance;
  }

  public static <T> T getBean(String domainName,String solutionCode,String beanName){
    T t = getSmallBean(beanName, JiuWoCache.getJiuWoCache(domainName,solutionCode).getApplicationContext());
    if(null != t){
      return t;
    }

    if(null != JiuWoCache.getParentJiuWoCache()){
      t = getSmallBean(beanName,  JiuWoCache.getJiuWoDefaultCache().getApplicationContext());
      if(null != t){
        return t;
      }
    }

    t = getSmallBean(beanName, ROOT_APPLICATION_CONTEXT);
    if(null != t){
      return t;
    }
    throw new RuntimeException("找不到 bean"+ beanName);
  }

  public static <T> T getBean(String beanName){
    // 先从当前容器取
    JiuWoCache currentJiuWoCache = JiuWoCache.getJiuWoCache();
    T t = getSmallBean(beanName,currentJiuWoCache.getApplicationContext());
    if(null != t){
      return t;
    }

    // 没有从父容器取
    if(null != JiuWoCache.getParentJiuWoCache()){
      t = getSmallBean(beanName,JiuWoCache.getParentJiuWoCache().getApplicationContext());
      if(null != t){
        return t;
      }
    }

    t = getSmallBean(beanName,JiuWoCache.getJiuWoDefaultCache().getApplicationContext());
    if(null != t){
      return t;
    }

    t = getSmallBean(beanName,getRootApplication());
    if(null != t){
      return t;
    }

    throw new RuntimeException("找不到 bean"+ beanName);
  }

  private static <T> T getSmallBean(String beanName,ApplicationContext applicationContext){
    if(null == applicationContext){
      return null;
    }
    String simpleName = Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
    Object bean = null;
    try{
      bean =  applicationContext.getBean(simpleName);
    }catch(NoSuchBeanDefinitionException e){
      return null;
    }
    return (T) bean;
  }

}
