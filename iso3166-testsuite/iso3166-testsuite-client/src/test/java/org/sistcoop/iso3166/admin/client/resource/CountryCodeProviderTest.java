package org.sistcoop.iso3166.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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
        CountryCodeRepresentation representation = new CountryCodeRepresentation();
        representation.setAlpha2Code("XX");
        representation.setAlpha3Code("ABC");
        representation.setNumericCode("123");
        representation.setShortNameEn("Peru");
        representation.setShortNameUppercaseEn("PERU");
        representation.setFullNameEn("Republica del Peru");
        representation.setIndependent(true);
        representation.setStatus("oficially-assigned");

        @SuppressWarnings("unused")
        Jsend jsend = given().contentType(ContentType.JSON).body(representation).log().everything().expect()
                .statusCode(201).log().ifError().when().post(baseURI).as(Jsend.class);

        @SuppressWarnings("rawtypes")
        SearchResultsRepresentation result = given().contentType(ContentType.JSON).log().everything()
                .expect().statusCode(200).log().ifError().when().get(baseURI)
                .as(SearchResultsRepresentation.class);

        // assert
        assertThat(result, is(notNullValue()));
        assertThat(result.getTotalSize(), is(1l));
        assertThat(result.getItems().size(), is(1));
    }
}
