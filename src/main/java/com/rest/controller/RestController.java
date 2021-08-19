package com.rest.controller;


import com.rest.data.Response;
import com.rest.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/")
public class RestController {


    @RequestMapping(value = "time", method = RequestMethod.GET)
    public ResponseEntity<Response<String>> getCurrentTime() {
        Response<String> data = new Response<>();
        data.setMessage(Constants.DATA_FETCHED_SUCCESS);
        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        data.setData(formatter.format(now));
        data.setStatusCode(Constants.SUCCESS_CODE);
        return ResponseEntity.ok().body(data);
    }


    @RequestMapping(value = "randomNumber", method = RequestMethod.GET)
    public ResponseEntity<Response<Integer>> randomNumber(
            @RequestParam(value = "lowerBound",required = false) Integer lowerBound,
            @RequestParam("upperBound") Integer upperBound
    ) {

        int lower = 1;

        if(lowerBound != null && lowerBound > 1){
            lower = lowerBound;
        }

        if(upperBound == null || upperBound < 1){
            return badRequest(Constants.UPPER_BOUND_REQUIRED);
        }
        int randomNum = ThreadLocalRandom.current().nextInt(lower, upperBound + 1);
        Response<Integer> data = new Response<>();
        data.setMessage(Constants.DATA_FETCHED_SUCCESS);
        data.setStatusCode(Constants.SUCCESS_CODE);
        data.setData(randomNum);
        return ResponseEntity.ok().body(data);
    }


    private ResponseEntity<Response<Integer>> badRequest(String message){
        Response<Integer> data = new Response<>();
        data.setMessage(message);
        data.setStatusCode(Constants.BAD_REQUEST_CODE);
        return ResponseEntity.badRequest().body(data);
    }

}
