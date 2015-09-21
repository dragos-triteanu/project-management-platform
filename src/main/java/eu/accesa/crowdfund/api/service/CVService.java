package eu.accesa.crowdfund.api.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.repository.ConsultantRepository;

@Controller
@RequestMapping("api/service")
public class CVService {

	@Autowired
	private ConsultantRepository consultantRepository;
	
	
	@RequestMapping(value="/cv", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF(HttpServletRequest request,HttpServletResponse response,@RequestParam("id") final int id) throws IOException, DocumentException {
		
		
		Consultant consultant = consultantRepository.retrieveConsultantByUid(id);
		byte[] contents = consultant.getCv();

         HttpHeaders headers = new HttpHeaders();
         headers.add("content-disposition", "inline");
         headers.add("content-type", "application/pdf");
         ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
         return responseEntity;
	}
	
}
