package ddd.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextHelper
 * author  wenhe
 * date 2019/3/21
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  private static BeanDefinitionRegistry beanDefinitonRegistry;

  /**
   * 动态注册bean
   */
  public synchronized static void registerBean(String beanName, Class clazz) {
    if (null == beanName || null == clazz) {
      throw new RuntimeException(beanName + "注册失败");
    }
    BeanDefinition beanDefinition = getBeanDefinitionBuilder(clazz).getBeanDefinition();
    if (!beanDefinitonRegistry.containsBeanDefinition(beanName)) {
      beanDefinitonRegistry.registerBeanDefinition(beanName, beanDefinition);
    }
  }

  private static BeanDefinitionBuilder getBeanDefinitionBuilder(Class clazz) {
    return BeanDefinitionBuilder.genericBeanDefinition(clazz);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextHelper.applicationContext = applicationContext;
    ConfigurableApplicationContext
        configurableApplicationContext =
        (ConfigurableApplicationContext) applicationContext;
    beanDefinitonRegistry =
        (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
  }

  public static <T> T getBean(Class<T> targetClz) {
    T beanInstance = null;
    //byType
    try {
      beanInstance = (T) applicationContext.getBean(targetClz);
    } catch (Exception e) {
    }
    //byName
    if (beanInstance == null) {
      String simpleName = targetClz.getSimpleName();
      simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
      beanInstance = (T) applicationContext.getBean(simpleName);
    }
    if (beanInstance == null) {
      throw new RuntimeException(
          "beanName " + targetClz.getSimpleName()
          + " can not be found in ApplicationContext (byType and byName)");
    }
    return beanInstance;
  }
}
