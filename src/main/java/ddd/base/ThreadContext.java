package ddd.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.vm.VM;

@Slf4j
public class ThreadContext {

  //创建者信息,等同于 ownerCode
  public static String DOMAIN_NAME = "domainName";
  //创建者信息
  public static String DATA_OWNER_CODE = "dataOwnerCode";
  //创建者信息
  public static String SOLUTION_CODE = "solutionCode";
  //登陆者信息
  public static String LOGIN_CODE = "loginCode";




  public static String IS_NEED_PAGE_SIZE = "isNeedPageSize";
  public static String PAGE_SIZE = "pageSize";
  public static String CURRENT_PAGE = "currentPage";








  public static final ThreadLocal<Map<String,Object>> CONTEXT = new InheritableThreadLocal<>();

  public static final void  put(String key,Object value){
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
    try{
      return (T) CONTEXT.get().getOrDefault(key, null);
    }catch(NullPointerException e){
      log.info("ThreadContext NullPointerException {} ",key,e);
      return null;
    }
  }

  public static final void  clear(){
      for(int i=0;i<2;i++){
        CONTEXT.remove();
      }
  }
}
