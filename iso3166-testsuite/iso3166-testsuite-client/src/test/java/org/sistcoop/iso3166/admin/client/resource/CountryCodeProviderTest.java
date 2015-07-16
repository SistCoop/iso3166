package org.sistcoop.iso3166.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.sistcoop.iso3166.Jsend;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;
import org.sistcoop.iso3166.representations.idm.search.SearchResultsRepresentation;

import com.jayway.restassured.http.ContentType;

public class CountryCodeProviderTest extends AbstractTest {

    private final String baseURI = "http://127.0.0.1:8080/test/rest/countryCodes";

    @Test
    public void create() {
        CountryCodeRepresentation representation = new CountryCodeRepresentation();
        representation.setAlpha2Code("XX");
        representation.setAlpha3Code("ABC");
        representation.setNumericCode("123");
        representation.setShortNameEn("Peru");
        representation.setShortNameUppercaseEn("PERU");
        representation.setFullNameEn("Republica del Peru");
        representation.setIndependent(true);
        representation.setStatus("oficially-assigned");

        Jsend jsend = given().contentType(ContentType.JSON).body(representation).log().everything().expect()
                .statusCode(201).log().ifError().when().post(baseURI).as(Jsend.class);

        assertThat(jsend, is(notNullValue()));
        assertThat(jsend.getId(), is(notNullValue()));
    }

    @Test
    public void search() {
        CountryCodeRepresentation representation1 = new CountryCodeRepresentation();
        representation1.setAlpha2Code("PE");
        representation1.setAlpha3Code("PER");
        representation1.setNumericCode("051");
        representation1.setShortNameEn("Peru");
        representation1.setShortNameUppercaseEn("PERU");
        representation1.setFullNameEn("Republica del Peru");
        representation1.setIndependent(true);
        representation1.setStatus("oficially-assigned");

        CountryCodeRepresentation representation2 = new CountryCodeRepresentation();
        representation2.setAlpha2Code("EU");
        representation2.setAlpha3Code("EUR");
        representation2.setNumericCode("090");
        representation2.setShortNameEn("Estado Unidos");
        representation2.setShortNameUppercaseEn("ESTADOS UNIDOS");
        representation2.setFullNameEn("Republica de Estados Unidos");
        representation2.setIndependent(true);
        representation2.setStatus("oficially-assigned");

        @SuppressWarnings("unused")
        Jsend jsend1 = given().contentType(ContentType.JSON).body(representation1).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(Jsend.class);
        @SuppressWarnings("unused")
        Jsend jsend2 = given().contentType(ContentType.JSON).body(representation2).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(Jsend.class);

        @SuppressWarnings("rawtypes")
        SearchResultsRepresentation result = given().contentType(ContentType.JSON).log().everything()
                .expect().statusCode(200).log().ifError().when().get(baseURI)
                .as(SearchResultsRepresentation.class);

        // assert
        assertThat(result, is(notNullValue()));
        assertThat(result.getTotalSize(), is(2l));
        assertThat(result.getItems().size(), is(2));
    }

    @Test
    public void searchFiltertext() {
        CountryCodeRepresentation representation1 = new CountryCodeRepresentation();
        representation1.setAlpha2Code("PE");
        representation1.setAlpha3Code("PER");
        representation1.setNumericCode("051");
        representation1.setShortNameEn("Peru");
        representation1.setShortNameUppercaseEn("PERU");
        representation1.setFullNameEn("Republica del Peru");
        representation1.setIndependent(true);
        representation1.setStatus("oficially-assigned");

        CountryCodeRepresentation representation2 = new CountryCodeRepresentation();
        representation2.setAlpha2Code("EU");
        representation2.setAlpha3Code("EUR");
        representation2.setNumericCode("090");
        representation2.setShortNameEn("Estados Unidos");
        representation2.setShortNameUppercaseEn("ESTADOS UNIDOS");
        representation2.setFullNameEn("Republica de Estados Unidos");
        representation2.setIndependent(true);
        representation2.setStatus("oficially-assigned");

        @SuppressWarnings("unused")
        Jsend jsend1 = given().contentType(ContentType.JSON).body(representation1).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(Jsend.class);
        @SuppressWarnings("unused")
        Jsend jsend2 = given().contentType(ContentType.JSON).body(representation2).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(Jsend.class);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("filterText", "peru");
        parameters.put("page", "1");
        parameters.put("pageSize", "10");

        @SuppressWarnings("rawtypes")
        SearchResultsRepresentation result = given().parameters(parameters).contentType(ContentType.JSON)
                .log().everything().expect().statusCode(200).log().ifError().when().get(baseURI)
                .as(SearchResultsRepresentation.class);

        // assert
        assertThat(result, is(notNullValue()));
        assertThat(result.getTotalSize(), is(1l));
        assertThat(result.getItems().size(), is(1));
    }
}
