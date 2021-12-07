package ddd.base.domain.dto;

import java.util.List;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * Created by luanqiu on 2019/4/24.
 */
@Data
public class AggrDTO extends BaseDTO {


  private static final long serialVersionUID = 3570762692300573568L;

  private String code;
  private String desc;
  private String ownerCode;

  /**
   * 类型
   * core/common/support
   */
  private String type;

  private List<AggrEleDTO> attributes;
  private List<AggrAbilityDTO> abilitys;

  public boolean entityLegal() {
    if(
        StringUtils.isEmpty(code) ||
        StringUtils.isEmpty(desc) ||
        StringUtils.isEmpty(ownerCode)
        ){
      return false;
    }
    return true;
  }
}
