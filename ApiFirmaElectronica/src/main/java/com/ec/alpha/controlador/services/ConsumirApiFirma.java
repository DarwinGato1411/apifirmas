package com.ec.alpha.controlador.services;

import org.apache.http.impl.client.HttpClientBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ec.alpha.requestfirma.CredencialesToken;
import com.ec.alpha.responsefirma.TokenFirma;


@Service
public class ConsumirApiFirma {
	
	@Autowired
    RestTemplate restTemplate;
	
	public TokenFirma obtenerToken(CredencialesToken param, String urlconsulta) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
//		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		HttpHeaders headers = new HttpHeaders();
		String respuesta = new String();

		headers.setContentType(MediaType.APPLICATION_JSON);
		try {

			HttpEntity<CredencialesToken> requestBody = new HttpEntity<CredencialesToken>(param, headers);

			System.out.println("TOKEN REPORTING SERVICES " + urlconsulta);
			// Send request with POST method.
			TokenFirma token = restTemplate.postForObject(urlconsulta, requestBody, TokenFirma.class);
			if (respuesta != null) {

				System.out.println("respuesta: " + respuesta);
			} else {
				System.out.println("RESPUESTA NULL!");
			}
			return token;
		} catch (Exception e) {
			System.out.println("ERROR AL CONSULTAR "+e.getMessage());
			return new TokenFirma("");
		}
	}
	
	
	
}
