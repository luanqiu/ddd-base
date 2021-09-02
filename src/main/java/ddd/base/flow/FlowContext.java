package ddd.base.flow;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ddd.base.ThreadContext;
import lombok.Data;

/**
* 上下文
*author  wenhe
*date 2020/2/21
*/
@Data
public class FlowContext implements Serializable {

  private static final long serialVersionUID = 3352823307400774412L;

  private FlowContext() {
  }

  /**
   * 初始化的策略可能改变
   * @return
   */
  public static final FlowContext getInstance(){
    return new FlowContext();
  }

  private final Map<String,Object> FLOW_MAP = new ConcurrentHashMap<>(16);

  public final <T> T get(String key){
    return (T) FLOW_MAP.get(key);
  }

  public final void put(String key,Object value){
    if (key == null || value == null){
      return;
    }
    FLOW_MAP.put(key,value);
  }

  public final void putMap(Map<String, Object> params) {
    if(CollectionUtils.isEmpty(params)){
      return;
    }
    // 不用 putAll 是为了去除 null
    for (Map.Entry<String, Object> e : params.entrySet()) {
      if(e.getValue() instanceof Integer){
        put(e.getKey(), Long.valueOf((Integer)e.getValue()));
      }else{
        put(e.getKey(), e.getValue());
      }
    }
    put("ownerCode", ThreadContext.get(ThreadContext.DOMAIN_NAME));
    put(ThreadContext.PAGE_SIZE, ThreadContext.get(ThreadContext.PAGE_SIZE));
    put(ThreadContext.CURRENT_PAGE, ThreadContext.get(ThreadContext.CURRENT_PAGE));
  }
}
