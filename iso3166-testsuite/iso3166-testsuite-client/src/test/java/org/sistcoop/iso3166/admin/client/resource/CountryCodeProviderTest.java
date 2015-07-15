package org.sistcoop.iso3166.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Test;

import com.jayway.restassured.response.Response;

public class CountryCodeProviderTest extends AbstractTest {

    private final String baseURI = "http://127.0.0.1:8080/test/rest/countryCodes";

    @Test
    public void create() {

        JsonObject jsonRep = Json.createObjectBuilder().add("alpha2Code", "XX").add("alpha3Code", "XXX")
                .add("numericCode", "000").add("independent", true).add("status", "officially-assigned")
                .add("shortNameEn", "Peru").add("shortNameUppercaseEn", "PERU")
                .add("fullNameEn", "Republica del Peru").build();

        Response response = given().contentType("application/json").body(jsonRep.toString()).when()
                .post(baseURI);

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(201));
    }

    @Test
    public void search() {
        JsonObject jsonRep = Json.createObjectBuilder().add("alpha2Code", "XX").add("alpha3Code", "XXX")
                .add("numericCode", "000").add("independent", true).add("status", "officially-assigned")
                .add("shortNameEn", "Peru").add("shortNameUppercaseEn", "PERU")
                .add("fullNameEn", "Republica del Peru").build();
        given().contentType("application/json").body(jsonRep.toString()).when().post(baseURI);

        Response response = given().contentType("application/json").when().get(baseURI);

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(200));
    }
}
