package org.sistcoop.iso3166.models.mongo;

import javax.persistence.EntityManager;

import org.sistcoop.iso3166.models.CountryCodeModel;
import org.sistcoop.iso3166.models.mongo.entities.CountryCodeEntity;

public class CountryCodeAdapter implements CountryCodeModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private CountryCodeEntity countryCodeEntity;
    private transient EntityManager em;

    public CountryCodeAdapter(EntityManager em, CountryCodeEntity countryCodeEntity) {
        this.em = em;
        this.countryCodeEntity = countryCodeEntity;
    }

    public CountryCodeEntity getCountryCodeEntity() {
        return this.countryCodeEntity;
    }

    public static CountryCodeEntity toCountryCodeEntity(CountryCodeModel model, EntityManager em) {
        if (model instanceof CountryCodeAdapter) {
            return ((CountryCodeAdapter) model).getCountryCodeEntity();
        }
        return em.getReference(CountryCodeEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(countryCodeEntity);
    }

    @Override
    public String getId() {
        return countryCodeEntity.getId();
    }

    @Override
    public String getAlpha2Code() {
        return countryCodeEntity.getAlpha2Code();
    }

    @Override
    public String getAlpha3Code() {
        return countryCodeEntity.getAlpha3Code();
    }

    @Override
    public String getNumericCode() {
        return countryCodeEntity.getNumericCode();
    }

    @Override
    public boolean isIndependent() {
        return countryCodeEntity.isIndependent();
    }

    @Override
    public void setIndependent(boolean independent) {
        countryCodeEntity.setIndependent(independent);
    }

    @Override
    public String getStatus() {
        return countryCodeEntity.getStatus();
    }

    @Override
    public void setStatus(String status) {
        countryCodeEntity.setStatus(status);
    }

    @Override
    public String getShortNameEn() {
        return countryCodeEntity.getShortNameEn();
    }

    @Override
    public void setShortNameEn(String shortNameEn) {
        countryCodeEntity.setShortNameEn(shortNameEn);
    }

    @Override
    public String getShortNameUppercaseEn() {
        return countryCodeEntity.getShortNameUppercaseEn();
    }

    @Override
    public void setShortNameUppercaseEn(String shortNameUppercaseEn) {
        countryCodeEntity.setShortNameUppercaseEn(shortNameUppercaseEn);
    }

    @Override
    public String getFullNameEn() {
        return countryCodeEntity.getFullNameEn();
    }

    @Override
    public void setFullNameEn(String fullNameEn) {
        countryCodeEntity.setFullNameEn(fullNameEn);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CountryCodeModel other = (CountryCodeModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
