package com.backbase.controllers;

import org.apache.camel.*;
import org.apache.camel.builder.BuilderSupport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by chauhan on 6/6/17.
 */
@Controller
@RequestMapping("/geocode/{address}")
public class GeocodeController {

    private final Logger logger = LoggerFactory.getLogger(GeocodeController.class);

    @Autowired
    @EndpointInject(uri="direct:start")
    private ProducerTemplate template;

    @Value("${google.map.api.key}")
    private String apiKey;



    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String code(@PathVariable(name = "address") String address) {
        if (StringUtils.isEmpty(address.trim())) {
            return new String("{\"status\": \"ERR\"}");
        }
        final Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("address", address);
        headers.put("apiKey", apiKey);

        Exchange exchange = template.send("direct:start", new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setHeaders(headers);

            }
        });

        String out = exchange.getOut().getBody(String.class);
        return out;
    }

}
