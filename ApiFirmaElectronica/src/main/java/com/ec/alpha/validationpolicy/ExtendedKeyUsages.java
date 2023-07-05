package com.ec.alpha.validationpolicy; 
public class ExtendedKeyUsages{
    public boolean critical;
    public Ekus ekus;
	public boolean isCritical() {
		return critical;
	}
	public void setCritical(boolean critical) {
		this.critical = critical;
	}
	public Ekus getEkus() {
		return ekus;
	}
	public void setEkus(Ekus ekus) {
		this.ekus = ekus;
	}
    
    
}
