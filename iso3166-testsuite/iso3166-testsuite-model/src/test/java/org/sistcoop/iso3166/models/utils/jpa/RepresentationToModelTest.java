package org.sistcoop.iso3166.models.utils.jpa;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.CountryCodeProvider;
import org.sistcoop.iso3166.models.jpa.AbstractTest;
import org.sistcoop.iso3166.models.utils.RepresentationToModel;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;

public class RepresentationToModelTest extends AbstractTest {

    @Inject
    private CountryCodeProvider countryCodeProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Test
    public void commit() {
        CountryCodeRepresentation rep = new CountryCodeRepresentation();
        rep.setAlpha2Code("PE");
        rep.setAlpha3Code("PER");
        rep.setNumericCode("051");
        rep.setIndependent(true);
        rep.setStatus(true);
        rep.setShortNameEn("Peru");
        rep.setShortNameUppercaseEn("PERU");
        rep.setFullNameEn("Republic of Peru");

        CountryCodeModel model = representationToModel.createCountryCode(rep, countryCodeProvider);

        assertThat(model, is(notNullValue()));
        assertThat(model.getAlpha2Code(), is(notNullValue()));
        assertThat(model.getAlpha3Code(), is(notNullValue()));
        assertThat(model.getNumericCode(), is(notNullValue()));
        assertThat(model.isIndependent(), is(notNullValue()));
        assertThat(model.isStatus(), is(notNullValue()));
        assertThat(model.getShortNameEn(), is(notNullValue()));
        assertThat(model.getShortNameUppercaseEn(), is(notNullValue()));
        assertThat(model.getFullNameEn(), is(notNullValue()));

    }

}
