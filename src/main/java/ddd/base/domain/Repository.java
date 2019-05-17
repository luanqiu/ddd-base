package org.ddd.base.domain;

import org.ddd.base.ApplicationContextHelper;

/**
 * 仓库
 * author  wenhe
 * date 2019/4/5
 */
@org.springframework.stereotype.Repository
public interface Repository {

  /**
   * 得到基础domain
   */
  static <T> T get(Class<T> clazz) {
    return ApplicationContextHelper.getBean(clazz);
  }

}
