package com.ec.alpha.responsefirma;

public class TokenFirma {
	private String access_token;

	
	
	public TokenFirma() {
		super();
	}

	public TokenFirma(String access_token) {
		super();
		this.access_token = access_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

}
