package ddd.base.domain.dto;

import java.io.Serializable;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * Created by luanqiu on 2019/5/9.
 */
@Data
public class BaseDTO implements Serializable {

  private static final long serialVersionUID = 3570762692300573568L;

  public String id;
  private Integer rows = 10;
  private Integer page =1;
  private String oper;
  private Integer begin = 0;
  private Integer end = 10;
  private String ownerCode;

  public void init() {
    if(StringUtils.endsWithIgnoreCase(id, "_empty")){
      setId(null);
    }
  }

  public void pageLimie(){
    if(page.equals(1)){
      begin =0;
      end=rows-1;
      return;
    }
    begin = (page-1) * rows;
    end = begin+rows-1;
  }
}
