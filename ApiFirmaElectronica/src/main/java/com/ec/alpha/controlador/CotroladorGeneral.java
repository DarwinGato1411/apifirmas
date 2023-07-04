package com.ec.alpha.controlador;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

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
import com.ec.alpha.responsefirma.TokenFirma;
import com.ec.alpha.utilidad.GeneradorCertificados;
import com.ec.alpha.utilidad.GenerateCSR;
import com.ec.alpha.utilidad.RequestApi;
import com.ec.alpha.utilidad.RespuestaProceso;

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
	@ApiOperation(tags = "Firma electronica", value = "Obtiene el certificado para generar la firma electrónica como archivo .p12")
	public ResponseEntity<?> descuento(@RequestBody RequestApi valor) {

		try {

			RequestMiembroEmpresa empresa = new RequestMiembroEmpresa();
			Validity validity = new Validity();
			validity.setNot_after(1686247857); // fecha actual en milisegundos
			validity.setNot_before(1701872053);// cantidad de años

			SubjectDn dn = new SubjectDn();
			dn.setCommon_name("steven.chiriboga@alphaside.com"); // correo del cliente
			dn.setCountry("EC"); // Pais
			dn.setOrganization("Alpha Technologies"); // Nombre de la empresa o puede ir vacio para persona natural
			dn.setSerial_number("0001"); // Cedula

			CustomExtensions ce = new CustomExtensions();
			ce.set_136141561051("www.defact.com"); // url de politicas
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
			ce.set_13614156105318("PFX");// texto quemado
//se genera  a la base de datos por parte de alpha
			
			/*LLAMADA AL */
			empresa.setPublic_key(
					"\"-----BEGIN CERTIFICATE REQUEST-----\\nMIICvzCCAacCAQAwejELMAkGA1UEBhMCRUMxEjAQBgNVBAgMCVBpY2hpbmNoYTEO\\nMAwGA1UEBwwFUXVpdG8xHDAaBgNVBAoME0RlZmF1bHQgQ29tcGFueSBMdGQxKTAn\\nBgNVBAMMIHN0ZXZlbi5jaGlyaWJvZ2FAYcODbHBoYXNpZGUuY29tMIIBIjANBgkq\\nhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzE69YpWtsb3VIe/iveimNfayVFanR93V\\nhLAWYBEHusV1QlJAGJSq2VjWC039IeTVgpdb3I+Pj74hSBiJxHt1md3+/YMuSwGZ\\nx9IW51EsUyYzH9k73JAidbvhLBuBzxNAMUTkfQDjxCMX23pZAW4PjFWdkfMdzcNn\\nMSwJdJc8Zg1ak1lv93S5qotVSS/69JzWPCpUoCuzCgCp6HT0eruQw55DKr9U3rFw\\ntrpIAWAJOTd2NMVdKDVG6rmCBCiaTUscTYwPKm+DYKKIGCMF4Wh+BU0knc/jO42y\\nbhOXaxouPiyOTn0b4TUXllrybwqWHRdpzxWM1L9siRFH7WoDCBQC0wIDAQABoAAw\\nDQYJKoZIhvcNAQELBQADggEBAFSlD/29s7Pbh5L1h0UYDpms1OgZwEKujFP+HDDY\\nE1nacNH5GMBR7UqakGXDQwVegH2cG7G+LWaa1pFzjNeloDeMqgiP9QemqGwFyl2t\\ndvPRMbBfBNh2B2xAzOGEr+Q0+Ld9P5xvQDHpLvcGEYE28s1QeWq77uhqDAGW1tfN\\nH/3B/pnTWmnR7MITjas0M2j003GVeYa81OB2rEQ+3+cUmPHbOlo6Q9ecXHSgAiv4\\n7HQ7w8hr6sTjZSreKPet9T3ksRtIrgiio5yq+qWDGH6DkFEbY9gWG5R5HEEU7iEE\\n+23/2Jt60Pj0nNn+WTQ0jodw5wvcF0HLrFFSU58trbVobk0=\\n-----END CERTIFICATE REQUEST-----\"");

			empresa.setCustom_extensions(ce);
			empresa.setSubject_dn(dn);
			empresa.setValidity(validity);

			Solicitud solicitud = repository.buscarPorIdSolicitud(valor.getIdSolicitud(), valor.getIdUsuario());

			if (solicitud != null) {

				return new ResponseEntity<>(new RespuestaProceso(HttpStatus.OK.toString(),
						"Firma generada para la solicitud " + solicitud.getSolRuc()), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new RespuestaProceso("200", "No encontro la solicitud"), HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<RespuestaProceso>(
					new RespuestaProceso(HttpStatus.BAD_REQUEST.toString(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/generar-csr", method = RequestMethod.GET)
	@ApiOperation(tags = "Certificado", value = "Generar certificado")
	public ResponseEntity<?> productos() throws Exception {
		String keyPublic = "";
		try {

		    GenerateCSR gcsr = GenerateCSR.getInstance();      
	        
	        //System.out.println(gcsr.getCSR()); // CSR sin nada de info
	       // System.out.println(gcsr.getCSR("Pablo")); //CSR solo con el CN
	        System.out.println(gcsr.getCSR("Pablo","Tecnologia","Alpha Technologies","Kennedy","Pichincha","EC")); //CSR con toda la data
	        
	        System.out.println(gcsr.getPEMPrivateKey());
	    	return new ResponseEntity<String>(gcsr.getPEMPrivateKey(), HttpStatus.OK);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("ERROR AL GENERARA EL CERTIFICADO: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	

	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ApiOperation(tags = "login", value = "Generar token")
	public ResponseEntity<?> loginAPi() throws Exception {
//		System.setProperty("com.ibm.jsse2.overrideDefaultTLS","true");
		
//		ConsumirApiFirma apiFirma= new ConsumirApiFirma();
		CredencialesToken param= new CredencialesToken();
		param.setApi_key("689652829f001d7d");
		param.setApi_secret("d7f286ac80dd40ac4df7db7e6e7186d5467985b6");
		TokenFirma token=consumirApiFirma.obtenerToken(param,urlconsulta+"login");
		return new ResponseEntity<TokenFirma>(token, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/generar-llaves", method = RequestMethod.GET)
	@ApiOperation(tags = "Certificado", value = "Generar certificado")
	public ResponseEntity<?> generarLlaves() throws Exception {
		GeneradorCertificados.crearClave();
		return new ResponseEntity<String>("Correcto", HttpStatus.BAD_REQUEST);
	}
	
	
	

}
