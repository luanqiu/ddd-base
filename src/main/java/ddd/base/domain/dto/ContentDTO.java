package ddd.base.domain.dto;


import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * ContentDTO
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class ContentDTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;

  private String code;
  private String desc;
  private String ownerCode;
  private String upper;

  public boolean contentLegal() {
    if (
        StringUtils.isEmpty(code) ||
        StringUtils.isEmpty(desc) ||
        StringUtils.isEmpty(ownerCode) ||
        null == upper
        ) {
      return false;
    }
    return true;
  }

}
