package ddd.base;

import ddd.base.domain.BaseEntity;
import ddd.base.domain.dto.AggrDTO;
import ddd.base.domain.dto.EntityDTO;
import ddd.base.flow.BaseFlow;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * JiuWoCache
 * author  wenhe
 * date 2019/3/21
 */
@Slf4j
public class JiuWoCache implements Serializable {

  private static final long serialVersionUID = -1764361340680660907L;

  /**
   * JiuWoCache
   * first key dataOwnerCode
   * second key solutionCode 解决方案code
   */
  private static Map<String,Map<String, JiuWoCache>> UNITIZE_APPLICATION_CACHE_MAP = new ConcurrentHashMap<>();

  public  synchronized static JiuWoCache registerJiuWoCache(String ownerCode, String solutionCode) {
    if(StringUtils.isEmpty(ownerCode)){
      throw new RuntimeException("租户标识不能为空~");
    }
    if(StringUtils.isEmpty(solutionCode)){
      throw new RuntimeException("解决方案不能为空~");
    }

    Map<String, JiuWoCache> solutionMap = UNITIZE_APPLICATION_CACHE_MAP.get(ownerCode);
    if(null ==  solutionMap){
      solutionMap = new ConcurrentHashMap<>();
      UNITIZE_APPLICATION_CACHE_MAP.put(ownerCode,solutionMap);
    }

    JiuWoCache jiuWoCache = solutionMap.get(solutionCode);
    if(null != jiuWoCache){
      return jiuWoCache;
    }

    jiuWoCache = new JiuWoCache();
    solutionMap.put(solutionCode,jiuWoCache);
    UNITIZE_APPLICATION_CACHE_MAP.put(ownerCode,solutionMap);

    return jiuWoCache;
  }

  public static final JiuWoCache getJiuWoCache(){
    String ownerCode = ThreadContext.get(ThreadContext.DOMAIN_NAME);
    String solutionCode = ThreadContext.get(ThreadContext.SOLUTION_CODE);
    if("code20211207155344732301".equalsIgnoreCase(solutionCode) ||
    "xiaojiuwo".equalsIgnoreCase(ownerCode)){
      return getJiuWoDefaultCache();
    }
    return getJiuWoCache(ownerCode,solutionCode);
  }

  public static final JiuWoCache getJiuWoDefaultCache(){
    return getJiuWoCache("xiaojiuwo","code20211207155344732301");
  }

  public static final JiuWoCache getJiuWoCache(String ownerCode,String solutionCode){
    if(StringUtils.isEmpty(ownerCode)){
      throw new RuntimeException("租户标识不能为空~");
    }
    if(StringUtils.isEmpty(solutionCode)){
      throw new RuntimeException("解决方案不能为空~");
    }
    Map<String, JiuWoCache> solutionMap = UNITIZE_APPLICATION_CACHE_MAP.get(ownerCode);
    if(null == solutionMap){
      log.info("solutionMap is null，dataOwnerCode is {} , solutionCode is {} ",ownerCode,solutionCode);
      return new JiuWoCache();
    }
    JiuWoCache jiuWoCache =  solutionMap.get(solutionCode);
    if(null == jiuWoCache){
      log.error("jiuWoCache is null，dataOwnerCode is {} , solutionCode is {} ",ownerCode,solutionCode);
      throw new RuntimeException("找不到 JiuWoCache~");
    }
    return jiuWoCache;
  }

  public static final JiuWoCache getParentJiuWoCache(){
    JiuWoCache currentJiuWoCache = getJiuWoCache();
    if(StringUtils.isEmpty(currentJiuWoCache.getParentSolutionCode())){
      return null;
    }
    return UNITIZE_APPLICATION_CACHE_MAP.get(currentJiuWoCache.getParentOwnerCode())
            .get(currentJiuWoCache.getParentSolutionCode());
  }

  /**
   * 当前 ownerCode
   */
  private String cueentOwnerCode;

  /**
   * parent OwnerCode
   */
  private String parentOwnerCode;

  /**
   * 当前解决方案 code
   */
  private String cueentSolutionCode;

  /**
   * 父解决方案 code
   */
  private String parentSolutionCode;

  /**
   * 当前解决方案实体
   */
  private BaseEntity cueentSolutionEntity;

  /**
   * 解决方案 Map
   * key：Business::code
   * BaseEntity = BusinessEntity
   */
  private Map<String, BaseEntity> solutionMap = new ConcurrentHashMap<>();

  /**
   * 页面组件 List
   * BaseEntity = PageAbilityEntity
   */
  private List<BaseEntity> pageAbilityList = new CopyOnWriteArrayList<>();

  /**
   * 链接器 List
   * BaseEntity =  ConnectorEntity
   */
  private List<BaseEntity> connectorList = new CopyOnWriteArrayList<>();

  /**
   * 流程 Map
   * key：BaseFlow::flowId
   * BaseFlow = org.light.speed.flow.init.Flow
   */
  private Map<String, BaseFlow> flowMap = new ConcurrentHashMap<>();

  /**
   * 实体 Map
   * key：EntityDTO::code
   */
  private Map<String, EntityDTO> entityMap = new ConcurrentHashMap<>();

  /**
   * 聚合 Map
   * key：AggrDTO::code
   */
  private Map<String, AggrDTO> aggrMap = new ConcurrentHashMap<>();

  /**
   * ApplicationContext
   */
  private ApplicationContext applicationContext;

  /**
   *  check jiuWoCache 的数据是否可用
   * @param jiuWoCache
   * @return false:不可用；true:可用
   */
  public static final boolean checkAvailable(JiuWoCache jiuWoCache){
    if(null == jiuWoCache
      || null == jiuWoCache.getApplicationContext()
      || jiuWoCache.getEntityMap().size() <= 0
    ){
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  /**
   * 获取实体
   * 先从当前上下文取
   * 再从当前上下文取
   * 最后从酒窝上下文取
   * @param entityKey
   * @return
   */
  public static EntityDTO findEntity(String entityKey) {
    EntityDTO entity = getJiuWoCache().getEntityMap().get(entityKey);
    if (null != entity) {
      return entity;
    }
    if(null != getParentJiuWoCache()){
      entity = getParentJiuWoCache().getEntityMap().get(entityKey);
      if (null != entity) {
        return entity;
      }
    }
    return getJiuWoDefaultCache().getEntityMap().get(entityKey);
  }

  public Map<String, BaseEntity> getSolutionMap() {
    return solutionMap;
  }

  public void setSolutionMap(Map<String, BaseEntity> solutionMap) {
    this.solutionMap = solutionMap;
  }

  public List<BaseEntity> getPageAbilityList() {
    return pageAbilityList;
  }

  public void setPageAbilityList(List<BaseEntity> pageAbilityList) {
    this.pageAbilityList = pageAbilityList;
  }

  public List<BaseEntity> getConnectorList() {
    return connectorList;
  }

  public void setConnectorList(List<BaseEntity> connectorList) {
    this.connectorList = connectorList;
  }

  public Map<String, BaseFlow> getFlowMap() {
    return flowMap;
  }

  public void setFlowMap(Map<String, BaseFlow> flowMap) {
    this.flowMap = flowMap;
  }

  public Map<String, EntityDTO> getEntityMap() {
    return entityMap;
  }

  public void setEntityMap(Map<String, EntityDTO> entityMap) {
    this.entityMap = entityMap;
  }

  public Map<String, AggrDTO> getAggrMap() {
    return aggrMap;
  }

  public void setAggrMap(Map<String, AggrDTO> aggrMap) {
    this.aggrMap = aggrMap;
  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public String getCueentSolutionCode() {
    return cueentSolutionCode;
  }

  public void setCueentSolutionCode(String cueentSolutionCode) {
    this.cueentSolutionCode = cueentSolutionCode;
  }

  public String getParentSolutionCode() {
    return parentSolutionCode;
  }

  public void setParentSolutionCode(String parentSolutionCode) {
    this.parentSolutionCode = parentSolutionCode;
  }

  public BaseEntity getCueentSolutionEntity() {
    return cueentSolutionEntity;
  }

  public void setCueentSolutionEntity(BaseEntity cueentSolutionEntity) {
    this.cueentSolutionEntity = cueentSolutionEntity;
  }

  public String getCueentOwnerCode() {
    return cueentOwnerCode;
  }

  public void setCueentOwnerCode(String cueentOwnerCode) {
    this.cueentOwnerCode = cueentOwnerCode;
  }

  public String getParentOwnerCode() {
    return parentOwnerCode;
  }

  public void setParentOwnerCode(String parentOwnerCode) {
    this.parentOwnerCode = parentOwnerCode;
  }

}
