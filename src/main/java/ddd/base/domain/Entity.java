package ddd.base.domain;

import java.util.Date;

/**
 * 实体，命名：名词+Entity
 * author  wenhe
 * date 2019/4/5
 */
public interface Entity<T> extends DomainI {

  /**
   * 得到实体的唯一标识
   */
  T obtainUniqueId();

  /**
   * 通用的方法
   * @return
   */
  String getOwnerCode();

  void setOwnerCode(String ownerCode);

  Boolean getAvailable();

  void setAvailable(Boolean available);

  Date getCreateTime();

  void setCreateTime(Date createTime);

  Date getUpdateTime();

  void setUpdateTime(Date updateTime);

}
