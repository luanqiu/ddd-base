package ddd.base.domain.dto;


import java.util.List;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * AbilityDTO
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class DomainServiceAbilityDTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;

  private String domainServiceCode;
  private String code;
  private String desc;
  private Boolean repository;

  /**
   * 入参
   */
  private List<RequestParamDTO> requestParams;

  /**
   * 出参
   */
  private List<ResponseParamDTO> responseParams;


  public boolean abilityEntityLegal() {
    if (
        StringUtils.isEmpty(domainServiceCode) ||
        StringUtils.isEmpty(desc) ||
        StringUtils.isEmpty(code)
        ) {
      return false;
    }
    return true;
  }
}
