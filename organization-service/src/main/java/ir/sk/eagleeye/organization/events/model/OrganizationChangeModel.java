package ir.sk.eagleeye.organization.events.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/25/2020.
 */
@Data
@RequiredArgsConstructor
public class OrganizationChangeModel {
    private String type;
    /**
     * This is the action that triggered the event. I’ve included the action in
     * the message to give the message consumer more context on how it should process
     * an event
     */
    private String action;

    /**
     * This is the organization ID associated with the event
     */
    private String organizationId;
    /**
     * For Transitions
     * This is the correlation ID the service call that triggered the
     * event. You should always include a correlation ID in your events as it helps
     * greatly with tracking and debugging the flow of messages through your services
     */
    private String correlationId;


    public  OrganizationChangeModel(String type, String action, String organizationId, String correlationId) {
        super();
        this.type   = type;
        this.action = action;
        this.organizationId = organizationId;
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "OrganizationChangeModel [type=" + type +
                ", action=" + action +
                ", orgId="  + organizationId +
                ", correlationId=" + correlationId + "]";
    }
}
