package com.ec.alpha.requestfirma;

public class RequestRevocar {
	private String revocation_reason;
	private long revocation_time;

	public String getRevocation_reason() {
		return revocation_reason;
	}

	public void setRevocation_reason(String revocation_reason) {
		this.revocation_reason = revocation_reason;
	}

	public long getRevocation_time() {
		return revocation_time;
	}

	public void setRevocation_time(long revocation_time) {
		this.revocation_time = revocation_time;
	}

}
