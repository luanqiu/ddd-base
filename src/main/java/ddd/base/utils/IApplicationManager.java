package ddd.base.utils;

public interface IApplicationManager {

	boolean registerApplicationByJar(String applicationKey, String loadFilePath,
			String packagePrefix) throws Exception;

}
