package com.ec.alpha.requestfirma;

public class RequestPersonaNatural {
	public Validity validity;
	public SubjectDn subject_dn;
	public CustomExtensionsPersona custom_extensions;
	public String public_key;

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

	public CustomExtensionsPersona getCustom_extensions() {
		return custom_extensions;
	}

	public void setCustom_extensions(CustomExtensionsPersona custom_extensions) {
		this.custom_extensions = custom_extensions;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

}
