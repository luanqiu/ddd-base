package ddd.base.domain;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Transient;

public abstract class BaseEntity<T> implements Entity<T> {

	/**
	 * 当前页面
	 */
	@Transient
	public Long currentPage = 1L;

	/**
	 * 每页多少
	 */
	@Transient
	public Long pageSize = 100000L;

	/**
	 * 每页多少
	 */
	@Transient
	public Long totalPageSize;

	/**
	 * 数据拥有者
	 */
	@Column(name = "data_owner_code")
	public String dataOwnerCode;

	/**
	 * 是否需要更新数据库标志
	 */
	@Transient
	@JSONField(serialize=false)
	public boolean update = true;

	/**
	 * 是否需要新增数据库标志
	 * true 需要新增
	 * false 不需要
	 */
	@Transient
	@JSONField(serialize=false)
	public boolean insert = true;

	/**
	 * 是否需要查询数据库标志
	 * true 需要新增
	 * false 不需要
	 */
	@Transient
	@JSONField(serialize=false)
	public boolean query = true;

	/**
	 * 和query配置使用，query == false 时，直接返回 queryResult
	 */
	@Transient
	public Object queryResult;


	/**
	 * 是否需要数据拥有者
	 * true 不需要
	 * false 需要
	 */
	@Transient
	@JSONField(serialize=false)
	public boolean noDataOwnerCode = false;

	/**
	 * 是否需要在基础设施层执行脚本
	 * true 不在基础设施层执行脚本
	 * false 在基础设施层执行脚本
	 */
	@Transient
	@JSONField(serialize=false)
	public boolean noExecuteGroovy = false;

	/**
	 * insert 之前是否进行幂等校验
	 * true 需要
	 * false 不需要
	 */
	@Transient
	@JSONField(serialize=false)
	public boolean miDengBeforeInsert = false;

	@Transient
	public BaseEntity miDengEntity;

	/**
	 * 最新数据库操作类型
	 * @see RepositoryLogicTypeEnum
	 */
	@Transient
	public String lastNewRepositoryLogicType;

	/**
	 * 主要用于批量操作的对象
	 */
	@Transient
	public List<BaseEntity> baseEntitys;

	/**
	 * 对数据库 in 的操作
	 */
	@Transient
	public List<InVO> inVOS;

	/**
	 * 数据转化错误类
	 * key:execl 行号/唯一表示
	 * keyTwo:列名
	 */
	@Transient
	private Map<String,Set<String>> errorSetsMap = new HashMap<>();

	@Transient
	private Map<String, Map<String, Set<String>>> errorSetsMapTwo = new HashMap<>();
	/**
	 * execl的行号
	 */
	@Transient
	private String execlLine;

	public boolean isUpdate() {
		return update;
	}

	public boolean isInsert() {
		return insert;
	}

	public boolean isMiDengBeforeInsert() {
		return miDengBeforeInsert;
	}

	public void setMiDengBeforeInsert(boolean miDengBeforeInsert) {
		this.miDengBeforeInsert = miDengBeforeInsert;
	}

	public BaseEntity getMiDengEntity() {
		return miDengEntity;
	}

	public void setMiDengEntity(BaseEntity miDengEntity) {
		this.miDengEntity = miDengEntity;
	}

	public List<InVO> getInVOS() {
		return inVOS;
	}

	public void setInVOS(List<InVO> inVOS) {
		this.inVOS = inVOS;
	}

	public boolean getUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean getInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public String getLastNewRepositoryLogicType() {
		return lastNewRepositoryLogicType;
	}

	public void setLastNewRepositoryLogicType(String lastNewRepositoryLogicType) {
		this.lastNewRepositoryLogicType = lastNewRepositoryLogicType;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(Long totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	public String getDataOwnerCode(){
		return dataOwnerCode;
	}

	public void setDataOwnerCode(String dataOwnerCode){
		this.dataOwnerCode = dataOwnerCode;
	}

	public List<BaseEntity> getBaseEntitys() {
		return baseEntitys;
	}

	public void setBaseEntitys(List<BaseEntity> baseEntitys) {
		this.baseEntitys = baseEntitys;
	}

	public boolean isNoDataOwnerCode() {
		return noDataOwnerCode;
	}

	public void setNoDataOwnerCode(boolean noDataOwnerCode) {
		this.noDataOwnerCode = noDataOwnerCode;
	}

	public boolean isQuery() {
		return query;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public Object getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(Object queryResult) {
		this.queryResult = queryResult;
	}

	public Map<String,Set<String>> getErrorSetsMap() {
		return errorSetsMap;
	}

	public void addErrorCodes(String execlLine, String errors) {
		Set<String> s = this.getErrorSetsMap().getOrDefault(execlLine, new HashSet<>());
		s.add(errors);
		this.getErrorSetsMap().put(execlLine, s);
	}

	public void addErrorCodesTwo(String execlLine, String fieldCode, String errors) {
		Map<String,Set<String>> m = this.getErrorSetsMapTwo().getOrDefault(execlLine, new HashMap<>());
		Set<String> s = m.getOrDefault(fieldCode, new HashSet<>());
		s.add(errors);
		m.put(fieldCode, s);
		this.getErrorSetsMapTwo().put(execlLine, m);
	}

	public Map<String, Map<String, Set<String>>> getErrorSetsMapTwo() {
		return errorSetsMapTwo;
	}

	public void setErrorSetsMapTwo(
			Map<String, Map<String, Set<String>>> errorSetsMapTwo) {
		this.errorSetsMapTwo = errorSetsMapTwo;
	}

	public void setErrorSetsMap(Map<String,Set<String>> errorSetsMap) {
		this.errorSetsMap = errorSetsMap;
	}

	public String getExeclLine() {
		return execlLine;
	}

	public void setExeclLine(String execlLine) {
		this.execlLine = execlLine;
	}

	public boolean isNoExecuteGroovy() {
		return noExecuteGroovy;
	}

	public void init(){
		if(null == currentPage){
			currentPage = 1L;
		}
		if(null == pageSize){
			pageSize = 20L;
		}
		if(null == totalPageSize){
			totalPageSize = 0L;
		}
	}

}
