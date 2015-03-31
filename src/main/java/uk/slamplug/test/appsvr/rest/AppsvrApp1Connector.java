package uk.slamplug.test.appsvr.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import uk.slamplug.test.appsvr.model.CustomerDetails;

import java.io.IOException;

public class AppsvrApp1Connector {

    private static final Logger logger = LoggerFactory.getLogger(AppsvrApp1Connector.class);

    public CustomerDetails getCustomerDetails(final long id) throws IOException {
        logger.info("getCustomerDetails, id {" + id + "}");
        final String url = buildUrl(id);
        logger.info("getCustomerDetails, url {" + url + "}");
        return new RestTemplate().getForObject(url, CustomerDetails.class);
    }

    private String buildUrl(final long id) {
        return getBackendUrl() + "/db/customer/" + id;
    }

    private String getBackendUrl() {
        return "http://" + System.getProperty("db.host", "192.168.56.10") + ":" + System.getProperty("db.port", "9300");
    }
}
