package com.ec.alpha.validationpolicy; 
public class KeyUsages{
    public String digital_signature;
    public String content_commitment;
    public String key_encipherment;
    public String data_encipherment;
    public String key_agreement;
    public String key_certificate_sign;
    public String crl_sign;
    public String encipher_only;
    public String decipher_only;
	public String getDigital_signature() {
		return digital_signature;
	}
	public void setDigital_signature(String digital_signature) {
		this.digital_signature = digital_signature;
	}
	public String getContent_commitment() {
		return content_commitment;
	}
	public void setContent_commitment(String content_commitment) {
		this.content_commitment = content_commitment;
	}
	public String getKey_encipherment() {
		return key_encipherment;
	}
	public void setKey_encipherment(String key_encipherment) {
		this.key_encipherment = key_encipherment;
	}
	public String getData_encipherment() {
		return data_encipherment;
	}
	public void setData_encipherment(String data_encipherment) {
		this.data_encipherment = data_encipherment;
	}
	public String getKey_agreement() {
		return key_agreement;
	}
	public void setKey_agreement(String key_agreement) {
		this.key_agreement = key_agreement;
	}
	public String getKey_certificate_sign() {
		return key_certificate_sign;
	}
	public void setKey_certificate_sign(String key_certificate_sign) {
		this.key_certificate_sign = key_certificate_sign;
	}
	public String getCrl_sign() {
		return crl_sign;
	}
	public void setCrl_sign(String crl_sign) {
		this.crl_sign = crl_sign;
	}
	public String getEncipher_only() {
		return encipher_only;
	}
	public void setEncipher_only(String encipher_only) {
		this.encipher_only = encipher_only;
	}
	public String getDecipher_only() {
		return decipher_only;
	}
	public void setDecipher_only(String decipher_only) {
		this.decipher_only = decipher_only;
	}
    
    
}
