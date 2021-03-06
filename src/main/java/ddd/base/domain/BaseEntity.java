package ddd.base.domain;

import java.util.List;
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
	public Long pageSize = 20L;

	/**
	 * 每页多少
	 */
	@Transient
	public Long totalPageSize = 0L;

	/**
	 * 数据拥有者
	 */
	@Column(name = "data_owner_code")
	public String dataOwnerCode;

	/**
	 * 是否需要更新数据库标志
	 */
	public boolean update = true;

	/**
	 * 主要用于批量操作的对象
	 */
	@Transient
	public List<BaseEntity> baseEntitys;

	public boolean getUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
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
