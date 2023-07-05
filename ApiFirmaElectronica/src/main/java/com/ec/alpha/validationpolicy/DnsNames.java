package com.ec.alpha.validationpolicy; 
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty; 
public class DnsNames{
    @JsonProperty("static") 
    public boolean mystatic;
    public ArrayList<Object> list;
    public int mincount;
    public int maxcount;
	public boolean isMystatic() {
		return mystatic;
	}
	public void setMystatic(boolean mystatic) {
		this.mystatic = mystatic;
	}
	public ArrayList<Object> getList() {
		return list;
	}
	public void setList(ArrayList<Object> list) {
		this.list = list;
	}
	public int getMincount() {
		return mincount;
	}
	public void setMincount(int mincount) {
		this.mincount = mincount;
	}
	public int getMaxcount() {
		return maxcount;
	}
	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}
    
    
}
