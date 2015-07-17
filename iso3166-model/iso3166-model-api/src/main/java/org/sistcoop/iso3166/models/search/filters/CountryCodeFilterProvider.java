package org.sistcoop.iso3166.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.iso3166.provider.Provider;

@Local
public interface CountryCodeFilterProvider extends Provider {

    String getIdFilter();

    String getAlpha2CodeFilter();

    String getAlpha3CodeFilter();

    String getNumericCodeFilter();

    String getShortNameEn();

    String getShortNameUppercaseEn();

    String getFullNameEn();

}
