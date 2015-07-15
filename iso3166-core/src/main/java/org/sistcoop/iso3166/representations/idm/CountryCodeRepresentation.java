package org.sistcoop.iso3166.representations.idm;

import java.io.Serializable;

public class CountryCodeRepresentation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String alpha2Code;
    private String alpha3Code;
    private String numericCode;

    private Boolean independent;
    private String status;

    private String shortNameEn;
    private String shortNameUppercaseEn;
    private String fullNameEn;

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public Boolean getIndependent() {
        return independent;
    }

    public void setIndependent(Boolean independent) {
        this.independent = independent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShortNameEn() {
        return shortNameEn;
    }

    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn;
    }

    public String getShortNameUppercaseEn() {
        return shortNameUppercaseEn;
    }

    public void setShortNameUppercaseEn(String shortNameUppercaseEn) {
        this.shortNameUppercaseEn = shortNameUppercaseEn;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

}
