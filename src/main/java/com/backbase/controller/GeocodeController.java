package com.backbase.controller;

import com.backbase.model.GeocodeResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

/**
 * Created by chauhan on 6/6/17.
 */
@Controller
@RequestMapping("/geocode/{address}")
public class GeocodeController {
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody GeocodeResponse code(@PathVariable(name = "address") String base64Add) {
        if (StringUtils.isEmpty(base64Add.trim())) {
            return new GeocodeResponse("ERR");
        }
        String add = new String(Base64.getDecoder().decode(base64Add));
        System.out.println(add);
        return new GeocodeResponse("SUCCESS");
    }
}
