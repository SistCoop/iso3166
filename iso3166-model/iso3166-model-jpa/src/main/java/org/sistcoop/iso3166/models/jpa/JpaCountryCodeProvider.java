package org.sistcoop.iso3166.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.CountryCodeProvider;
import org.sistcoop.iso3166.models.jpa.entities.CountryCodeEntity;
import org.sistcoop.iso3166.models.search.SearchCriteriaModel;
import org.sistcoop.iso3166.models.search.SearchResultsModel;

@Named
@Stateless
@Local(CountryCodeProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCountryCodeProvider extends AbstractHibernateStorage implements CountryCodeProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public CountryCodeModel create(String alpha2Code, String alpha3Code, String numericCode,
            boolean independent, String status, String shortNameEn, String shortNameUppercaseEn,
            String fullNameEn) {

        CountryCodeEntity entity = new CountryCodeEntity();
        entity.setAlpha2Code(alpha2Code);
        entity.setAlpha3Code(alpha3Code);
        entity.setNumericCode(numericCode);
        entity.setIndependent(independent);
        entity.setStatus(status);
        entity.setShortNameEn(shortNameEn);
        entity.setShortNameUppercaseEn(shortNameUppercaseEn);
        entity.setFullNameEn(fullNameEn);
        em.persist(entity);
        return new CountryCodeAdapter(em, entity);
    }

    @Override
    public boolean remove(CountryCodeModel countryCodeModel) {
        CountryCodeEntity countryCodeEntity = em.find(CountryCodeEntity.class, countryCodeModel.getId());
        if (countryCodeEntity == null) {
            return false;
        }
        em.remove(countryCodeEntity);
        return true;
    }

    @Override
    public CountryCodeModel findById(String id) {
        CountryCodeEntity countryCodeEntity = this.em.find(CountryCodeEntity.class, id);
        return countryCodeEntity != null ? new CountryCodeAdapter(em, countryCodeEntity) : null;
    }

    @Override
    public CountryCodeModel findByAlpha2Code(String alpha2Code) {
        TypedQuery<CountryCodeEntity> query = em.createNamedQuery("CountryCodeEntity.findByAlpha2Code",
                CountryCodeEntity.class);
        query.setParameter("alpha2Code", alpha2Code);
        List<CountryCodeEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return new CountryCodeAdapter(em, results.get(0));
    }

    @Override
    public CountryCodeModel findByAlpha3Code(String alpha3Code) {
        TypedQuery<CountryCodeEntity> query = em.createNamedQuery("CountryCodeEntity.findByAlpha3Code",
                CountryCodeEntity.class);
        query.setParameter("alpha3Code", alpha3Code);
        List<CountryCodeEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return new CountryCodeAdapter(em, results.get(0));
    }

    @Override
    public CountryCodeModel findByNumericCode(String numericCode) {
        TypedQuery<CountryCodeEntity> query = em.createNamedQuery("CountryCodeEntity.findByNumericCode",
                CountryCodeEntity.class);
        query.setParameter("numericCode", numericCode);
        List<CountryCodeEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return new CountryCodeAdapter(em, results.get(0));
    }

    @Override
    public List<CountryCodeModel> getAll() {
        TypedQuery<CountryCodeEntity> query = em.createNamedQuery("CountryCodeEntity.findAll",
                CountryCodeEntity.class);

        List<CountryCodeEntity> entities = query.getResultList();
        List<CountryCodeModel> models = new ArrayList<CountryCodeModel>();
        for (CountryCodeEntity countryCodeEntity : entities) {
            models.add(new CountryCodeAdapter(em, countryCodeEntity));
        }

        return models;
    }

    @Override
    public SearchResultsModel<CountryCodeModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<CountryCodeEntity> entityResult = find(criteria, CountryCodeEntity.class);

        SearchResultsModel<CountryCodeModel> modelResult = new SearchResultsModel<>();
        List<CountryCodeModel> list = new ArrayList<>();
        for (CountryCodeEntity entity : entityResult.getModels()) {
            list.add(new CountryCodeAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    public SearchResultsModel<CountryCodeModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<CountryCodeEntity> entityResult = findFullText(criteria, CountryCodeEntity.class,
                filterText, "alpha2Code", "alpha3Code", "numericCode", "shortNameEn", "shortNameUppercaseEn",
                "fullNameEn");

        SearchResultsModel<CountryCodeModel> modelResult = new SearchResultsModel<>();
        List<CountryCodeModel> list = new ArrayList<>();
        for (CountryCodeEntity entity : entityResult.getModels()) {
            list.add(new CountryCodeAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
