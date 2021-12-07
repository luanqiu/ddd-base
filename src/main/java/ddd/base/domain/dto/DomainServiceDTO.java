package ddd.base.domain.dto;


import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * AbilityDTO
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class DomainServiceDTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;

  private String code;
  private String desc;
  private String ownerCode;
  private String contentCode;
  private String contentDesc;
  private ContentDTO refContent;

  private DomainServiceAbilityDTO abilityDTO;

  public boolean domainSerivceLegal() {
    if (
        StringUtils.isEmpty(code) ||
        StringUtils.isEmpty(desc) ||
        StringUtils.isEmpty(ownerCode) ||
        null ==refContent
        ) {
      return false;
    }
    return true;
  }

}
