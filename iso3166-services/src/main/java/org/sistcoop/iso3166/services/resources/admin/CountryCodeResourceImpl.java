package org.sistcoop.iso3166.services.resources.admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.iso3166.admin.client.resource.CountryCodeResource;
import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.CountryCodeProvider;
import org.sistcoop.iso3166.models.utils.ModelToRepresentation;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;

@Stateless
public class CountryCodeResourceImpl implements CountryCodeResource {

    @PathParam("countryCode")
    private String countryCode;

    @Inject
    private CountryCodeProvider countryCodeProvider;

    private CountryCodeModel getCountryCodeModel() {
        if (countryCode.length() == 2) {
            return countryCodeProvider.findByAlpha2Code(countryCode);
        } else if (countryCode.length() == 3) {
            Pattern pattern = Pattern.compile("^[0-9]+$");
            Matcher matcher = pattern.matcher(countryCode);
            if (matcher.matches()) {
                return countryCodeProvider.findByNumericCode(countryCode);
            } else {
                return countryCodeProvider.findByAlpha3Code(countryCode);
            }
        } else {
            return null;
        }
    }

    @Override
    public CountryCodeRepresentation countryCode() {
        CountryCodeModel model = getCountryCodeModel();
        if (model != null) {
            return ModelToRepresentation.toRepresentation(model);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(CountryCodeRepresentation representation) {
        CountryCodeModel model = getCountryCodeModel();
        model.setIndependent(representation.getIndependent());
        model.setStatus(representation.getStatus());
        model.setShortNameEn(representation.getShortNameEn());
        model.setShortNameUppercaseEn(representation.getShortNameUppercaseEn());
        model.setFullNameEn(representation.getFullNameEn());

        model.commit();
    }

    @Override
    public void disable() {
        throw new NotFoundException();
    }

    @Override
    public void remove() {
        CountryCodeModel model = getCountryCodeModel();
        boolean result = countryCodeProvider.remove(model);
        if (!result) {
            throw new InternalServerErrorException();
        }
    }

}
