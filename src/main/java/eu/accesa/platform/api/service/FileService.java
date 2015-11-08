package eu.accesa.platform.api.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;

import eu.accesa.platform.api.exceptions.ResourceNotFoundException;
import eu.accesa.platform.model.entities.Consultant;
import eu.accesa.platform.repository.UserRepository;

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
	
}
