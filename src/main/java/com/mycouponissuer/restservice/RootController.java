package com.mycouponissuer.restservice;

import java.net.InetAddress;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class RootApiController {

    @GetMapping("/")
    public String index(@RequestParam(value = "name", defaultValue = "World") String name) {
        String hostname;

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();

            hostname = inetAddress.toString();
        } catch (Exception e) {
            hostname = "unknown";
        }

        return String.format("Hello %s! Greeting from %s", name, hostname);
    }

}