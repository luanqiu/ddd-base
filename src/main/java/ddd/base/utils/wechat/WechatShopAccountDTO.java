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

  private String id;
  private String jiuwoAccountCode;
  private String appId;
  private String authorizationCode;
  private String componentAccessToken;
  private String authorizerRefreshToken;
  private String loginCode;
  private String ownerCode;
  private Boolean available;
  private String dataOwnerCode;
  private Date createTime;
  private Date updateTime;


}
