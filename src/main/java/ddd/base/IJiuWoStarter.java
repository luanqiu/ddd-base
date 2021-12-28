package ddd.base;

public interface IJiuWoStarter {

	/**
	 * 单独发布解决方案
	 * @param domainName ownerCode
	 * @param solutionCode 解决方案 code
	 * @param codePath jar 路径
	 * @param codePackagePrefix package路径
	 */
	void publishSolution(String domainName, String solutionCode, String codePath,
			String codePackagePrefix);

	/**
	 * 初始化领域
	 * @param domainName
	 * @param solutionCode
	 */
	void initDomain(String domainName, String solutionCode);

	void init();
}
