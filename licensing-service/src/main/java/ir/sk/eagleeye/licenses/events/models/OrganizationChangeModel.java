package ir.sk.eagleeye.licenses.events.models;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/25/2020.
 */
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


    public OrganizationChangeModel(String type, String action, String organizationId, String correlationId) {
        super();
        this.type   = type;
        this.action = action;
        this.organizationId = organizationId;
        this.correlationId = correlationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }


    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
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
