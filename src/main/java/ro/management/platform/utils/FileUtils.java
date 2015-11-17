package ro.management.platform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ro.management.platform.model.dto.ChatMessage;
import ro.management.platform.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

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

    public static ChatMessage saveFileToDiskForChat(final MultipartFile attachment, HttpServletRequest request, final int orderId) throws IOException {
        User user = SessionUtils.GetCurrentUser();
        String savePath = request.getSession().getServletContext().getRealPath("/") + "resources\\chat\\";
        String fileName = new Date().getTime() + "_" + user.getUserId() + "_" + attachment.getOriginalFilename();

        File chatDir = new File(savePath);

        if(!chatDir.exists()){
            chatDir.mkdir();
        }

        String fileLocation = savePath + fileName;
        File file = new File(fileLocation);
        if(!file.exists()){
            file.createNewFile();
        }
        attachment.transferTo(file);
        String filePath = "http://" + request.getServerName()+":"+request.getServerPort()+request.getServletPath() + "?id=" + fileName;

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setLocation(filePath);
        chatMessage.setFileName(attachment.getOriginalFilename());
        chatMessage.setTimestamp(new Date());
        chatMessage.setFrom(user.getUserId());
        chatMessage.setOrderId(orderId);
        return chatMessage;
    }


    public static void getFileAsStream(HttpServletRequest request , HttpServletResponse response, String fileId) throws IOException {
        String savePath = request.getSession().getServletContext().getRealPath("/") + "resources\\chat\\";
        String fileLocation = savePath +  fileId;

        File file = new File(fileLocation);
        InputStream stream = new FileInputStream(file);

        FileCopyUtils.copy(stream,response.getOutputStream());
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
