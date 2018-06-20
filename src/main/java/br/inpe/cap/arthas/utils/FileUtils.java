package br.inpe.cap.arthas.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class FileUtils {

	
	public static void createJavaFiles(String commit,Map<String, String> sources, String dirPath) {
		createDir(dirPath + File.separator + commit);
		String commitDir = dirPath + File.separator + commit;
		sources.forEach((className,source)->{
			if(className.contains(".java")) {
				int index = className.lastIndexOf("/");
				if(index != -1)
					className = className.substring(index+1);
				Path file = Paths.get(commitDir + File.separator + className);
				try {
					Files.write(file, new ArrayList<String>(Arrays.asList(source)), Charset.forName("UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void createDir(String dirPath) {
		Path path = Paths.get(dirPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
}
