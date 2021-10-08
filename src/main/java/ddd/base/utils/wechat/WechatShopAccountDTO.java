package ddd.base.utils.wechat;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 小商店账户
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class WechatShopAccountDTO implements Serializable {

  private static final long serialVersionUID = 3570762692300573568L;

  private Long id;
  private String jiuwoAccountCode;
  // 三方平台 appid
  private String appId;
  // 授权者的 appid
  private String authorizerAppid;
  // 微信接口后缀的 access_token
  private String authorizationCode;
  // 令牌
  private String componentAccessToken;
  // access_token 过期时，使用 authorizerRefreshToken 进行请求刷新 access_token
  private String authorizerRefreshToken;
  private String ownerCode;
  private Boolean available;
  private String dataOwnerCode;
  private Date createTime;
  private Date updateTime;

  /**
   * 调用令牌过期时间
   */
  private Date expiresTime;

  /**
   * 权限集集合
   */
  private String funcInfo;


}
