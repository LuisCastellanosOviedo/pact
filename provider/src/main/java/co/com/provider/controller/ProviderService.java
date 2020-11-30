package co.com.provider.controller;

import co.com.provider.model.Rquid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderService {

  @PostMapping(value = "/busicapa-api/onbase/documents/query",produces = "application/json")
  public Rquid providerService(){
    return Rquid.builder().rqUID("90124021").build();
  }


}
