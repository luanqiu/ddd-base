package ddd.base.domain;

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
	public Long pageSize = 5L;

	/**
	 * 每页多少
	 */
	@Transient
	public Long totalPageSize = 0L;

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
}
