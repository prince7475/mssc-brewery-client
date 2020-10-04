package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {
    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apiHost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDTO getBeerById(UUID id){
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + id.toString(), BeerDTO.class);
    }

    public URI saveNewBeer(BeerDTO beerDTO){
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1,beerDTO);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

}
