package org.sistcoop.iso3166.admin.client.resource;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;
import org.sistcoop.iso3166.JaxRsActivator;
import org.sistcoop.iso3166.admin.client.Config;
import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.jpa.JpaCountryCodeProvider;
import org.sistcoop.iso3166.models.jpa.entities.CountryCodeEntity;
import org.sistcoop.iso3166.models.search.SearchCriteriaFilterModel;
import org.sistcoop.iso3166.models.utils.ModelToRepresentation;
import org.sistcoop.iso3166.provider.Provider;
import org.sistcoop.iso3166.representations.idm.CountryCodeRepresentation;
import org.sistcoop.iso3166.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.iso3166.services.messages.Messages;
import org.sistcoop.iso3166.services.resources.admin.CountryCodesResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public abstract class AbstractTest {

    protected Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @Deployment
    public static WebArchive createDeployment() {
        File[] logger = Maven.resolver().resolve("org.slf4j:slf4j-simple:1.7.10").withoutTransitivity()
                .asFile();

        File[] restAssured = Maven.resolver().resolve("com.jayway.restassured:rest-assured:2.4.1")
                .withTransitivity().asFile();

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "test.war")

                /** model-api **/
                .addPackage(Provider.class.getPackage())
                .addPackage(CountryCodeModel.class.getPackage())

                .addPackage(ModelToRepresentation.class.getPackage())

                .addPackage(SearchCriteriaFilterModel.class.getPackage())

                /** model-jpa **/
                .addPackage(JpaCountryCodeProvider.class.getPackage())
                .addPackage(CountryCodeEntity.class.getPackage())

                /** client */
                .addPackage(Config.class.getPackage())
                .addPackage(CountryCodesResource.class.getPackage())

                /** services */
                .addPackage(Messages.class.getPackage())
                .addPackage(CountryCodesResourceImpl.class.getPackage())

                /** core */
                .addPackage(CountryCodeRepresentation.class.getPackage())
                .addPackage(SearchResultsRepresentation.class.getPackage())

                /** core jaxrs */
                .addPackage(JaxRsActivator.class.getPackage())

                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource("META-INF/test-jboss-deployment-structure.xml",
                        "jboss-deployment-structure.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "web.xml").addAsWebInfResource("test-ds.xml");

        war.addAsLibraries(logger);
        war.addAsLibraries(restAssured);

        return war;
    }
}
