package ir.sk.eagleeye.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import ir.sk.eagleeye.zuulsvr.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * a pre-filter that will ensure
 * that every request flowing from Zuul has a correlation ID associated with it. A
 * correlation ID is a unique ID that gets carried across all the microservices that
 * are executed when carrying out a customer request. A correlation ID allows you
 * to trace the chain of events that occur as a call goes through a series of
 * microservice calls
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/13/2020.
 */
@Component
public class TrackingFilter extends ZuulFilter { /*All Zuul filters must extend the ZuulFilter class and override four methods: filterType(), filterOrder(), shouldFilter(), and run().*/

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;
    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    @Autowired
    FilterUtils filterUtils;

    @Autowired
    private ServiceConfig serviceConfig;

    /**
     * used to tell Zuul whether the filter
     * is a pre-, route, or post filter
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    /**
     * The filterOrder() method returns an integer
     * value indicating what order Zuul should send
     * requests through the different filter types
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    /**
     * The shouldFilter() method returns
     * a Boolean indicating whether or
     * not the filter should be active
     *
     * @return
     */
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent() {
        if (filterUtils.getCorrelationId() != null) {
            return true;
        }

        return false;
    }

    /**
     * The helper methods that actually
     * check if the tmx-correlation-id is
     * present and can also generate a
     * correlation ID GUIID value
     *
     * @return
     */
    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * The run() method is the code
     * that is executed every time a
     * service passes through the
     * filter. In your run() function,
     * you check to see if the tmxcorrelation-
     * id is present and
     * if it isn’t, you generate a
     * correlation value and set the
     * tmx-correlation-id HTTP
     *
     * @return
     */
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();

        if (isCorrelationIdPresent()) {
            logger.debug("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
            logger.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
        }

        System.out.println("The organization id from the token is : " + getOrganizationId());
        filterUtils.setOrgId(getOrganizationId());
        logger.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());
        return null;
    }

    /**
     * parse out a custom field in the JWT token
     *
     * @return
     */
    private String getOrganizationId() {

        String result = "";
        if (filterUtils.getAuthToken() != null) {

            // Parse out the token out of the Authorization HTTP header
            String authToken = filterUtils.getAuthToken().replace("Bearer ", "");
            try {
                // Use JWTS class to parse out the token, passing in the signing key used to sign the token
                Claims claims = Jwts.parser()
                        .setSigningKey(serviceConfig.getJwtSigningKey().getBytes("UTF-8"))
                        .parseClaimsJws(authToken).getBody();
                // Pull the organizationId out of the JavaScript token
                result = (String) claims.get("organizationId");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
