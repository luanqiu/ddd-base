package ddd.base.domain.dto;

import lombok.Data;

/**
 * DomainNameDTO
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class DomainNameDTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;

  private String code;
  private String desc;
  private String ownerCode;

  /**
   * 类型
   * core/common/support
   */
  private String type;

}
