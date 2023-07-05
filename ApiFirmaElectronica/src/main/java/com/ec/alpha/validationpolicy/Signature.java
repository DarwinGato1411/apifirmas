package com.ec.alpha.validationpolicy; 
public class Signature{
    public Algorithm algorithm;
    public HashAlgorithm hash_algorithm;
	public Algorithm getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	public HashAlgorithm getHash_algorithm() {
		return hash_algorithm;
	}
	public void setHash_algorithm(HashAlgorithm hash_algorithm) {
		this.hash_algorithm = hash_algorithm;
	}
    
    
}
