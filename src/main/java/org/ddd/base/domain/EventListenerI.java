package org.ddd.base.domain;

import org.springframework.stereotype.Component;

/**
 * 事件监听者
 * author  wenhe
 * date 2019/4/5
 */
@Component
public interface EventListenerI<T extends Event> {

  /**
   * 处理事件
   */
  void execute(T event);

}
