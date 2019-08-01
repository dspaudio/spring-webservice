package family.namkang.webservice.domain.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.common.util.DateTimeUtil;

public class FileUtil {
	public static final String BASE_PATH_POST = "D:\\Dev\\workspace_sts4\\spring-webservice\\src\\main\\resources\\upload\\post";
	
	
	public static Path getFilePahtPost() throws IOException{
		
		Path dir = Paths.get(BASE_PATH_POST, DateTimeUtil.getCurrentyyyy(), DateTimeUtil.getCurrentMM(), DateTimeUtil.getCurrentdd());
		Files.createDirectories(dir);
		
		return dir;
	}
	public static String getFileUri(Path path) throws IOException{
		String exclude = "file:///D:/Dev/workspace_sts4/spring-webservice/src/main/resources";
		return path.toUri().toString().replaceFirst(exclude, "");
	}
	
	public static List<File> getUploadedFilesPost (MultipartFile[] files, Long postId) throws IOException {
        List<File> uploads = new ArrayList<File>();
		for (MultipartFile multipartFile : files) {
			File f = File.builder().build();
			f.setMultipartFile(multipartFile, postId);
			uploads.add( f );
		}
		return uploads;
	}
	
	
}
