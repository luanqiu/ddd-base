package ddd.base.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
* 参数
*author  wenhe
*date 2020/1/6
*/
@Data
public class ParamDTO extends BaseDTO {

  private static final long serialVersionUID = -8222896216694750517L;

  /**
   * 参数 code
   */
  private String code;

  /**
   * 参数 名称
   */
  private String name;

  /**
   * 参数 类型
   */
  private String type;

  /**
   * 对象 name
   */
  private String objectName;

  /**
   * 参数 类型
   */
  private String targetCode;

  /**
   * 参数 类型,入参，还是出参
   */
  private String paramType;

  /**
   *
   */
  private int sort;

  private String descript;
  private boolean available;
  private Date createTime;
  private Date updateTime;
  private String dataOwnerCode;

  /**
   * 当前对象的局部变量
   */
  private List<ParamDTO> childParams = new ArrayList<>();

}
