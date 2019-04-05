package org.ddd.base.domain;

import org.springframework.stereotype.Component;

/**
 * 事件处理接口
 * author  likongpeng
 * date 2019/4/5
 */
@Component
public interface EventHandlerI<T extends Event> {

  /**
   * 处理事件
   */
  void execute(T event);

}
