package org.sistcoop.persona.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.iso3166.provider.Provider;

@Local
public interface CountryCodeFilterProvider extends Provider {

    String getAlpha2CodeFilter();

    String getAlpha3CodeFilter();

    String getNumericCodeFilter();

    String getShortNameEn();

    String getShortNameUppercaseEn();

    String getFullNameEn();

}
