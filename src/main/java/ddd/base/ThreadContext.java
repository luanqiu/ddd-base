package ddd.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadContext {

  /**
   * 创建者信息
   */
  public static String DOMAIN_NAME = "domainName";

  /**
   * 登陆者信息
   */
  public static String LOGIN_CODE = "loginCode";

  /**
   * 创建者信息
   */
  public static String DATA_OWNER_CODE = "dataOwnerCode";

  /**
   * 是否需要进行分页
   */
  public static String IS_NEED_PAGE_SIZE = "isNeedPageSize";

  /**
   * 查询创建者本身数据
   */
  public static String SELF_DATA_OWNER_CODE = "selfDataOwnerCode";

  public static final ThreadLocal<Map<String,Object>> CONTEXT = new InheritableThreadLocal<>();

  public static final void put(String key,Object value){
    if(null == key || null == value){
      return;
    }
    Map<String,Object> map = CONTEXT.get();
    if(null == map){
      map = new ConcurrentHashMap<>();
      CONTEXT.set(map);
    }
    map.put(key,value);
  }

  public static final <T> T get(String key){
    return (T) CONTEXT.get().getOrDefault(key, null);
  }

  public static final void clear(){
    for(int i=0;i<2;i++){
      CONTEXT.get().clear();
    }
  }
}
