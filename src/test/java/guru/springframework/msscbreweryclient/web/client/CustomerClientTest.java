package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerDTO dto = customerClient.getCustomerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void saveNewCustomer(){
        CustomerDTO dto = CustomerDTO.builder().build();
        URI response = customerClient.saveNewCustomer(dto);
        assertNotNull(response);
    }

    @Test
    void updateCustomer(){
        CustomerDTO dto = CustomerDTO.builder().build();
        customerClient.updateCustomer(UUID.randomUUID(),dto);
    }

    @Test
    void deleteCustomer(){
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}