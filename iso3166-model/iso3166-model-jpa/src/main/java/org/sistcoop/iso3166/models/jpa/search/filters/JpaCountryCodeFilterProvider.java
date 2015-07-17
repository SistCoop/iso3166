package org.sistcoop.iso3166.models.jpa.search.filters;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.sistcoop.iso3166.models.search.filters.CountryCodeFilterProvider;

@Named
@Stateless
@Local(CountryCodeFilterProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCountryCodeFilterProvider implements CountryCodeFilterProvider {

    private final String id = "id";
    private final String alpha2Code = "alpha2Code";
    private final String alpha3Code = "alpha3Code";
    private final String numericCode = "numericCode";
    private final static String shortNameEn = "shortNameEn";
    private final static String shortNameUppercaseEn = "shortNameUppercaseEn";
    private final static String fullNameEn = "fullNameEn";

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public String getIdFilter() {
        return this.id;
    }

    @Override
    public String getAlpha2CodeFilter() {
        return alpha2Code;
    }

    @Override
    public String getAlpha3CodeFilter() {
        return alpha3Code;
    }

    @Override
    public String getNumericCodeFilter() {
        return numericCode;
    }

    @Override
    public String getShortNameEn() {
        return shortNameEn;
    }

    @Override
    public String getShortNameUppercaseEn() {
        return shortNameUppercaseEn;
    }

    @Override
    public String getFullNameEn() {
        return fullNameEn;
    }

}
