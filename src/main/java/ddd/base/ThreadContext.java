package ddd.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadContext {

  /**
   * 用户登录信息
   */
  public static String DOMAIN_NAME = "domainName";

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
