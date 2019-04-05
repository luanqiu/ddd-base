package org.ddd.base.domain;

import org.ddd.base.ApplicationContextHelper;

/**
* DomainFactory
*author  likongpeng
*date 2019/4/5
*/
public interface DomainFactory<V extends VO, D extends DomainI> {

  /**
   * 完善domain属性
   */
  D perfect(V vo);

  /**
   * 得到基础domain
   */
  static <T> T get(Class<T> clazz) {
    return ApplicationContextHelper.getBean(clazz);
  }


}
