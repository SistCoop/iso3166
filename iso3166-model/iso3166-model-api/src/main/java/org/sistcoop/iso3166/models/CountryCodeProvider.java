package org.sistcoop.iso3166.models;

import javax.ejb.Local;

import org.sistcoop.iso3166.models.search.SearchCriteriaModel;
import org.sistcoop.iso3166.models.search.SearchResultsModel;
import org.sistcoop.iso3166.provider.Provider;

@Local
public interface CountryCodeProvider extends Provider {

    CountryCodeModel create(String alpha2Code, String alpha3Code, String numericCode, boolean independent,
            boolean status, String shortNameEn, String shortNameUppercaseEn, String fullNameEn);

    boolean remove(CountryCodeModel countryCodeModel);

    CountryCodeModel findByAlpha2Code(String alpha2Code);

    CountryCodeModel findByAlpha3Code(String alpha3Code);

    CountryCodeModel findByNumericCode(String numericCode);

    SearchResultsModel<CountryCodeModel> search();

    SearchResultsModel<CountryCodeModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<CountryCodeModel> search(SearchCriteriaModel criteria, String filterText);

}
