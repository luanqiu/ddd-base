package ddd.base.domain;

import ddd.base.ThreadContext;
import lombok.Getter;

/**
* 必须的字段
*@author  likongpeng
*@date 2021/12/28
*/
@Getter
public enum RequiredFieldEnum {

  DOMAIN_NAME("ownerCode",ThreadContext.DOMAIN_NAME),
  DATA_OWNER_CODE(ThreadContext.DATA_OWNER_CODE, ThreadContext.DATA_OWNER_CODE),
  SOLUTION_CODE(ThreadContext.SOLUTION_CODE, ThreadContext.SOLUTION_CODE),
  ;

  private String code;
  private String desc;

  RequiredFieldEnum(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  /**
   * 通过code查找枚举
   */
  public static RequiredFieldEnum findByCode(String code) {
    for (RequiredFieldEnum item : values()) {
      if (item.code.equals(code)) {
        return item;
      }
    }
    return null;
  }

}
