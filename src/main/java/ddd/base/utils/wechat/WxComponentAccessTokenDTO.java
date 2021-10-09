package ddd.base.utils.wechat;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class WxComponentAccessTokenDTO implements Serializable {

  private static final long serialVersionUID = 3570762692300573568L;

  private Long id;
  private String appId;
  /**
   * 令牌
   */
  private String token;

  /**
   * 验证令牌
   */
  private String componentVerifyTicket;

  /**
   * 令牌过期时间
   */
  private Date tokenExpireTime;

  private Boolean available;
  private Date createTime;
  private Date updateTime;
}
