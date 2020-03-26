package ir.sk.eagleeye.organization.events.source;

import ir.sk.eagleeye.organization.events.model.OrganizationChangeModel;
import ir.sk.microservice.utils.UserContext;
import ir.sk.microservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * The message publication code
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/25/2020.
 */
@Component
public class SimpleSourceBean {

    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    // Spring Cloud Stream will inject a Source interface implementation for use by the service
    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishOrgChange(String action,String orgId){
        logger.debug("Sending Kafka message {} for Organization Id: {}", action, orgId);
        OrganizationChangeModel change =  new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                orgId,
                UserContextHolder.getContext().getCorrelationId()); // The message to be published is a Java POJO

        // When you’re ready to send the message, use the send() method from a channel defined on the Source class
        source.output().send(MessageBuilder.withPayload(change).build());
    }
}
