package ir.sk.eagleeye.licenses.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/26/2020.
 */
public interface CustomChannels {
    // defines the name of the channel
    @Input("inboundOrgChanges")
    SubscribableChannel orgs();
}
