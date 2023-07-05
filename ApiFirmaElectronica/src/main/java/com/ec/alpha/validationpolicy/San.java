package com.ec.alpha.validationpolicy; 
public class San{
    public boolean critical;
    public DnsNames dns_names;
    public Emails emails;
    public IpAddresses ip_addresses;
    public Uris uris;
    public Object other_names;
	public boolean isCritical() {
		return critical;
	}
	public void setCritical(boolean critical) {
		this.critical = critical;
	}
	public DnsNames getDns_names() {
		return dns_names;
	}
	public void setDns_names(DnsNames dns_names) {
		this.dns_names = dns_names;
	}
	public Emails getEmails() {
		return emails;
	}
	public void setEmails(Emails emails) {
		this.emails = emails;
	}
	public IpAddresses getIp_addresses() {
		return ip_addresses;
	}
	public void setIp_addresses(IpAddresses ip_addresses) {
		this.ip_addresses = ip_addresses;
	}
	public Uris getUris() {
		return uris;
	}
	public void setUris(Uris uris) {
		this.uris = uris;
	}
	public Object getOther_names() {
		return other_names;
	}
	public void setOther_names(Object other_names) {
		this.other_names = other_names;
	}
    
    
}
