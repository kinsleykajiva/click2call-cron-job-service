package zihub.click2call.cronjobsservices.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zihub.click2call.cronjobsservices.playground.PlaygroundService;

import java.util.Map;

//api/v1/jobs/runhelloworld
@RestController

public class IndexController {
    private final PlaygroundService service;

    @Autowired
    public IndexController(PlaygroundService service) {
        this.service = service;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> index() {


            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of(
                                    "success", true,
                                    "message", "Index  "

                                    )

                    );


    }
    @RequestMapping(value = "/run-hello", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> testing() {
        service.runHelloWorldJob();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of(
                                    "success", true,
                                    "message", "job running  "

                                    )

                    );


    }


}
