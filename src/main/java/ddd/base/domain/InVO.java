package ddd.base.domain;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

/**
 * in 的 VO
 */
@Builder
@Data
public class InVO implements VO {

	/**
	 * db 字段
	 */
	private String dbFiled;

	/**
	 * db 对应的值集合
	 */
	private Set<Object> values;
}
