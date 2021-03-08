package ddd.base.flow;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Id = 前缀 3位 + 时间14位 + 随机6位
 *
 * by wenhe，id 容易冲突，需要修改
 */
public class IDBuilderUtils {

  private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  public static final String CODE ="code";

  public static String build(String prefix) {
    String timeStamp = DTF.format(LocalDateTime.now());
    StringBuilder idBuilder = new StringBuilder();
    idBuilder.append(prefix);
    idBuilder.append(timeStamp);
    getRandomSive(idBuilder);
    return idBuilder.toString();
  }

  private static void getRandomSive(StringBuilder idBuilder){
    Random random = new Random();
    String result="";
    for (int i=0;i<6;i++)
    {
      idBuilder.append(random.nextInt(10));
    }
  }
}
