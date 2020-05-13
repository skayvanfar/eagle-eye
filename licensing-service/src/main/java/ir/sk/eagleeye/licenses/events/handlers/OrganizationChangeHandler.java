package ir.sk.eagleeye.licenses.events.handlers;

import ir.sk.eagleeye.licenses.events.CustomChannels;
import ir.sk.eagleeye.licenses.events.models.OrganizationChangeModel;
import ir.sk.eagleeye.licenses.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * the message handling code that you’ll
 * use with the inboundOrgChanges channel
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/26/2020.
 */
// Move the @EnableBindings out of the Application.java class and into
//the OrganizationChangeHandler class. This time instead of using
//Sink.class, use your CustomChannels class as the parameter to pass
// @EnableBinding(CustomChannels.class)
public class OrganizationChangeHandler {

    @Autowired
    private OrganizationRedisRepository organizationRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationChangeHandler.class);

    // With the @StreamListener annotation, you passed in the name of
    //your channel, “inboundOrgChanges”, instead of using Sink.INPUT
    @StreamListener("inboundOrgChanges")
    public void loggerSink(OrganizationChangeModel orgChange) {
        logger.debug("Received a message of type " + orgChange.getType());
        switch(orgChange.getAction()){
            case "GET":
                logger.debug("Received a GET event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "SAVE":
                logger.debug("Received a SAVE event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "UPDATE":
                logger.debug("Received a UPDATE event from the organization service for organization id {}", orgChange.getOrganizationId());
                organizationRedisRepository.deleteOrganization(orgChange.getOrganizationId());
                break;
            case "DELETE":
                logger.debug("Received a DELETE event from the organization service for organization id {}", orgChange.getOrganizationId());
                organizationRedisRepository.deleteOrganization(orgChange.getOrganizationId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the organization service of type {}", orgChange.getType());
                break;

        }
    }
}
