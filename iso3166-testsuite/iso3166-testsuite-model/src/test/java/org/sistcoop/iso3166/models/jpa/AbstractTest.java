package org.sistcoop.iso3166.models.jpa;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.jpa.entities.CountryCodeEntity;
import org.sistcoop.iso3166.models.search.SearchCriteriaFilterModel;
import org.sistcoop.iso3166.provider.Provider;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public abstract class AbstractTest {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                /** model-api **/
                .addPackage(Provider.class.getPackage()).addPackage(CountryCodeModel.class.getPackage())
                .addPackage(SearchCriteriaFilterModel.class.getPackage())

                /** model-jpa **/
                .addPackage(JpaCountryCodeProvider.class.getPackage())
                .addPackage(CountryCodeEntity.class.getPackage())

                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("test-ds.xml");

        return war;
    }
}
