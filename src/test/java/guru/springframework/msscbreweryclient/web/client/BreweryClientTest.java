package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        BeerDTO dto = client.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void saveNewBeer(){
        BeerDTO dto = BeerDTO.builder().build();
        URI uri = client.saveNewBeer(dto);
        assertNotNull(uri);
    }

    @Test
    void updateBeerTest(){
        BeerDTO dto = BeerDTO.builder().build();
        client.updateBeer(UUID.randomUUID(),dto);
    }

    @Test
    void deleteBeerTest(){
        client.deleteBeer(UUID.randomUUID());
    }


}