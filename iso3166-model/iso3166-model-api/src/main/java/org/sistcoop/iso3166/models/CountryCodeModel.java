package org.sistcoop.iso3166.models;

public interface CountryCodeModel extends Model {

	String getId();
	
	String getAlpha2Code();

	String getAlpha3Code();

	String getNumericCode();

	boolean isIndependent();

	void setIndependent(boolean independent);

	String getStatus();

	void setStatus(String status);

	String getShortNameEn();

	void setShortNameEn(String shortNameEn);

	String getShortNameUppercaseEn();

	void setShortNameUppercaseEn(String shortNameUppercaseEn);

	String getFullNameEn();

	void setFullNameEn(String fullNameEn);

}