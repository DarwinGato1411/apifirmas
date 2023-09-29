package com.ec.alpha.utilidad;

public class RequestRevocarApi {
	private String idSolicitud;
	private String idUsuario;


	public RequestRevocarApi() {
		super();
	}

	public RequestRevocarApi(String idSolicitud, String idUsuario) {
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


}
