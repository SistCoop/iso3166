package org.sistcoop.iso3166.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Test;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class CountryCodeProviderTest extends AbstractTest {

    private final String baseURI = "http://127.0.0.1:8080/test/rest/countryCodes";

    @Test
    public void create() {
        // create
        JsonObject jsonRep = Json.createObjectBuilder().add("alpha2Code", "XX").add("alpha3Code", "XXX")
                .add("numericCode", "000").add("independent", true).add("status", "officially-assigned")
                .add("shortNameEn", "Peru").add("shortNameUppercaseEn", "PERU")
                .add("fullNameEn", "Republica del Peru").build();
        Response response = given().contentType("application/json").body(jsonRep.toString()).when()
                .post(baseURI);

        // extract
        JsonPath jsonPath = response.getBody().jsonPath();
        String id = jsonPath.getObject("id", String.class);

        // assert
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(201));

        assertThat(id, is(notNullValue()));
    }

    @Test
    public void search() {
        // create
        JsonObject jsonRep = Json.createObjectBuilder().add("alpha2Code", "XX").add("alpha3Code", "XXX")
                .add("numericCode", "000").add("independent", true).add("status", "officially-assigned")
                .add("shortNameEn", "Peru").add("shortNameUppercaseEn", "PERU")
                .add("fullNameEn", "Republica del Peru").build();
        given().contentType("application/json").body(jsonRep.toString()).when().post(baseURI);

        // search
        Response response = given().contentType("application/json").when().get(baseURI);

        // extract
        JsonPath jsonPath = response.getBody().jsonPath();
        long totalSize = jsonPath.getObject("totalSize", Long.class);
        List<CountryCodeRepresentation> representations = jsonPath.getList("items");

        // assert
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(200));

        assertThat(totalSize, is(1l));
        assertThat(representations.size(), is(1));
    }
}
