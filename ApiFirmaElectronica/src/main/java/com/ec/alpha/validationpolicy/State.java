package com.ec.alpha.validationpolicy; 
public class State{
    public String presence;
    public String format;
    public boolean ignore_empty;
	public String getPresence() {
		return presence;
	}
	public void setPresence(String presence) {
		this.presence = presence;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public boolean isIgnore_empty() {
		return ignore_empty;
	}
	public void setIgnore_empty(boolean ignore_empty) {
		this.ignore_empty = ignore_empty;
	}
    
    
}
