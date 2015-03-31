package uk.slamplug.test.appsvr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.slamplug.test.appsvr.model.CustomerDetails;
import uk.slamplug.test.appsvr.rest.AppsvrApp1Connector;

@Controller
@SuppressWarnings("UnusedDeclaration")
@RequestMapping("/app")
public class AppsvrApp1Controller {
    private static final Logger logger = LoggerFactory.getLogger(AppsvrApp1Controller.class);

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCustomerDetails(@PathVariable("id") long id) throws IOException {
        logger.info("getCustomerDetails, id {" + id + "}");
        CustomerDetails customerDetails = new AppsvrApp1Connector().getCustomerDetails(id);
        logger.info("Got CustomerDetails [" + customerDetails.toString() + "] from db");
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(customerDetails);
    }

    private String buildUrl(final long id) {
        return getBackendUrl() + "/db/customer/" + id;
    }

    private String getBackendUrl() {
        return "http://" + System.getProperty("db.host", "192.168.56.10") + ":" + System.getProperty("db.port", "9300");
    }
}
