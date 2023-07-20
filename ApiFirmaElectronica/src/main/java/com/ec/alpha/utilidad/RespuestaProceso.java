package com.ec.alpha.utilidad;

public class RespuestaProceso {
	private String codigo;
	private String mensaje;
	private String observacion;

	public RespuestaProceso() {
		super();
	}

	public RespuestaProceso(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	

	public RespuestaProceso(String codigo, String mensaje, String observacion) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.observacion = observacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	

}
