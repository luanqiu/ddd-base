package ddd.base.domain.dto;

import ddd.base.domain.TypeClassEnum;
import java.util.ArrayList;
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

  /**
   * 全路径名称
   */
  private String fullName;
  private Class clazz;

  private List<EntityAttributeDTO> attributes = new ArrayList<>();
  // key EntityAttributeDTO::code ; value:EntityAttributeDTO
  private transient Map<String,EntityAttributeDTO> attributesMap = new HashMap<>();

  private List<EntityAbilityDTO> abilitys;
  // key:EntityAbilityDTO::code ; value:EntityAbilityDTO
  private transient Map<String,EntityAbilityDTO> abilitysMap = new HashMap<>();

  private List<VODTO> vos;
  // firstKey:VODTO::code; sedKey:VODTO::attrCode;value::VODTO
  private transient Map<String,Map<String,VODTO>> vosMap = new HashMap<>();


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

  public void initMap() {
    if(!CollectionUtils.isEmpty(attributes)){
      attributesMap.putAll(
              attributes.stream().collect(Collectors.toMap(
                      EntityAttributeDTO::getCode,e->e,(e1,e2)->e1
              ))
      );
    }

    if(!CollectionUtils.isEmpty(abilitys)){
      abilitysMap.putAll(
              abilitys.stream()
                      // 能力出入参全路径
                      .peek(ability->{
                        if(!CollectionUtils.isEmpty(ability.getRequestParams())){
                          for (RequestParamDTO requestParam : ability.getRequestParams()) {
                            String fullName = TypeClassEnum.convertNodeRequestParamTypeSimpleToCX(requestParam.getType(),requestParam.getCode());
                            Class clazz = TypeClassEnum.convertNodeRequestParamTypeSimpleToClazz(requestParam.getType(),requestParam.getCode());
                            requestParam.setFullName(fullName);
                            requestParam.setClazz(clazz);
                          }
                        }

                        if(!CollectionUtils.isEmpty(ability.getResponseParams())){
                          for (ResponseParamDTO responseParam : ability.getResponseParams()) {
                            String fullName = TypeClassEnum.convertNodeRequestParamTypeSimpleToCX(responseParam.getType(),responseParam.getCode());
                            responseParam.setFullName(fullName);
                          }
                        }
                      })
                      .collect(Collectors.toMap(
                      EntityAbilityDTO::getCode,e->e,(e1,e2)->e1
              ))
      );
    }

    if(!CollectionUtils.isEmpty(vos)){
      vosMap.putAll(
              vos.stream().collect(Collectors.groupingBy(
                      VODTO::getCode,Collectors.toMap(VODTO::getAttrCode,v->v,(v1,v2)->v1)
              ))
      );
    }
  }
}
