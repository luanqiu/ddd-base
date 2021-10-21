package ddd.base.utils.wechat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import lombok.Data;

/**
 * 场景入参表
 * author  likongpeng
 * date 2019/4/12
 */
@Data
public class WechatSceneParamsDTO implements Serializable {

  private static final long serialVersionUID = 3570762692300573568L;

  private Long id;

  /**
   * 二维码类型
   * 任务海报 taskPromote
   * 首页 firstPage
   */
  private String type;

  /**
   * 不同的类型存储的字段不同
   */
  private String  value;

  /**
   * 商家授权的唯一标识
   * 微信小程序对应的是 appId
   */
  private String merchantId;

  /**
   * 这个 map 是 value 的反 json
   */
  private Map<String,String> valueMap;

  /**
   * 默认xiaojiuwo
   */
  private String ownerCode;
  private Boolean available;

  /**
   * 对应商家的merchantId
   */
  private String dataOwnerCode;
  private Date createTime;
  private Date updateTime;

  /**
   * 二维码url
   */
  private String erweimaUrl;

}
