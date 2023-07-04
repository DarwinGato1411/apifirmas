package com.ec.alpha.utilidad;

public class RequestApi {
	private Integer idSolicitud;
	private Integer idUsuario;

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

}
