package ddd.base.domain.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * Created by luanqiu on 2019/4/24.
 */
@Data
public class EntityDTO extends BaseDTO {


  private static final long serialVersionUID = 3570762692300573568L;

  private String code;
  private String desc;
  private String ownerCode;

  /**
   * 类型
   * core/common/support
   */
  private String type;

  private List<EntityAttributeDTO> attributes = new ArrayList<>();
  private List<EntityAbilityDTO> abilitys;
  private List<VODTO> vos;


  public boolean entityLegal() {
    if (
        StringUtils.isEmpty(code) ||
        StringUtils.isEmpty(desc) ||
        StringUtils.isEmpty(type) ||
        StringUtils.isEmpty(ownerCode)
        ) {
      return false;
    }
    return true;
  }

}
