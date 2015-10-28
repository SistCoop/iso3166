package org.sistcoop.iso3166.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;

import com.jayway.restassured.http.ContentType;

public class CountryCodeProviderTest extends AbstractTest {

    private final String baseURI = "http://127.0.0.1:8080/test/rest/countryCodes";

    @Test
    public void create() {
        CountryCodeRepresentation rep = new CountryCodeRepresentation();
        rep.setAlpha2Code("XX");
        rep.setAlpha3Code("ABC");
        rep.setNumericCode("123");
        rep.setShortNameEn("Peru");
        rep.setShortNameUppercaseEn("PERU");
        rep.setFullNameEn("Republica del Peru");
        rep.setIndependent(true);
        rep.setStatus("oficially-assigned");

        CountryCodeRepresentation repCreated = given().contentType(ContentType.JSON).body(rep).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(CountryCodeRepresentation.class);

        assertThat(repCreated, is(notNullValue()));
        assertThat(repCreated.getAlpha2Code(), is(notNullValue()));
        assertThat(repCreated.getAlpha3Code(), is(notNullValue()));
        assertThat(repCreated.getNumericCode(), is(notNullValue()));
    }

    /*
     * @Test public void search() { CountryCodeRepresentation rep1 = new
     * CountryCodeRepresentation(); rep1.setAlpha2Code("PE");
     * rep1.setAlpha3Code("PER"); rep1.setNumericCode("051");
     * rep1.setShortNameEn("Peru"); rep1.setShortNameUppercaseEn("PERU");
     * rep1.setFullNameEn("Republica del Peru"); rep1.setIndependent(true);
     * rep1.setStatus("oficially-assigned");
     * 
     * CountryCodeRepresentation rep2 = new CountryCodeRepresentation();
     * rep2.setAlpha2Code("EU"); rep2.setAlpha3Code("EUR");
     * rep2.setNumericCode("090"); rep2.setShortNameEn("Estado Unidos");
     * rep2.setShortNameUppercaseEn("ESTADOS UNIDOS"); rep2.setFullNameEn(
     * "Republica de Estados Unidos"); rep2.setIndependent(true);
     * rep2.setStatus("oficially-assigned");
     * 
     * @SuppressWarnings("unused") CountryCodeRepresentation repCreated1 =
     * given().contentType(ContentType.JSON).body(rep1).log()
     * .everything().expect().statusCode(201).log().ifError().when().post(
     * baseURI) .as(CountryCodeRepresentation.class);
     * 
     * @SuppressWarnings("unused") CountryCodeRepresentation repCreated2 =
     * given().contentType(ContentType.JSON).body(rep2).log()
     * .everything().expect().statusCode(201).log().ifError().when().post(
     * baseURI) .as(CountryCodeRepresentation.class);
     * 
     * @SuppressWarnings("rawtypes") SearchResultsRepresentation result =
     * given().contentType(ContentType.JSON).log().everything().expect()
     * .statusCode(200).log().ifError().when().get(baseURI).as(
     * SearchResultsRepresentation.class);
     * 
     * // assert assertThat(result, is(notNullValue()));
     * assertThat(result.getTotalSize(), is(2));
     * assertThat(result.getItems().size(), is(2)); }
     * 
     * @Test public void searchFiltertext() { CountryCodeRepresentation rep1 =
     * new CountryCodeRepresentation(); rep1.setAlpha2Code("PE");
     * rep1.setAlpha3Code("PER"); rep1.setNumericCode("051");
     * rep1.setShortNameEn("Peru"); rep1.setShortNameUppercaseEn("PERU");
     * rep1.setFullNameEn("Republica del Peru"); rep1.setIndependent(true);
     * rep1.setStatus("oficially-assigned");
     * 
     * CountryCodeRepresentation rep2 = new CountryCodeRepresentation();
     * rep2.setAlpha2Code("EU"); rep2.setAlpha3Code("EUR");
     * rep2.setNumericCode("090"); rep2.setShortNameEn("Estados Unidos");
     * rep2.setShortNameUppercaseEn("ESTADOS UNIDOS"); rep2.setFullNameEn(
     * "Republica de Estados Unidos"); rep2.setIndependent(true);
     * rep2.setStatus("oficially-assigned");
     * 
     * @SuppressWarnings("unused") CountryCodeRepresentation repCreated1 =
     * given().contentType(ContentType.JSON).body(rep1).log()
     * .everything().expect().statusCode(201).log().ifError().when().post(
     * baseURI) .as(CountryCodeRepresentation.class);
     * 
     * @SuppressWarnings("unused") CountryCodeRepresentation repCreated2 =
     * given().contentType(ContentType.JSON).body(rep2).log()
     * .everything().expect().statusCode(201).log().ifError().when().post(
     * baseURI) .as(CountryCodeRepresentation.class);
     * 
     * Map<String, String> parameters = new HashMap<String, String>();
     * parameters.put("filterText", "peru"); parameters.put("page", "1");
     * parameters.put("pageSize", "10");
     * 
     * @SuppressWarnings("rawtypes") SearchResultsRepresentation result =
     * given().parameters(parameters).contentType(ContentType.JSON)
     * .log().everything().expect().statusCode(200).log().ifError().when().get(
     * baseURI) .as(SearchResultsRepresentation.class);
     * 
     * // assert assertThat(result, is(notNullValue()));
     * assertThat(result.getItems().size(), greaterThan(1));
     * assertThat(result.getTotalSize(), greaterThan(1)); }
     */

}
