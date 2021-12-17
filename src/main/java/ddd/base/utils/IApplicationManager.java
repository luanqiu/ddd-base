package ddd.base.utils;

import org.springframework.context.ApplicationContext;

public interface IApplicationManager {

	ApplicationContext registerApplicationByJar(String domainName,String solutionCode, String loadFilePath,
			String packagePrefix) throws Exception;

}
