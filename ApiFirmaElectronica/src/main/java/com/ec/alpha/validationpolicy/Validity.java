package com.ec.alpha.validationpolicy; 
public class Validity{
    public int secondsmin;
    public int secondsmax;
    public int not_before_negative_skew;
    public int not_before_positive_skew;
    public int issuer_expiry;
	public int getSecondsmin() {
		return secondsmin;
	}
	public void setSecondsmin(int secondsmin) {
		this.secondsmin = secondsmin;
	}
	public int getSecondsmax() {
		return secondsmax;
	}
	public void setSecondsmax(int secondsmax) {
		this.secondsmax = secondsmax;
	}
	public int getNot_before_negative_skew() {
		return not_before_negative_skew;
	}
	public void setNot_before_negative_skew(int not_before_negative_skew) {
		this.not_before_negative_skew = not_before_negative_skew;
	}
	public int getNot_before_positive_skew() {
		return not_before_positive_skew;
	}
	public void setNot_before_positive_skew(int not_before_positive_skew) {
		this.not_before_positive_skew = not_before_positive_skew;
	}
	public int getIssuer_expiry() {
		return issuer_expiry;
	}
	public void setIssuer_expiry(int issuer_expiry) {
		this.issuer_expiry = issuer_expiry;
	}
    
    
}
