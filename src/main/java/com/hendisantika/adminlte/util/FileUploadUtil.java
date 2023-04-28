package com.hendisantika.adminlte.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(String uploadDir, String fileName,
        MultipartFile multipartFile) throws IOException {
		String userDirectory = Paths.get("")
                .toAbsolutePath()
                .toString();
        Path uploadPath = Paths.get(userDirectory+uploadDir);
        
        System.out.println("$$$$$$$$$$$\ncurrent path : \n"+userDirectory+"\n$$$$$$$$$$$$$$$");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
        	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&\nFileUploadUtil filepath:\n"+filePath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) { 
        	System.out.println("***************\nFileUploadUtil Error:\n"+ioe);
            throw new IOException("Could not save file: " + fileName, ioe);
        }      
    }
}
