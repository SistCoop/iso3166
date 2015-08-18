package org.sistcoop.iso3166.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.iso3166.admin.client.resource.CountryCodeResource;
import org.sistcoop.iso3166.admin.client.resource.CountryCodesResource;
import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.CountryCodeProvider;
import org.sistcoop.iso3166.models.search.PagingModel;
import org.sistcoop.iso3166.models.search.SearchCriteriaModel;
import org.sistcoop.iso3166.models.search.SearchResultsModel;
import org.sistcoop.iso3166.models.utils.ModelToRepresentation;
import org.sistcoop.iso3166.models.utils.RepresentationToModel;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;
import org.sistcoop.iso3166.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.iso3166.models.search.filters.CountryCodeFilterProvider;

@Stateless
public class CountryCodesResourceImpl implements CountryCodesResource {

    @Inject
    private CountryCodeProvider countryCodeProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private CountryCodeResource countryCodeResource;

    @Inject
    private CountryCodeFilterProvider filterProvider;

    @Override
    public CountryCodeResource countryCode(String countryCode) {
        return countryCodeResource;
    }

    @Override
    public Response create(CountryCodeRepresentation representation) {
        CountryCodeModel model = representationToModel.createCountryCode(representation, countryCodeProvider);
        return Response.created(uriInfo.getAbsolutePathBuilder().build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(ModelToRepresentation.toRepresentation(model)).build();
    }

    @Override
    public SearchResultsRepresentation<CountryCodeRepresentation> search(String filterText, Integer page,
            Integer pageSize) {

        SearchResultsModel<CountryCodeModel> results = null;
        if (filterText == null && page == null && pageSize == null) {
            results = countryCodeProvider.search();
        } else {
            if (filterText == null) {
                filterText = "";
            }
            if (page == null) {
                page = 1;
            }
            if (pageSize == null) {
                pageSize = 20;
            }

            PagingModel paging = new PagingModel();
            paging.setPage(page);
            paging.setPageSize(pageSize);

            SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
            searchCriteriaBean.setPaging(paging);
            searchCriteriaBean.addOrder(filterProvider.getShortNameEn(), true);

            results = countryCodeProvider.search(searchCriteriaBean, filterText);
        }

        SearchResultsRepresentation<CountryCodeRepresentation> rep = new SearchResultsRepresentation<>();
        List<CountryCodeRepresentation> representations = new ArrayList<>();
        for (CountryCodeModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }
}