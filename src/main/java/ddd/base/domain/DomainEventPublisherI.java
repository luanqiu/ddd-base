package org.ddd.base.domain;

/**
 * 发布消息
 * 保证消息一定可以发送出去
 * author  wenhe
 * date 2019/4/6
 */
public interface DomainEventPublisherI {

  /**
   * @param async true 异步，false 同步
   */
  void publish(DomainEvent event, Boolean async);
}
