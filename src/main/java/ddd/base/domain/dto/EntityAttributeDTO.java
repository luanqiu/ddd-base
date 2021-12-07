package ddd.base.domain.dto;

import lombok.Data;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
 * AttributeDTO
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class EntityAttributeDTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;


  private String entityCode;
  private String code;
  private String descript;
  private Boolean uniqueId;
  private String attrType;
  private String name;
  private Boolean available;
  private Date createTime;
  private Date updateTime;
  private String dataOwnerCode;
  // 定制的属性值，没有定制就为空
  private String entityAttrDZ;

  public  boolean attributeLegal() {
    if(StringUtils.endsWithIgnoreCase(id,"_empty")){
      setId(null);
    }
    if (
        StringUtils.isEmpty(entityCode) ||
        StringUtils.isEmpty(code) ||
        StringUtils.isEmpty(attrType) ||
        StringUtils.isEmpty(descript)
        ) {
      return false;
    }
    return true;
  }

  public  boolean queryAttributeLegal() {
    if (
        StringUtils.isEmpty(entityCode) ||
        null == getRows() ||
        null == getPage()
        ) {
      return false;
    }
    return true;
  }


}
