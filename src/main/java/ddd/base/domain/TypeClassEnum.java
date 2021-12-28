package ddd.base.domain;

import ddd.base.ApplicationContextHelper;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
* NodeEnum
*author  wenhe
*date 2020/2/21
*/
@Getter
public enum TypeClassEnum {

  String("String", String.class),
  Double("Double", Double.class),
  Long("Long", Long.class),
  Boolean("Boolean", Boolean.class),
  List("List", java.util.List.class),
  Map("Map", java.util.Map.class),
  Date("Date", java.util.Date.class),
  Enum("Enum", Enum.class),
  FlowContext("FlowContext", ddd.base.flow.FlowContext.class),
//  Entity("en", ddd.base.domain.Entity.class),
//  Aggr("aggr", ddd.base.domain.Aggr.class),
//  DomainService("ds", ddd.base.domain.DomainService.class),

  ;

  private String code;
  private Class clazz;

  TypeClassEnum(String code, Class clazz) {
    this.code = code;
    this.clazz = clazz;
  }

  /**
   * 通过code查找枚举
   */
  public static TypeClassEnum findByCode(String code) {
    for (TypeClassEnum item : values()) {
      if (item.code.equals(code)) {
        return item;
      }
    }
    return null;
  }

  public static final String convertNodeRequestParamTypeSimpleToCX(String simpleType, String code) {
    // 实体,实体的 code 从字符串中截取
    if(simpleType.startsWith("o_en_") && !simpleType.endsWith("_List")){
      return ApplicationContextHelper.getBean(simpleType.split("_")[2]+"Entity").getClass().getName();
    }
    // List实体的泛型
    if(simpleType.startsWith("o_en_") && simpleType.endsWith("_List")){
      String fullName = ApplicationContextHelper.getBean(simpleType.split("_")[2]+"Entity").getClass().getName();
      return "java.util.List<"+ fullName +">";
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.String.getCode())){
      return String.getClass().getName();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Double.getCode())){
      return Double.getClass().getName();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Long.getCode())){
      return Long.getClass().getName();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Boolean.getCode())){
      return Boolean.getClass().getName();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.List.getCode())){
      return List.getClass().getName();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Map.getCode())){
      return Map.getClass().getName();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Date.getCode())){
      return Date.getClass().getName();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.FlowContext.getCode())){
      return FlowContext.getClass().getName();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.List.getCode())){
      return List.getClass().getName();
    }
    throw new RuntimeException("不支持的类型"+simpleType);
  }

  public static final Class convertNodeRequestParamTypeSimpleToClazz(String simpleType, String code) {
    // 实体
    if(simpleType.startsWith("o_en_") && !simpleType.endsWith("_List")){
      return ApplicationContextHelper.getBean(simpleType.split("_")[2]+"Entity").getClass();
    }
    // List实体的泛型
    if(simpleType.startsWith("o_en_") && simpleType.endsWith("_List")){
//      Class entity = JiuWoCache.getJiuWoCache().getApplicationContext().getBean(code).getClass();
//      String fullName = entity.getName();
//      java.util.List<fullName> list = new ArrayList<>();
//      list.add(entity);
      return java.util.List.class;
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.String.getCode())){
      return String.getClass();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Double.getCode())){
      return Double.getClass();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Long.getCode())){
      return Long.getClass();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Boolean.getCode())){
      return Boolean.getClass();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.List.getCode())){
      return List.getClass();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Map.getCode())){
      return Map.getClass();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.Date.getCode())){
      return Date.getClass();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.FlowContext.getCode())){
      return FlowContext.getClass();
    }
    if(StringUtils.equals(simpleType,TypeClassEnum.List.getCode())){
      return List.getClass();
    }
    throw new RuntimeException("不支持的类型"+simpleType);
  }


}
