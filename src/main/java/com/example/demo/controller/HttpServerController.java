package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class HttpServerController {

    @GetMapping("/startHttpServer/{libraryLocation}")
    public String startHttpServer(@PathVariable String libraryLocation, Model model) {
        try {
            // Construct the command to start http-server
            String command = "http-server " + libraryLocation;

            // Start the http-server using the command
            Process process = Runtime.getRuntime().exec(command);

            // Provide information back to the Thymeleaf template
            model.addAttribute("libraryLocation", libraryLocation);
            model.addAttribute("serverStarted", true); // To indicate that the server has started

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("libraryLocation", libraryLocation);
            model.addAttribute("serverStarted", false); // To indicate that server start failed
        }

        return "http_server_started";
    }
}
