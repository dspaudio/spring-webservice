package family.namkang.webservice.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
	public static final String BASE_PATH = "D:\\Dev\\workspace_sts4\\spring-webservice\\upload";
	
	
	public static Path getFilePahtDaily(String basePath) throws IOException{
		
		Path dir = Paths.get(basePath, DateTimeUtil.getCurrentyyyy(), DateTimeUtil.getCurrentMM(), DateTimeUtil.getCurrentdd());
		Files.createDirectories(dir);
		
		return dir;
	}
	
	public static String getFileUri(Path path) throws IOException{
		String exclude = "file:///D:/Dev/workspace_sts4/spring-webservice/src/main/resources";
		return path.toUri().toString().replaceFirst(exclude, "");
	}
	
	public static boolean removeFromDisk(String path) {
		try {
			Files.delete( Paths.get(path) );
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
