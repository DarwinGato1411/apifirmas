package com.ec.alpha.validationpolicy; 
public class Validationpolicy{
    public Validity validity;
    public SubjectDn subject_dn;
    public San san;
    public KeyUsages key_usages;
    public ExtendedKeyUsages extended_key_usages;
    public CustomExtensions custom_extensions;
    public Signature signature;
    public PublicKey public_key;
    public String public_key_signature;
	public Validity getValidity() {
		return validity;
	}
	public void setValidity(Validity validity) {
		this.validity = validity;
	}
	public SubjectDn getSubject_dn() {
		return subject_dn;
	}
	public void setSubject_dn(SubjectDn subject_dn) {
		this.subject_dn = subject_dn;
	}
	public San getSan() {
		return san;
	}
	public void setSan(San san) {
		this.san = san;
	}
	public KeyUsages getKey_usages() {
		return key_usages;
	}
	public void setKey_usages(KeyUsages key_usages) {
		this.key_usages = key_usages;
	}
	public ExtendedKeyUsages getExtended_key_usages() {
		return extended_key_usages;
	}
	public void setExtended_key_usages(ExtendedKeyUsages extended_key_usages) {
		this.extended_key_usages = extended_key_usages;
	}
	public CustomExtensions getCustom_extensions() {
		return custom_extensions;
	}
	public void setCustom_extensions(CustomExtensions custom_extensions) {
		this.custom_extensions = custom_extensions;
	}
	public Signature getSignature() {
		return signature;
	}
	public void setSignature(Signature signature) {
		this.signature = signature;
	}
	public PublicKey getPublic_key() {
		return public_key;
	}
	public void setPublic_key(PublicKey public_key) {
		this.public_key = public_key;
	}
	public String getPublic_key_signature() {
		return public_key_signature;
	}
	public void setPublic_key_signature(String public_key_signature) {
		this.public_key_signature = public_key_signature;
	}
    
    
}
