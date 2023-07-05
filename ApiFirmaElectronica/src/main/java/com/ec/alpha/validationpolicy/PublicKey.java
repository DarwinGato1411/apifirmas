package com.ec.alpha.validationpolicy;

import java.util.ArrayList;

public class PublicKey{
    public String key_type;
    public ArrayList<Integer> allowed_lengths;
    public String key_format;
	public String getKey_type() {
		return key_type;
	}
	public void setKey_type(String key_type) {
		this.key_type = key_type;
	}
	public ArrayList<Integer> getAllowed_lengths() {
		return allowed_lengths;
	}
	public void setAllowed_lengths(ArrayList<Integer> allowed_lengths) {
		this.allowed_lengths = allowed_lengths;
	}
	public String getKey_format() {
		return key_format;
	}
	public void setKey_format(String key_format) {
		this.key_format = key_format;
	}
    
    
}
