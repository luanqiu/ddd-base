package ddd.base.domain.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * VODTO
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class VODTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;

  private String code;
  private String desc;
  private String entityCode;
  private EntityDTO entityDTO;
  private String attrCode;
  private String attrDesc;

  /**
   * vo 扩展标记字段
   */
  private String voAttrDZ;

  /**
   * 更新时间
   */
  private Date updateTime;

  public boolean voLegal() {
    if (
        StringUtils.isEmpty(code) ||
        StringUtils.isEmpty(desc) ||
        null ==entityDTO
        ) {
      return false;
    }
    return true;
  }


}
