package org.sistcoop.iso3166.models.utils.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.CountryCodeProvider;
import org.sistcoop.iso3166.models.jpa.AbstractTest;
import org.sistcoop.iso3166.models.utils.ModelToRepresentation;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;

public class ModelToRepresentationTest extends AbstractTest {

    @Inject
    private CountryCodeProvider countryCodeProvider;

    @Test
    public void commit() {
        CountryCodeModel model1 = countryCodeProvider.create("PE", "PER", "051", true, true, "Peru", "PERU",
                "Republic of Peru");

        CountryCodeRepresentation countryCodeRepresentation = ModelToRepresentation.toRepresentation(model1);

        assertThat(countryCodeRepresentation, is(notNullValue()));
        assertThat(countryCodeRepresentation.getAlpha2Code(), is(notNullValue()));
        assertThat(countryCodeRepresentation.getAlpha3Code(), is(notNullValue()));
        assertThat(countryCodeRepresentation.getNumericCode(), is(notNullValue()));
        assertThat(countryCodeRepresentation.getIndependent(), is(notNullValue()));
        assertThat(countryCodeRepresentation.getStatus(), is(notNullValue()));
        assertThat(countryCodeRepresentation.getShortNameEn(), is(notNullValue()));
        assertThat(countryCodeRepresentation.getShortNameUppercaseEn(), is(notNullValue()));
        assertThat(countryCodeRepresentation.getFullNameEn(), is(notNullValue()));

    }

}
