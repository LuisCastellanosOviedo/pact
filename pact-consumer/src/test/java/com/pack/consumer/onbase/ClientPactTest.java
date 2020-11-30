package com.pack.consumer.onbase;

// This sets up a mock server that pretends to be our provider


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pack.consumer.collaborator.OnbaseCollaborator;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.junit.Rule;
import org.junit.Test;


public class ClientPactTest {


  @Rule
  public PactProviderRule provider = new PactProviderRule("Provider", this);

  private LocalDateTime dateTime;
  private String dateResult;


  @Pact(provider = "Provider", consumer = "Consumer from unit test")
  public RequestResponsePact pact(PactDslWithProvider builder) {
    dateTime = LocalDateTime.now();
    dateResult = "2013-08-16T15:31:20+10:00";
    HashMap<String,String> headers = new HashMap();
    headers.put("Content-Type","application/json");
    return builder
        .given("The provider has a valid rquid ")
        .uponReceiving("a request for json data")
        .path("/busicapa-api/onbase/documents/query")
        .method("POST")
        .headers(headers)
        .body("{}")
        .willRespondWith()
        .status(200)
        .headers(headers)
        .body(
            new PactDslJsonBody()
                .stringValue("rqUID", "90124021")
        )

        .toPact();
  }

  @Test
  @PactVerification("Provider")
  public void pactWithOurProvider() throws UnirestException {

    OnbaseCollaborator client = new OnbaseCollaborator();

    HttpResponse<JsonNode> result = client.invokeOnBaseServiceFromApiGateway(provider.getUrl());

    assertEquals(result.getHeaders().size(), 3);

  }
}