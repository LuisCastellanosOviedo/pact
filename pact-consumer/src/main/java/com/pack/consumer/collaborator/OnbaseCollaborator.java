package com.pack.consumer.collaborator;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.HashMap;


public class OnbaseCollaborator {



  public HttpResponse<JsonNode> invokeOnBaseServiceFromApiGateway(String url ) throws UnirestException {

    HashMap<String,String> headers = new HashMap();
    headers.put("Content-Type","application/json");


    HttpResponse<JsonNode> onbaseQuery = Unirest.post(url+"/busicapa-api/onbase/documents/query")
        .headers(headers)
        .body("{}")
        .asJson();

    System.out.println("***************************************************");
    System.out.println();

    System.out.println(onbaseQuery);

    System.out.println();
    System.out.println("***************************************************");

    return onbaseQuery;

  }
}
