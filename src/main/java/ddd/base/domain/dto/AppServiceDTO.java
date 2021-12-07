package ddd.base.domain.dto;


import java.util.List;

import lombok.Data;

/**
 * AppServiceDTO
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class AppServiceDTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;

  private String code;
  private String desc;
  private String ownerCode;
  private String abilityCode;
  private String sort;

  /**
   * 访问的 url
   */
  private String url;

  /**
   * 入参
   */
  private List<RequestParamDTO> requestParams;

  /**
   * 出参
   */
  private List<ResponseParamDTO> responseParams;
}
