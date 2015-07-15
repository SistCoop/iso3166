package org.sistcoop.iso3166.models.jpa;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.CountryCodeProvider;
import org.sistcoop.iso3166.models.search.SearchResultsModel;

public class CountryCodeProviderTest extends AbstractTest {

    @Inject
    private CountryCodeProvider countryCodeProvider;

    @Test
    public void create() {
        CountryCodeModel model = countryCodeProvider.create("PE", "PER", "051", true, true, "Peru", "PERU",
                "Republic of Peru");

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
    }

    @Test
    public void getCountryCodeByAlpha2Code() {
        CountryCodeModel model1 = countryCodeProvider.create("PE", "PER", "051", true, true, "Peru", "PERU",
                "Republic of Peru");

        String alpha2Code = model1.getAlpha2Code();

        CountryCodeModel model2 = countryCodeProvider.findByAlpha2Code(alpha2Code);

        assertThat(model1, is(equalTo(model2)));
    }

    @Test
    public void getCountryCodeByAlpha3Code() {
        CountryCodeModel model1 = countryCodeProvider.create("PE", "PER", "051", true, true, "Peru", "PERU",
                "Republic of Peru");

        String alpha3Code = model1.getAlpha3Code();

        CountryCodeModel model2 = countryCodeProvider.findByAlpha3Code(alpha3Code);

        assertThat(model1, is(equalTo(model2)));
    }

    @Test
    public void getCountryCodeByNumericCode() {
        CountryCodeModel model1 = countryCodeProvider.create("PE", "PER", "051", true, true, "Peru", "PERU",
                "Republic of Peru");

        String numericCode = model1.getNumericCode();

        CountryCodeModel model2 = countryCodeProvider.findByNumericCode(numericCode);

        assertThat(model1, is(equalTo(model2)));
    }

    @Test
    public void getCountryCodes() {
        countryCodeProvider.create("PE", "PER", "051", true, true, "Peru", "PERU", "Republic of Peru");

        SearchResultsModel<CountryCodeModel> results = countryCodeProvider.search();
        for (CountryCodeModel model : results.getModels()) {
            assertThat(model, is(notNullValue()));
        }

        assertThat(results.getTotalSize(), is(1));
        assertThat(results.getModels().size(), is(1));
    }

    @Test
    public void remove() {
        CountryCodeModel model1 = countryCodeProvider.create("PE", "PER", "051", true, true, "Peru", "PERU",
                "Republic of Peru");

        String alpha2Code = model1.getAlpha2Code();
        boolean result = countryCodeProvider.remove(model1);

        CountryCodeModel model2 = countryCodeProvider.findByAlpha2Code(alpha2Code);

        assertThat(result, is(true));
        assertThat(model2, is(nullValue()));
    }

}
