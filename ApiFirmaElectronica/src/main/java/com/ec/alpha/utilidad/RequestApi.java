package com.ec.alpha.utilidad;

import com.ec.alpha.requestfirma.RequestMiembroEmpresa;

public class RequestApi {
	private Integer idSolicitud;
	private Integer idUsuario;
	private String clave;


	public RequestApi() {
		super();
	}

	public RequestApi(Integer idSolicitud, Integer idUsuario) {
		super();
		this.idSolicitud = idSolicitud;
		this.idUsuario = idUsuario;
	}

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
