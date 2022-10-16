package com.openedsource.pizzastore.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static ResponseEntity<Object> buildMessageReponse(HttpStatus status, String message){
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", message);
        return ResponseEntity.status(status).body(responseData);
    }
}
