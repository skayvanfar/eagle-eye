package ir.sk.eagleeye.zuulsvr.model;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/25/2020.
 */
public class UserInfo {

    String organizationId;
    String userId;

    public String getOrganizationId() {
        return this.organizationId;
    }


    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
