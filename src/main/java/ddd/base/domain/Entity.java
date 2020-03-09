package ddd.base.domain;

import java.util.zip.ZipInputStream;

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

}
