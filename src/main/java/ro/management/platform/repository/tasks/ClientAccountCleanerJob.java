package ro.management.platform.repository.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.management.platform.model.entities.Client;
import ro.management.platform.repository.UserRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Scheduled task that will delete all the CLIENT type users in the database, that have not logged in for allowedNumberOfDaysSinceCreation days.
 * Created by Dragos on 11/10/2015.
 */
@Component
@PropertySource("classpath:conf/application.properties")
public class ClientAccountCleanerJob {
    private static final Logger LOG = LoggerFactory.getLogger(ClientAccountCleanerJob.class);

    @Value("${client.numberOrDaysSinceCreation}")
    private int allowedNumberOfDaysSinceCreation;

    @Value("${client.numberOfDaysSinceLastLogin}")
    private int allowedNumberOrDaysSinceLastLogin;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "${client.removal.task.cron}")
    public void cleanClientAccounts(){
        LOG.info("STARTED cleanup job, for removing unused user accounts");
        List<Client> clients = userRepository.retrieveClients();
        for(Client client : clients){
            long currentDateTime = new Date().getTime();
            long createdOnTime = client.getCreatedOn().getTime();
            long daysBetweenCreation = TimeUnit.DAYS.convert(currentDateTime - createdOnTime, TimeUnit.MILLISECONDS);

            if(daysBetweenCreation >= allowedNumberOfDaysSinceCreation){
                Timestamp lastLogin = client.getLastLogin();
                if(lastLogin == null){
                    LOG.info("CLIENT with userId={} was created on={} but has never logged in. Deleting account",client.getUserId(),client.getCreatedOn());
                    userRepository.deleteClient(client);
                }else{
                    long daysSinceLastLogin = TimeUnit.DAYS.convert(currentDateTime - lastLogin.getTime(), TimeUnit.MILLISECONDS);
                    if(daysSinceLastLogin > allowedNumberOrDaysSinceLastLogin){
                        LOG.info("CLIENT with userID={} has not logged in for {} days. Removing account",client.getUserId(),allowedNumberOrDaysSinceLastLogin);
                        userRepository.deleteClient(client);
                    }
                }
            }
        }
    }
}
