package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.customer", ignoreUnknownFields = false)
public class CustomerClient {
    private final String CUSTOMER_PATH_V1= "/api/v1/customer";
    private String apiHost;
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public CustomerDTO getCustomerById(UUID id) {
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + "/" + id.toString(),CustomerDTO.class);
    }

    public URI saveNewCustomer(CustomerDTO customerDTO){
        return restTemplate.postForLocation(apiHost + CUSTOMER_PATH_V1,customerDTO);
    }

    public void updateCustomer(UUID id, CustomerDTO customerDTO){
        restTemplate.put(apiHost + CUSTOMER_PATH_V1 + "/" + id.toString(),customerDTO);
    }

    public void deleteCustomer(UUID id){
        restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + "/" + id.toString());
    }

}
