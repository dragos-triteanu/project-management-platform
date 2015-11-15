package ro.management.platform.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import ro.management.platform.model.entities.MailMessage;
import ro.management.platform.model.entities.User;
import ro.management.platform.repository.UserRepository;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by dragos.triteanu on 11/15/15.
 */
@Service
public class MailService {
    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private UserRepository userRepository;


    public void sendEmail(final MailMessage mailMessage){
        LOG.info("Sending mail message from {} to {}",mailMessage.getSender(),mailMessage.getReceiver());
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom(mailMessage.getSender());
        simpleMessage.setTo(mailMessage.getReceiver());
        simpleMessage.setSubject(mailMessage.getSubject());
        simpleMessage.setText(mailMessage.getContent());
        mailSender.send(simpleMessage);
    }

    public void sendEmailToAllAdmins(final MailMessage mailMessage) throws Exception {
        LOG.info("Sending mail message from {} to {}", mailMessage.getSender(), mailMessage.getReceiver());
        List<User> allAdmins = userRepository.getAllAdmins();

        if(allAdmins.isEmpty()){
            throw new RuntimeException("There was an error sending your request");
        }

        String to = "";
        for(User user : allAdmins){
            to += user.getMail();
            to += ";";
        }
        to = to.substring(0,to.lastIndexOf(";"));
        MimeMessage mimeMail = createMimeMail(to, mailMessage.getSender(), mailMessage.getSubject(), mailMessage.getContent());
        mailSender.send(mimeMail);
    }


    private MimeMessage createMimeMail(String to, String from, String subject,String bodyText) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        InternetAddress tAddress = new InternetAddress(to);
        InternetAddress fAddress = new InternetAddress(from);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return  email;
    }



}