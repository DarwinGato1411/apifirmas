package com.ec.alpha.utilidad;

import com.ec.alpha.requestfirma.RequestMiembroEmpresa;

public class RequestApi {
	private String idSolicitud;
	private String idUsuario;
	private String clave;


	public RequestApi() {
		super();
	}

	public RequestApi(String idSolicitud, String idUsuario) {
		super();
		this.idSolicitud = idSolicitud;
		this.idUsuario = idUsuario;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
