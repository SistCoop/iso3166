package org.sistcoop.iso3166.admin.client.resource;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class CountryCodeProviderTest extends AbstractTest {

    @Test
    public void test() {
        get("https://www.google.com.pe/?gfe_rd=cr&ei=Tp-mVYaNKY_0oQW3wYHwBA");
    }

}
