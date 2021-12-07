package ddd.base.domain.dto;


import lombok.Data;

/**
 * AttributeDTO
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class AggrEleDTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;


  private String aggrCode;
  private String entityCode;
  private EntityDTO entityDTO;
  private Boolean root;

  public boolean attributeLegal() {
    if (
        null == entityDTO
        ) {
      return false;
    }
    return true;
  }

}
