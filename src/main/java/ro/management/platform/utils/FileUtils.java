package ro.management.platform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import ro.management.platform.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by Dragos on 11/16/2015.
 */
public class FileUtils {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public static String saveFileToDisk(MultipartFile attachment, HttpServletRequest request) throws IOException {
        User user = SessionUtils.GetCurrentUser();
        String savePath = request.getSession().getServletContext().getRealPath("/") + "resources\\images\\";
        String fileName = user.getUserId() + attachment.getOriginalFilename();
        String fileLocation = savePath + fileName;
        File file = new File(fileLocation);
        if(!file.exists()){
            file.createNewFile();
        }

        attachment.transferTo(file);
        return fileLocation;
    }

    public static InputStream getFileAsStream(String fileLocation) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(fileLocation);
        return inputStream;
    }

    public static void deleteFile(String fileLocation){
        try{
            File file = new File(fileLocation);
            file.delete();
        }catch (Exception e){
            LOG.error("Could not delete file with location: {}",fileLocation,e);
        }
    }


}
