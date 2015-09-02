package org.sistcoop.iso3166.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.iso3166.models.search.SearchCriteriaModel;
import org.sistcoop.iso3166.models.search.SearchResultsModel;
import org.sistcoop.iso3166.provider.Provider;

@Local
public interface CountryCodeProvider extends Provider {

    CountryCodeModel findById(String id);

    CountryCodeModel findByAlpha2Code(String alpha2Code);

    CountryCodeModel findByAlpha3Code(String alpha3Code);

    CountryCodeModel findByNumericCode(String numericCode);

    CountryCodeModel create(String alpha2Code, String alpha3Code, String numericCode, boolean independent,
            String status, String shortNameEn, String shortNameUppercaseEn, String fullNameEn);

    boolean remove(CountryCodeModel countryCodeModel);

    List<CountryCodeModel> getAll();

    SearchResultsModel<CountryCodeModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<CountryCodeModel> search(SearchCriteriaModel criteria, String filterText);

}
