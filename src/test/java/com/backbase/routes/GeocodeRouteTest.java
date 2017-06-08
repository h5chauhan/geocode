package com.backbase.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import com.backbase.routes.GecodeRoute;
/**
 * Created by chauhan on 6/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GeocodeRouteTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Value("${google.map.api.key}")
    private String apiKey;

    @Test
    public void testRoute() {

        final String address = "535 Mission St San Francisco CA 94105";
        final Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("address", address);
        headers.put("apiKey", apiKey);
        Exchange exchange = producerTemplate.send("direct:start", new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setHeaders(headers);

            }
        });

        Assert.assertTrue(exchange.getOut().getBody(String.class).contains("\"status\":\"OK\""));
    }
}
