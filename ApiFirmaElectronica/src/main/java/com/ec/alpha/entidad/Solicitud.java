/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.alpha.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "solicitud")

public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;
    @Column(name = "sol_ruc")
    private String solRuc;
    @Column(name = "sol_codigo_dactilar")
    private String solCodigoDactilar;
    @Column(name = "sol_nombre")
    private String solNombre;
    @Column(name = "sol_apellido1")
    private String solApellido1;
    @Column(name = "sol_apellido2")
    private String solApellido2;
    @Column(name = "sol_nacionalidad")
    private String solNacionalidad;
    @Column(name = "sol_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date solFechaNacimiento;
    @Column(name = "sol_edad")
    private Integer solEdad;
    @Column(name = "sol_sexo")
    private String solSexo;
    @Column(name = "sol_tipo_documento")
    private String solTipoDocumento;
    @Column(name = "sol_celular")
    private String solCelular;
    @Column(name = "sol_mail")
    private String solMail;
    @Column(name = "sol_celular_op")
    private String solCelularOp;
    @Column(name = "sol_mail_op")
    private String solMailOp;
    @Column(name = "sol_provincia")
    private String solProvincia;
    @Column(name = "sol_ciudad")
    private String solCiudad;
    @Column(name = "sol_direccion_completa")
    private String solDireccionCompleta;
    @Column(name = "sol_formato")
    private String solFormato;
    @Column(name = "sol_vigencia")
    private String solVigencia;
    @Column(name = "sol_path_cedula")
    private String solPathCedula;
    @Column(name = "sol_path_selfi")
    private String solPathSelfi;
    @Column(name = "sol_path_ruc")
    private String solPathRuc;
    @Column(name = "sol_path_constitucion")
    private String solPathConstitucion;
    @Column(name = "sol_path_cedula_anverso")
    private String solPathCedulaAnverso;
    @Column(name = "sol_path_cedula_reverso")
    private String solPathCedulaReverso;
    @Column(name = "age_estado")
    private Boolean ageEstado;
    @Column(name = "sol_con_ruc")
    private Boolean solConRuc;
    @Column(name = "sol_certificado")
    private String solCertificado;
    @Column(name = "sol_json")
    private String solJson;
    @Column(name = "sol_id_request")
    private String solIdRequest;

    @Column(name = "sol_tipo")
    private String solTipo;

    @Column(name = "sol_ruc_empresa")
    private String solRucEmpresa;

    @Column(name = "sol_razon_social")
    private String solRazonSocial;

    @Column(name = "sol_area")
    private String solArea;

    @Column(name = "sol_cargo_representante")
    private String solCargoRepresentante;

    @Column(name = "sol_cargo_solicitante")
    private String solCargoSolicitante;

    @Column(name = "sol_me_tipodocumento")
    private String solMeTipodocumento;

    @Column(name = "sol_me_nombres")
    private String solMeNombres;

    @Column(name = "sol_me_apellido1")
    private String solMeApellido1;

    @Column(name = "sol_me_apellido2")
    private String solMeApellido2;

    @Column(name = "sol_me_ruc")
    private String solMeRuc;

    @Column(name = "sol_fecha_creacion")
    private Date solFechaCreacion;
    
    @Column(name = "sol_path_constitucion_compania")
    private String solPathConstitucionCompania;
    
    @Column(name = "sol_path_nombramiento_representante")
    private String solPathNombramientoRepresentante;
    
    @Column(name = "sol_path_aceptacion_nombramiento")
    private String solPathAceptacionNombramiento;
    
    @Column(name = "sol_path_ruc_empresa")
    private String solPathRucEmpresa;
    
    @Column(name = "sol_path_cedula_representante_empresa")
    private String solPathCedulaRepresentanteEmpresa;
    
    @Column(name = "sol_path_autorizacion_representante")
    private String solPathAutorizacionRepresentante;
    
    @Column(name = "sol_num_retencion")
    private String solNumRetencion;
    
    @Column(name = "certificate")
    private String certificate; 
    
    
    @Column(name = "certificate_json")
    private String certificateJson;
    
    
    @Column(name = "sol_cedula")
    private String solCedula;
//    
//    @Column(name = "sol_private_key")
//    private String solPrivateKey;
    
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne
    private Ciudad idCiudad;
    
    @JoinColumn(name = "id_estado_firma", referencedColumnName = "id_estado_firma")
    @ManyToOne
    private EstadoFirma idEstadoFirma;
    
    @JoinColumn(name = "id_estado_proceso", referencedColumnName = "id_estado_proceso")
    @ManyToOne
    private EstadoProceso idEstadoProceso;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    @JoinColumn(name = "id_detalle_tipo_firma", referencedColumnName = "id_detalle_tipo_firma")
    @ManyToOne
    private DetalleTipoFirma idDetalleTipoFirma;

    public Solicitud() {
    }

    public Solicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getSolRuc() {
        return solRuc;
    }

    public void setSolRuc(String solRuc) {
        this.solRuc = solRuc;
    }

    public String getSolCodigoDactilar() {
        return solCodigoDactilar;
    }

    public void setSolCodigoDactilar(String solCodigoDactilar) {
        this.solCodigoDactilar = solCodigoDactilar;
    }

    public String getSolNombre() {
        return solNombre;
    }

    public void setSolNombre(String solNombre) {
        this.solNombre = solNombre;
    }

    public String getSolApellido1() {
        return solApellido1;
    }

    public void setSolApellido1(String solApellido1) {
        this.solApellido1 = solApellido1;
    }

    public String getSolApellido2() {
        return solApellido2;
    }

    public void setSolApellido2(String solApellido2) {
        this.solApellido2 = solApellido2;
    }

    public String getSolNacionalidad() {
        return solNacionalidad;
    }

    public void setSolNacionalidad(String solNacionalidad) {
        this.solNacionalidad = solNacionalidad;
    }

    public String getSolSexo() {
        return solSexo;
    }

    public void setSolSexo(String solSexo) {
        this.solSexo = solSexo;
    }

    public String getSolCelular() {
        return solCelular;
    }

    public void setSolCelular(String solCelular) {
        this.solCelular = solCelular;
    }

    public String getSolMail() {
        return solMail;
    }

    public void setSolMail(String solMail) {
        this.solMail = solMail;
    }

    public String getSolCelularOp() {
        return solCelularOp;
    }

    public void setSolCelularOp(String solCelularOp) {
        this.solCelularOp = solCelularOp;
    }

    public String getSolMailOp() {
        return solMailOp;
    }

    public void setSolMailOp(String solMailOp) {
        this.solMailOp = solMailOp;
    }

    public String getSolProvincia() {
        return solProvincia;
    }

    public void setSolProvincia(String solProvincia) {
        this.solProvincia = solProvincia;
    }

    public String getSolCiudad() {
        return solCiudad;
    }

    public void setSolCiudad(String solCiudad) {
        this.solCiudad = solCiudad;
    }

    public String getSolDireccionCompleta() {
        return solDireccionCompleta;
    }

    public void setSolDireccionCompleta(String solDireccionCompleta) {
        this.solDireccionCompleta = solDireccionCompleta;
    }

    public String getSolFormato() {
        return solFormato;
    }

    public void setSolFormato(String solFormato) {
        this.solFormato = solFormato;
    }

    public String getSolVigencia() {
        return solVigencia;
    }

    public void setSolVigencia(String solVigencia) {
        this.solVigencia = solVigencia;
    }

    public String getSolPathCedula() {
        return solPathCedula;
    }

    public void setSolPathCedula(String solPathCedula) {
        this.solPathCedula = solPathCedula;
    }

    public String getSolPathSelfi() {
        return solPathSelfi;
    }

    public void setSolPathSelfi(String solPathSelfi) {
        this.solPathSelfi = solPathSelfi;
    }

    public String getSolPathRuc() {
        return solPathRuc;
    }

    public void setSolPathRuc(String solPathRuc) {
        this.solPathRuc = solPathRuc;
    }

    public String getSolPathConstitucion() {
        return solPathConstitucion;
    }

    public void setSolPathConstitucion(String solPathConstitucion) {
        this.solPathConstitucion = solPathConstitucion;
    }

    public String getSolPathCedulaAnverso() {
        return solPathCedulaAnverso;
    }

    public void setSolPathCedulaAnverso(String solPathCedulaAnverso) {
        this.solPathCedulaAnverso = solPathCedulaAnverso;
    }

    public String getSolPathCedulaReverso() {
        return solPathCedulaReverso;
    }

    public void setSolPathCedulaReverso(String solPathCedulaReverso) {
        this.solPathCedulaReverso = solPathCedulaReverso;
    }

    public Boolean getAgeEstado() {
        return ageEstado;
    }

    public void setAgeEstado(Boolean ageEstado) {
        this.ageEstado = ageEstado;
    }

    public String getSolCertificado() {
        return solCertificado;
    }

    public void setSolCertificado(String solCertificado) {
        this.solCertificado = solCertificado;
    }

    public String getSolJson() {
        return solJson;
    }

    public void setSolJson(String solJson) {
        this.solJson = solJson;
    }

    public String getSolIdRequest() {
        return solIdRequest;
    }

    public void setSolIdRequest(String solIdRequest) {
        this.solIdRequest = solIdRequest;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public EstadoFirma getIdEstadoFirma() {
        return idEstadoFirma;
    }

    public void setIdEstadoFirma(EstadoFirma idEstadoFirma) {
        this.idEstadoFirma = idEstadoFirma;
    }

    public EstadoProceso getIdEstadoProceso() {
        return idEstadoProceso;
    }

    public void setIdEstadoProceso(EstadoProceso idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getSolFechaNacimiento() {
        return solFechaNacimiento;
    }

    public void setSolFechaNacimiento(Date solFechaNacimiento) {
        this.solFechaNacimiento = solFechaNacimiento;
    }

    public Integer getSolEdad() {
        return solEdad;
    }

    public void setSolEdad(Integer solEdad) {
        this.solEdad = solEdad;
    }

    public String getSolTipoDocumento() {
        return solTipoDocumento;
    }

    public void setSolTipoDocumento(String solTipoDocumento) {
        this.solTipoDocumento = solTipoDocumento;
    }

    public Boolean getSolConRuc() {
        return solConRuc;
    }

    public void setSolConRuc(Boolean solConRuc) {
        this.solConRuc = solConRuc;
    }

    public DetalleTipoFirma getIdDetalleTipoFirma() {
        return idDetalleTipoFirma;
    }

    public void setIdDetalleTipoFirma(DetalleTipoFirma idDetalleTipoFirma) {
        this.idDetalleTipoFirma = idDetalleTipoFirma;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Solicitud[ idSolicitud=" + idSolicitud + " ]";
    }

    public String getSolTipo() {
        return solTipo;
    }

    public void setSolTipo(String solTipo) {
        this.solTipo = solTipo;
    }

    public String getSolRucEmpresa() {
        return solRucEmpresa;
    }

    public void setSolRucEmpresa(String solRucEmpresa) {
        this.solRucEmpresa = solRucEmpresa;
    }

    public String getSolRazonSocial() {
        return solRazonSocial;
    }

    public void setSolRazonSocial(String solRazonSocial) {
        this.solRazonSocial = solRazonSocial;
    }

    public String getSolArea() {
        return solArea;
    }

    public void setSolArea(String solArea) {
        this.solArea = solArea;
    }

    public String getSolCargoRepresentante() {
        return solCargoRepresentante;
    }

    public void setSolCargoRepresentante(String solCargoRepresentante) {
        this.solCargoRepresentante = solCargoRepresentante;
    }

    public String getSolCargoSolicitante() {
        return solCargoSolicitante;
    }

    public void setSolCargoSolicitante(String solCargoSolicitante) {
        this.solCargoSolicitante = solCargoSolicitante;
    }

    public String getSolMeTipodocumento() {
        return solMeTipodocumento;
    }

    public void setSolMeTipodocumento(String solMeTipodocumento) {
        this.solMeTipodocumento = solMeTipodocumento;
    }

    public String getSolMeNombres() {
        return solMeNombres;
    }

    public void setSolMeNombres(String solMeNombres) {
        this.solMeNombres = solMeNombres;
    }

    public String getSolMeApellido1() {
        return solMeApellido1;
    }

    public void setSolMeApellido1(String solMeApellido1) {
        this.solMeApellido1 = solMeApellido1;
    }

    public String getSolMeApellido2() {
        return solMeApellido2;
    }

    public void setSolMeApellido2(String solMeApellido2) {
        this.solMeApellido2 = solMeApellido2;
    }

    public String getSolMeRuc() {
        return solMeRuc;
    }

    public void setSolMeRuc(String solMeRuc) {
        this.solMeRuc = solMeRuc;
    }

    public Date getSolFechaCreacion() {
        return solFechaCreacion;
    }

    public void setSolFechaCreacion(Date solFechaCreacion) {
        this.solFechaCreacion = solFechaCreacion;
    }

    public String getSolPathConstitucionCompania() {
        return solPathConstitucionCompania;
    }

    public void setSolPathConstitucionCompania(String solPathConstitucionCompania) {
        this.solPathConstitucionCompania = solPathConstitucionCompania;
    }

    public String getSolPathNombramientoRepresentante() {
        return solPathNombramientoRepresentante;
    }

    public void setSolPathNombramientoRepresentante(String solPathNombramientoRepresentante) {
        this.solPathNombramientoRepresentante = solPathNombramientoRepresentante;
    }

    public String getSolPathAceptacionNombramiento() {
        return solPathAceptacionNombramiento;
    }

    public void setSolPathAceptacionNombramiento(String solPathAceptacionNombramiento) {
        this.solPathAceptacionNombramiento = solPathAceptacionNombramiento;
    }

    public String getSolPathRucEmpresa() {
        return solPathRucEmpresa;
    }

    public void setSolPathRucEmpresa(String solPathRucEmpresa) {
        this.solPathRucEmpresa = solPathRucEmpresa;
    }

    public String getSolPathCedulaRepresentanteEmpresa() {
        return solPathCedulaRepresentanteEmpresa;
    }

    public void setSolPathCedulaRepresentanteEmpresa(String solPathCedulaRepresentanteEmpresa) {
        this.solPathCedulaRepresentanteEmpresa = solPathCedulaRepresentanteEmpresa;
    }

    public String getSolPathAutorizacionRepresentante() {
        return solPathAutorizacionRepresentante;
    }

    public void setSolPathAutorizacionRepresentante(String solPathAutorizacionRepresentante) {
        this.solPathAutorizacionRepresentante = solPathAutorizacionRepresentante;
    }

    public String getSolNumRetencion() {
        return solNumRetencion;
    }

    public void setSolNumRetencion(String solNumRetencion) {
        this.solNumRetencion = solNumRetencion;
    }

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getCertificateJson() {
		return certificateJson;
	}

	public void setCertificateJson(String certificateJson) {
		this.certificateJson = certificateJson;
	}

	public String getSolCedula() {
		return solCedula;
	}

	public void setSolCedula(String solCedula) {
		this.solCedula = solCedula;
	}

//	public String getSolPrivateKey() {
//		return solPrivateKey;
//	}
//
//	public void setSolPrivateKey(String solPrivateKey) {
//		this.solPrivateKey = solPrivateKey;
//	}

    
}
