package org.sistcoop.iso3166.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotBlank;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;
import org.sistcoop.iso3166.representations.idm.search.SearchResultsRepresentation;

@Path("/countryCodes")
@Consumes(MediaType.APPLICATION_JSON)
public interface CountryCodesResource {

    @Path("/{countryCode}")
    public CountryCodeResource countryCode(@PathParam("countryCode") String countryCode);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CountryCodeRepresentation representation);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<CountryCodeRepresentation> search(
            @QueryParam("filterText") @NotBlank String filterText,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("pageSize") @DefaultValue("20") int pageSize);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<CountryCodeRepresentation> search();

}