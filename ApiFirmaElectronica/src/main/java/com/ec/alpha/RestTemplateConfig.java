package com.ec.alpha;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {	


	@Value("${trust.store}")
	private Resource trustStore;
	@Value("${trust.store.password}")
	private String trustStorePassword;
	String protocol = "TLSv1.2";

	@Bean
	RestTemplate restTemplate() throws Exception {
		SSLContext sslContext;
		RestTemplate restTemplate=null;
		try {
			   sslContext = SSLContextBuilder
		                .create()
		                .loadKeyMaterial(ResourceUtils.getFile("classpath:alpha.p12"), trustStorePassword.toCharArray(), trustStorePassword.toCharArray())
		                .loadTrustMaterial(ResourceUtils.getFile("classpath:alpha.jks"), trustStorePassword.toCharArray(), new TrustSelfSignedStrategy() {
		                    @Override
		                    public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
		                        return true;
		                    }
		                })
		                .build();
			
			CloseableHttpClient client = HttpClients.custom()
			        .setSSLContext(sslContext)
			        .build();
			HttpComponentsClientHttpRequestFactory requestFactory
			        = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(client);
			 restTemplate = new RestTemplate(requestFactory);
			
			
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CertificateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return restTemplate;
	}
}
