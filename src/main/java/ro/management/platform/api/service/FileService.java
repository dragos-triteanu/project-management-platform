package ro.management.platform.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;

import org.springframework.web.multipart.MultipartFile;
import ro.management.platform.api.exceptions.ResourceNotFoundException;
import ro.management.platform.model.dto.ChatMessage;
import ro.management.platform.model.dto.StoredFile;
import ro.management.platform.model.entities.Consultant;
import ro.management.platform.repository.MessageRepository;
import ro.management.platform.repository.UserRepository;
import ro.management.platform.utils.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("api/service")
public class FileService {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/cv", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF(@RequestParam("id") final int id) throws IOException, DocumentException {
		
		Consultant consultant = userRepository.retrieveConsultantByUid(id);
		if(consultant == null){
			throw new ResourceNotFoundException();
		}
		byte[] contents = consultant.getCv();
		
		if(contents == null){
			throw new ResourceNotFoundException();
		}

         HttpHeaders headers = new HttpHeaders();
         headers.add("content-disposition", "inline");
         headers.add("content-type", "application/pdf");
         ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
         return responseEntity;
	}

	@RequestMapping(value = "/file", method= RequestMethod.POST)
	public ResponseEntity<ChatMessage> uploadFile(HttpServletRequest request ,
											      @RequestParam MultipartFile uploadedFile,
												  @RequestParam("orderId") int orderId) throws IOException {
		ChatMessage storedFile = FileUtils.saveFileToDiskForChat(uploadedFile, request,orderId);
		return new ResponseEntity<ChatMessage>(storedFile,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/file", method= RequestMethod.GET)
	public void getFile(HttpServletRequest request ,
						HttpServletResponse response,
						@RequestParam("id") String fileId) throws IOException {

		response.setHeader("content-disposition", "attachment; filename=" + fileId);
		FileUtils.getFileAsStream(request,response, fileId);
	}


}
