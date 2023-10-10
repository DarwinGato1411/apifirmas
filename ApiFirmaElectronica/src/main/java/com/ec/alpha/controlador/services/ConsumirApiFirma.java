package com.ec.alpha.controlador.services;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.apache.http.impl.client.HttpClientBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ec.alpha.requestfirma.CredencialesToken;
import com.ec.alpha.requestfirma.RequestJuridica;
import com.ec.alpha.requestfirma.RequestMiembroEmpresa;
import com.ec.alpha.requestfirma.RequestPersonaNatural;
import com.ec.alpha.requestfirma.RequestPersonaNatural2;
import com.ec.alpha.requestfirma.RequestRevocar;
import com.ec.alpha.responsefirma.CertificateObtenido;
import com.ec.alpha.responsefirma.TokenFirma;
import com.ec.alpha.validationpolicy.Validationpolicy;

@Service
public class ConsumirApiFirma {

	@Autowired
	RestTemplate restTemplate;

	@Value("${url.api.firma}")
	private String urlconsulta;

	public TokenFirma obtenerToken(CredencialesToken param) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
//		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		try {
			String urlPeticion = "";
			urlPeticion = urlconsulta + "v2/login";
			HttpEntity<CredencialesToken> requestBody = new HttpEntity<CredencialesToken>(param, headers);

			System.out.println("TOKEN REPORTING SERVICES " + urlPeticion);
			// Send request with POST method.
			TokenFirma token = restTemplate.postForObject(urlPeticion, requestBody, TokenFirma.class);

			return token;
		} catch (Exception e) {
			System.out.println("ERROR AL CONSULTAR " + e.getMessage());
			return new TokenFirma("");
		}
	}

	/* CERTIFICATE EMPRESA */
	public CertificateObtenido certificate(RequestMiembroEmpresa param, String token) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
//		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		HttpHeaders headers = new HttpHeaders();
//		ResponseEnvioCorreo respuesta = new ResponseEnvioCorreo();

		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Bearer " + token.replace("\"", ""));
		try {
			String urlPeticion = "";
			urlPeticion = urlconsulta + "v2/certificates";
			HttpEntity<RequestMiembroEmpresa> requestBody = new HttpEntity<RequestMiembroEmpresa>(param, headers);

			System.out.println("URL  certificates" + urlPeticion);
			// Send request with POST method.
//			ResponseEntity <String> response = restTemplate.exchange(urlconsulta, HttpMethod.POST, requestBody, String.class);
			HttpEntity<String> response = restTemplate.exchange(urlPeticion, HttpMethod.POST, requestBody,
					String.class);
			String resultString = response.getBody();
			HttpHeaders headersRespo = response.getHeaders();
			String valor = headersRespo.getFirst("Location");

			urlPeticion = urlconsulta + valor;

			ResponseEntity<CertificateObtenido> responseCertificate = restTemplate.exchange(urlPeticion, HttpMethod.GET,
					requestBody, CertificateObtenido.class, 1);

			CertificateObtenido certificate = responseCertificate.getBody();
			certificate.setRevocar(valor);
			return certificate;
		} catch (Exception e) {
			System.out.println("ERROR AL CONSULTAR " + e.getMessage());
			return null;
		}
	}

	/* CERTIFICATE JURIDICA */
	public CertificateObtenido certificateJuridica(RequestJuridica param, String token) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
//		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		HttpHeaders headers = new HttpHeaders();
//		ResponseEnvioCorreo respuesta = new ResponseEnvioCorreo();

		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Bearer " + token.replace("\"", ""));
		try {
			String urlPeticion = "";
			urlPeticion = urlconsulta + "v2/certificates";
			HttpEntity<RequestJuridica> requestBody = new HttpEntity<RequestJuridica>(param, headers);

			System.out.println("URL  certificates" + urlPeticion);
			// Send request with POST method.
//			ResponseEntity <String> response = restTemplate.exchange(urlconsulta, HttpMethod.POST, requestBody, String.class);
			HttpEntity<String> response = restTemplate.exchange(urlPeticion, HttpMethod.POST, requestBody,
					String.class);
			String resultString = response.getBody();
			HttpHeaders headersRespo = response.getHeaders();
			String valor = headersRespo.getFirst("Location");

			urlPeticion = urlconsulta + valor;

			ResponseEntity<CertificateObtenido> responseCertificate = restTemplate.exchange(urlPeticion, HttpMethod.GET,
					requestBody, CertificateObtenido.class, 1);

			CertificateObtenido certificate = responseCertificate.getBody();
			certificate.setRevocar(valor);
			return certificate;
		} catch (Exception e) {
			System.out.println("ERROR AL CONSULTAR " + e.getMessage());
			return null;
		}
	}

	/* CERTIFICATE PERSONA NATURAL */
	public CertificateObtenido certificatePersonaNatural(RequestPersonaNatural param, String token) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
//		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		HttpHeaders headers = new HttpHeaders();
//		ResponseEnvioCorreo respuesta = new ResponseEnvioCorreo();

		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Bearer " + token.replace("\"", ""));

		if (param.getCustom_extensions() != null && param.getCustom_extensions().get_13614156105311() == null) {
			param.getCustom_extensions().set_13614156105311(null);
		}
		try {
			String urlPeticion = "";
			urlPeticion = urlconsulta + "v2/certificates";
			HttpEntity<RequestPersonaNatural> requestBody = new HttpEntity<RequestPersonaNatural>(param, headers);

			System.out.println("URL  certificates" + urlPeticion);
			// Send request with POST method.
//			ResponseEntity <String> response = restTemplate.exchange(urlconsulta, HttpMethod.POST, requestBody, String.class);
			HttpEntity<String> response = restTemplate.exchange(urlPeticion, HttpMethod.POST, requestBody,
					String.class);
			String resultString = response.getBody();
			HttpHeaders headersRespo = response.getHeaders();
			String valor = headersRespo.getFirst("Location");

			urlPeticion = urlconsulta + valor;

			ResponseEntity<CertificateObtenido> responseCertificate = restTemplate.exchange(urlPeticion, HttpMethod.GET,
					requestBody, CertificateObtenido.class, 1);

			CertificateObtenido certificate = responseCertificate.getBody();
			certificate.setRevocar(valor);
			return certificate;
		} catch (Exception e) {
			System.out.println("ERROR AL CONSULTAR " + e.getMessage());
			return null;
		}
	}

	public CertificateObtenido certificatePersonaNatural2(RequestPersonaNatural2 param, String token) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
//		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		HttpHeaders headers = new HttpHeaders();
//		ResponseEnvioCorreo respuesta = new ResponseEnvioCorreo();

		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Bearer " + token.replace("\"", ""));

		try {
			String urlPeticion = "";
			urlPeticion = urlconsulta + "v2/certificates";
			HttpEntity<RequestPersonaNatural2> requestBody = new HttpEntity<RequestPersonaNatural2>(param, headers);

			System.out.println("URL  certificates" + urlPeticion);
			// Send request with POST method.
//			ResponseEntity <String> response = restTemplate.exchange(urlconsulta, HttpMethod.POST, requestBody, String.class);
			HttpEntity<String> response = restTemplate.exchange(urlPeticion, HttpMethod.POST, requestBody,
					String.class);
			String resultString = response.getBody();
			HttpHeaders headersRespo = response.getHeaders();
			String valor = headersRespo.getFirst("Location");

			urlPeticion = urlconsulta + valor;

			ResponseEntity<CertificateObtenido> responseCertificate = restTemplate.exchange(urlPeticion, HttpMethod.GET,
					requestBody, CertificateObtenido.class, 1);

			CertificateObtenido certificate = responseCertificate.getBody();
			certificate.setRevocar(valor);
			return certificate;
		} catch (Exception e) {
			System.out.println("ERROR AL CONSULTAR " + e.getMessage());
			return null;
		}
	}

	/* validationpolicy */
	public Validationpolicy validationpolicy(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Bearer " + token.replace("\"", ""));
		try {
			String urlPeticion = "";
			urlPeticion = urlconsulta + "validationpolicy";
			HttpEntity request = new HttpEntity(headers);

			System.out.println("URL validationpolicy " + urlPeticion);
			// Send request with POST method.
//			ResponseEntity <String> response = restTemplate.exchange(urlconsulta, HttpMethod.POST, requestBody, String.class);
			ResponseEntity<Validationpolicy> response = restTemplate.exchange(urlPeticion, HttpMethod.GET, request,
					Validationpolicy.class, 1);

			return response.getBody();
		} catch (Exception e) {
			System.out.println("ERROR AL CONSULTAR " + e.getMessage());
			return null;
		}
	}

	/* validationpolicy */
	public String claimsDomains(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Bearer " + token.replace("\"", ""));
		try {
			String urlPeticion = "";
			urlPeticion = urlconsulta + "claims/domains";
			HttpEntity request = new HttpEntity(headers);

			System.out.println("URL validationpolicy " + urlPeticion);
			// Send request with POST method.
//			ResponseEntity <String> response = restTemplate.exchange(urlconsulta, HttpMethod.POST, requestBody, String.class);
			ResponseEntity<String> response = restTemplate.exchange(urlPeticion, HttpMethod.GET, request, String.class,
					1);

			return response.getBody();
		} catch (Exception e) {
			System.out.println("ERROR AL CONSULTAR " + e.getMessage());
			return null;
		}
	}

	/* REVOCAR CERTIFICADO */

	/* CERTIFICATE PERSONA NATURAL */
	public String revocarCertificado(String location, String token) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().build());
//		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		HttpHeaders headers = new HttpHeaders();
//		ResponseEnvioCorreo respuesta = new ResponseEnvioCorreo();

		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Bearer " + token.replace("\"", ""));
		System.out.println("\nToken " + token.replace("\"", ""));
		try {
			String urlPeticion = "";

			RequestRevocar param = new RequestRevocar();
			long before = Instant.now().getEpochSecond();
			param.setRevocation_reason("keyCompromise");
			param.setRevocation_time(before);

			HttpEntity<RequestRevocar> requestBody = new HttpEntity<RequestRevocar>(param, headers);

			urlPeticion = urlconsulta + location.substring(1);
			System.out.println("\nURL  certificates " + urlPeticion);
			System.out.println("\nRevocation_reason " + param.getRevocation_reason());
			System.out.println("\nRevocation_time " + param.getRevocation_time());

			ResponseEntity<byte[]> response = restTemplate.exchange(urlPeticion, HttpMethod.PATCH, requestBody,
					byte[].class);

			int estadoCodigo = response.getStatusCodeValue();

			if (estadoCodigo == 204) {
				return "Revocacion exitosa (204)";
			}

			byte[] responseBody = response.getBody();
			String respuestaCompleta = new String(responseBody, StandardCharsets.UTF_8);

			return respuestaCompleta + " " + estadoCodigo;
		} catch (HttpClientErrorException e) {
//			System.out.println("ERROR AL CONSULTAR " + e.getMessage());
//			return "ERROR AL CONSULTAR " + e.getMessage();

			int statusCode = e.getRawStatusCode();
			if (statusCode == 400) {

				return "ERROR Firma no revocada";
			} else if (statusCode == 204) {
				return "Revocacion exitosa (204)";
			} else if (statusCode == 412) {
				return "Firma ya revocada (412)";
			} else {
				return "Excepcion desconocida";
			}
		} catch (Exception e) {
			return "ERROR al consultar" + e.getMessage();
		}
	}

}
