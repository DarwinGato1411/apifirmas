package com.ec.alpha.controlador;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ec.alpha.controlador.services.ConsumirApiFirma;
import com.ec.alpha.entidad.Solicitud;
import com.ec.alpha.repository.SolicitudRepository;
import com.ec.alpha.requestfirma.CredencialesToken;
import com.ec.alpha.requestfirma.CustomExtensions;
import com.ec.alpha.requestfirma.RequestMiembroEmpresa;
import com.ec.alpha.requestfirma.SubjectDn;
import com.ec.alpha.requestfirma.Validity;
import com.ec.alpha.responsefirma.CertificateObtenido;
import com.ec.alpha.responsefirma.TokenFirma;
import com.ec.alpha.utilidad.CsrValidity;
import com.ec.alpha.utilidad.GeneradorCertificados;
import com.ec.alpha.utilidad.GenerateCSR;
import com.ec.alpha.utilidad.RequestApi;
import com.ec.alpha.utilidad.RespuestaProceso;
import com.ec.alpha.validationpolicy.Validationpolicy;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Consumo de  descuentos", tags = "Descuentos", description = "Consumo de informacion desde aplicaciones de terceros")
public class CotroladorGeneral {

	private ConsumirApiFirma consumirApiFirma;

	@Autowired
	public CotroladorGeneral(ConsumirApiFirma sslService) {

		this.consumirApiFirma = sslService;
	}

	@Autowired
	SolicitudRepository repository;
	@Value("${url.api.firma}")
	private String urlconsulta;

	@RequestMapping(value = "/procesar-firma-empresa", method = RequestMethod.POST)
	@ApiOperation(tags = "Global Sing", value = "Obtiene el certificado para generar la firma electrónica como archivo .p12")
	public ResponseEntity<?> descuento(@RequestBody RequestApi valor) {

		try {

			Solicitud solicitud = repository.buscarPorIdSolicitud(valor.getIdSolicitud(), valor.getIdUsuario());

			
			Date fechaActual = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaActual); // Configuramos la fecha que se recibe
			calendar.add(Calendar.YEAR, 1); // numero de horas a añadir, o restar en caso de horas<0

			long before = Instant.now().getEpochSecond();
			long after = calendar.getTime().toInstant().getEpochSecond();

			RequestMiembroEmpresa empresa = new RequestMiembroEmpresa();
			Validity validity = new Validity();
			validity.setNot_after(after); // fecha actual en milisegundos
			validity.setNot_before(before);// cantidad de años

			SubjectDn dn = new SubjectDn();
			dn.setCommon_name(solicitud.getSolMail()); // correo del cliente
			dn.setCountry("EC"); // Pais
			dn.setOrganization(solicitud.getSolRazonSocial()); // Nombre de la empresa o puede ir vacio para persona natural
			dn.setSerial_number(solicitud.getSolRuc()); // Cedula

			CustomExtensions ce = new CustomExtensions();
			ce.set_136141561051("www.alphaside.com"); // url de politicas
			ce.set_1361415610521("darwin@hotmail.com"); // correo
			ce.set_1361415610523("Certificado de Miembro de Empresa"); // texto quemado
			ce.set_1361415610531("1720489879");// cedula del cliente
			ce.set_1361415610532("Steven Daniel");// Nombre del cliente
			ce.set_1361415610533("Chiriboga");// Apellido del cliente
			ce.set_1361415610534("Torres");// Segundo apellido
			ce.set_1361415610535("Reservado");// texto quemado
			ce.set_1361415610536("Reservado");// texto quemado
			ce.set_1361415610537("De los cedros y pedro botto");// Direccion del cliente
			ce.set_1361415610538("09980409085");// Celular del cliente
			ce.set_1361415610539("Quito");// Ciudad de cliente
			ce.set_13614156105310("Ecuador");// Pais de emision
			ce.set_13614156105311("Alpha Technologies");// Nombre de la empresa
			ce.set_13614156105312("Gerente");// Cargo
			ce.set_13614156105313("1720489879001");// RUC
			ce.set_13614156105318("PFX");// texto quemado

//se genera  a la base de datos por parte de alpha
			GenerateCSR gcsr = GenerateCSR.getInstance();

			// System.out.println(gcsr.getCSR()); // CSR sin nada de info
			// System.out.println(gcsr.getCSR("Pablo")); //CSR solo con el CN
//			System.out.println(gcsr.getCSR("Pablo", "Tecnologia", "Alpha Technologies", "Kennedy", "Pichincha", "EC").replaceAll("\\r", "")); // CSR
			// con

			/* LLAMADA AL */
			empresa.setPublic_key(gcsr.getCSR("Pablo", "Tecnologia", "Alpha Technologies", "Kennedy", "Pichincha", "EC")
					.replaceAll("\\r", ""));

			empresa.setCustom_extensions(ce);
			empresa.setSubject_dn(dn);
			empresa.setValidity(validity);

			/* LLAMADA AL SERVICIO WEB GENERA FIRMA */
			CredencialesToken param = new CredencialesToken();
			param.setApi_key("014b7c08cdf45861");
			param.setApi_secret("595797bc67224af967ac6a35036ab6ae398a7cac");
			// Obtiene el token
			TokenFirma token = consumirApiFirma.obtenerToken(param);

			CertificateObtenido certificate = consumirApiFirma.certificate(empresa, token.getAccess_token());
			solicitud.setCertificate(certificate.getCertificate());

			Gson gson = new Gson();
			String JSON = gson.toJson(certificate);
			System.out.println("JSON ENVIO " + JSON);
			solicitud.setCertificateJson(JSON);
			repository.save(solicitud);
			return new ResponseEntity<>(new RespuestaProceso(HttpStatus.OK.toString(),JSON), HttpStatus.OK);
//			

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<RespuestaProceso>(
					new RespuestaProceso(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ApiOperation(tags = "Global Sing", value = "Generar token")
	public ResponseEntity<?> loginAPi() throws Exception {
//		System.setProperty("com.ibm.jsse2.overrideDefaultTLS","true");

//		ConsumirApiFirma apiFirma= new ConsumirApiFirma();
		CredencialesToken param = new CredencialesToken();
		param.setApi_key("689652829f001d7d");
		param.setApi_secret("d7f286ac80dd40ac4df7db7e6e7186d5467985b6");
		TokenFirma token = consumirApiFirma.obtenerToken(param);
		return new ResponseEntity<TokenFirma>(token, HttpStatus.OK);
	}

	@RequestMapping(value = "/validationpolicy", method = RequestMethod.GET)
	@ApiOperation(tags = "Global", value = "Validationpolicy ")
	public ResponseEntity<?> validationpolicy() throws Exception {

		CredencialesToken param = new CredencialesToken();
		param.setApi_key("689652829f001d7d");
		param.setApi_secret("d7f286ac80dd40ac4df7db7e6e7186d5467985b6");
		TokenFirma token = consumirApiFirma.obtenerToken(param);

		Validationpolicy valorRet = consumirApiFirma.validationpolicy(token.getAccess_token());

		return new ResponseEntity<Validationpolicy>(valorRet, HttpStatus.OK);
	}

	@RequestMapping(value = "/claims/domains", method = RequestMethod.GET)
	@ApiOperation(tags = "Global Sing", value = "Validationpolicy ")
	public ResponseEntity<?> claimsDomains() throws Exception {

		CredencialesToken param = new CredencialesToken();
		param.setApi_key("689652829f001d7d");
		param.setApi_secret("d7f286ac80dd40ac4df7db7e6e7186d5467985b6");
		TokenFirma token = consumirApiFirma.obtenerToken(param);

		String valorRet = consumirApiFirma.claimsDomains(token.getAccess_token());

		return new ResponseEntity<String>(valorRet, HttpStatus.OK);
	}

	@RequestMapping(value = "/empresa", method = RequestMethod.GET)
	@ApiOperation(tags = "Modelo", value = "Generar certificado")
	public ResponseEntity<?> empresa() throws Exception {

		try {

			Date fechaActual = new Date();
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(fechaActual); // Configuramos la fecha que se recibe

			calendar.add(Calendar.YEAR, 1); // numero de horas a añadir, o restar en caso de horas<0

//			       calendar.getTime();
			long before = Instant.now().getEpochSecond();
			long after = calendar.getTime().toInstant().getEpochSecond();

			RequestMiembroEmpresa empresa = new RequestMiembroEmpresa();
			Validity validity = new Validity();
			validity.setNot_after(after); // fecha actual en milisegundos
			validity.setNot_before(before);// cantidad de años

			SubjectDn dn = new SubjectDn();
			dn.setCommon_name("steven.chiriboga@alphaside.com"); // correo del cliente
			dn.setCountry("EC"); // Pais
			dn.setOrganization("Alpha Technologies"); // Nombre de la empresa o puede ir vacio para persona natural
			dn.setSerial_number("0001"); // Cedula

			CustomExtensions ce = new CustomExtensions();
			ce.set_136141561051("www.alphaside.com/politicas.pfx"); // url de politicas
			ce.set_1361415610521("darwin@hotmail.com"); // correo
			ce.set_1361415610523("Certificado de Miembro de Empresa"); // texto quemado
			ce.set_1361415610531("1720489879");// cedula del cliente
			ce.set_1361415610532("Steven Daniel");// Nombre del cliente
			ce.set_1361415610533("Chiriboga");// Apellido del cliente
			ce.set_1361415610534("Torres");// Segundo apellido
			ce.set_1361415610535("Reservado");// texto quemado
			ce.set_1361415610536("Reservado");// texto quemado
			ce.set_1361415610537("De los cedros y pedro botto");// Direccion del cliente
			ce.set_1361415610538("09980409085");// Celular del cliente
			ce.set_1361415610539("Quito");// Ciudad de cliente
			ce.set_13614156105310("Ecuador");// Pais de emision
			ce.set_13614156105311("Alpha Technologies");// Nombre de la empresa
			ce.set_13614156105312("Gerente");// Cargo
			ce.set_13614156105313("1720489879001");// RUC
			ce.set_13614156105318("PFX");// texto quemado

//se genera  a la base de datos por parte de alpha
			GenerateCSR gcsr = GenerateCSR.getInstance();

			// System.out.println(gcsr.getCSR()); // CSR sin nada de info
			// System.out.println(gcsr.getCSR("Pablo")); //CSR solo con el CN
//			System.out.println(gcsr.getCSR("Pablo", "Tecnologia", "Alpha Technologies", "Kennedy", "Pichincha", "EC").replaceAll("\\r", "")); // CSR
			// con

			/* LLAMADA AL */
			empresa.setPublic_key(gcsr.getCSR("Pablo", "Tecnologia", "Alpha Technologies", "Kennedy", "Pichincha", "EC")
					.replaceAll("\\r", ""));

			empresa.setCustom_extensions(ce);
			empresa.setSubject_dn(dn);
			empresa.setValidity(validity);

			Gson gson = new Gson();
			String JSON = gson.toJson(empresa);
			System.out.println("JSON ENVIO " + JSON);
			/* LLAMADA AL SERVICIO WEB GENERA FIRMA */
			CredencialesToken param = new CredencialesToken();
			param.setApi_key("014b7c08cdf45861");
			param.setApi_secret("595797bc67224af967ac6a35036ab6ae398a7cac");
			// Obtiene el token
			TokenFirma token = consumirApiFirma.obtenerToken(param);

			return new ResponseEntity<>(empresa, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<RespuestaProceso>(
					new RespuestaProceso(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

//	@RequestMapping(value = "/generar-llaves", method = RequestMethod.GET)
//	@ApiOperation(tags = "Certificado", value = "Generar certificado")
//	public ResponseEntity<?> generarLlaves() throws Exception {
//		GeneradorCertificados.crearClave();
//		return new ResponseEntity<String>("Correcto", HttpStatus.BAD_REQUEST);
//	}
//	@RequestMapping(value = "/generar-csr", method = RequestMethod.GET)
	@ApiOperation(tags = "Certificado", value = "Generar certificado")
	public ResponseEntity<?> productos() throws Exception {
		String keyPublic = "";
		try {
			Date fechaActual = new Date();
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(fechaActual); // Configuramos la fecha que se recibe

			calendar.add(Calendar.YEAR, 1); // numero de horas a añadir, o restar en caso de horas<0

//			       calendar.getTime();
			long time = Instant.now().getEpochSecond();
			long after = calendar.getTime().toInstant().getEpochSecond();
			GenerateCSR gcsr = GenerateCSR.getInstance();

			CsrValidity crsValidity = new CsrValidity();
			crsValidity.setNot_after(after);

			// System.out.println(gcsr.getCSR()); // CSR sin nada de info
			// System.out.println(gcsr.getCSR("Pablo")); //CSR solo con el CN
			System.out.println("CSR");
			System.out.println(
					gcsr.getCSR("pablo@gmail.com", "Tecnologia", "Alpha Technologies", "Kennedy", "Pichincha", "EC")); // CSR
			// con
			// toda
			// la
			// data

			System.out.println("PRIVATE KEY");
			System.out.println(gcsr.getPEMPrivateKey());

			System.out.println("PUBLIC KEY");
			System.out.println(gcsr.getPublicKey());
			String CSR = gcsr.getCSR("", "", "", "", "", "EC").replaceAll("\\r", "");
			crsValidity.setCsr(CSR);
			crsValidity.setNot_before(time);
			return new ResponseEntity<CsrValidity>(crsValidity, HttpStatus.OK);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("ERROR AL GENERARA EL CERTIFICADO: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}

	}

}
