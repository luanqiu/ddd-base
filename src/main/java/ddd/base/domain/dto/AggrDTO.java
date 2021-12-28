package ddd.base.domain.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.util.CollectionUtils;
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

  /**
   * 全路径名称
   */
  private String fullName;
  private Class clazz;

  private List<AggrEleDTO> attributes;
  // key：AggrEleDTO::entityCode ; value:AggrEleDTO
  private Map<String,AggrEleDTO> attributesMap = new HashMap<>();

  private List<AggrAbilityDTO> abilitys;
  // key：AggrAbilityDTO::code
  private Map<String,AggrAbilityDTO> abilitysMap = new HashMap<>();

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

  public void initMap() {
    if(!CollectionUtils.isEmpty(attributes)){
      attributesMap.putAll(
              attributes.stream().collect(Collectors.toMap(
                      AggrEleDTO::getEntityCode,e->e,(e1,e2)->e1
              ))
      );
    }

    if(!CollectionUtils.isEmpty(abilitys)){
      abilitysMap.putAll(
              abilitys.stream().collect(Collectors.toMap(
                      AggrAbilityDTO::getCode,e->e,(e1,e2)->e1
              ))
      );
    }


  }
}
