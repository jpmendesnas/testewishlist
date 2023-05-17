package com.wishlist.app.steps;

import com.wishlist.app.api.controller.dto.RequestProductDTO;
import com.wishlist.app.api.controller.dto.WishlistDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;
import java.util.HashSet;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WishlistSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<Object> response;

    private String productId = "prod1";
    private String clientId = "cli1";

    @Given("a product with id {string} and a client with id {string}")
    public void aProductWithIdAndAClientWithId(String productId, String clientId) {
        this.productId = productId;
        this.clientId = clientId;
    }

    @When("I send a POST request to {string} with the product and client ids")
    public void iSendAPostRequestToWithTheProductAndClientIds(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        WishlistDTO wishlist = new WishlistDTO();
        wishlist.setProductIds(new HashSet<>());
        wishlist.getProductIds().add(productId);
        wishlist.setClientId(clientId);

        HttpEntity<WishlistDTO> requestEntity = new HttpEntity<>(wishlist, headers);

        response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
    }

    @Then("the response status should be {int} CREATED")
    public void theResponseStatusShouldBeCreated(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCodeValue();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Given("a product with id {string} in the wishlist of a client with id {string}")
    public void aProductWithIdInWishlistAddOfClientWithId(String productId, String clientId) {
        // Chamar o endpoint de adicionar produto à wishlist
        String url = "/wishlist";
        RequestProductDTO requestProductDTO = new RequestProductDTO();
        requestProductDTO.setClientId(clientId);
        requestProductDTO.setProductId(productId);
        ResponseEntity<Object> response = restTemplate.postForEntity(url, requestProductDTO, Object.class);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @When("I send a DELETE request to {string}")
    public void iSendADeleteRequestTo(String url) {
        response = restTemplate.exchange(url, HttpMethod.DELETE, null, Object.class);
    }

    @Then("the response status should be {int} NO CONTENT")
    public void theResponseStatusShouldBeNoContent(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCodeValue();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Given("a client with id {string} with a wishlist")
    public void aClientWithIdWithAWishlist(String clientId) {
        this.clientId = clientId;

        // Lógica para preparar o cenário do cliente com a wishlist
        // Por exemplo, adicionar produtos à wishlist do cliente
    }

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String url) {
        response = restTemplate.getForEntity(url, Object.class);
    }

    @Then("the response status should be {int} OK")
    public void theResponseStatusShouldBeOK(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCodeValue();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response status should be {int} OK and body should be true")
    public void theResponseStatusShouldBeOKAndBodyShouldBeTrue(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCodeValue();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        boolean responseBody = (boolean) response.getBody();
        Assert.assertTrue("Expected the body of the response to be true", responseBody);
    }
}
