package ddd.base.domain.dto;
import lombok.Data;

/**
* 应用服务基础定义
*author  wenhe
*date 2020/1/6
*/
@Data
public class AppDefintionDTO extends BaseDTO {

  private static final long serialVersionUID = -8222896216694750517L;

  /**
   * 应用服务 code
   */
  private String code;

  /**
   * 应用服务 名称
   */
  private String desc;

}
