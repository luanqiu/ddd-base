package ddd.base.flow;

import java.io.Serializable;
import lombok.Data;

/**
 * 基础流程
 */
@Data
public class BaseFlow implements Serializable {

	/**
	 * 唯一标识
	 */
	private String flowId;

	private static final long serialVersionUID = 4374909718559662110L;

}
