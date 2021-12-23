package ddd.base.utils;

/**
* AopTargetUtils
*@author  likongpeng
*@date 2021/12/17
*/
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

/**
 * spring aop 动态代理工具类封装
 * @see AopUtils
 */
@Slf4j
public class AopTargetUtils {

  /**
   * 获取 目标对象
   * @param proxy 代理对象
   * @return
   * @throws Exception
   */
  public static Object getTarget(Object proxy) {
    if(!AopUtils.isAopProxy(proxy)) {
      return proxy;//不是代理对象
    }

    try{
      if(AopUtils.isJdkDynamicProxy(proxy)) {
        return getJdkDynamicProxyTargetObject(proxy);
      } else { //cglib
        return getCglibProxyTargetObject(proxy);
      }
    }catch(Exception e){
      log.error("getTarget error clazz is {}",proxy.getClass().getName());
    }
    return proxy;
  }


  public static Object getCglibProxyTargetObject(Object proxy) throws Exception {
    Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
    h.setAccessible(true);
    Object dynamicAdvisedInterceptor = h.get(proxy);

    Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
    advised.setAccessible(true);

    Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

    return target;
  }


  public static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
    Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
    h.setAccessible(true);
    AopProxy aopProxy = (AopProxy) h.get(proxy);

    Field advised = aopProxy.getClass().getDeclaredField("advised");
    advised.setAccessible(true);

    Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();

    return target;
  }

}
