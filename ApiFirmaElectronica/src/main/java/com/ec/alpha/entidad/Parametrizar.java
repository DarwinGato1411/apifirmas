/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.alpha.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "parametrizar")
public class Parametrizar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parametrizar")
    private Integer idParametrizar;
    @Column(name = "par_descripcion")
    private String parDescripcion;
    @Column(name = "par_correo")
    private String parCorreo;
    @Column(name = "par_contrasena")
    private String parContrasena;
    @Column(name = "par_puerto")
    private Integer parPuerto;
    @Column(name = "para_protocolo")
    private String paraProtocolo;
    @Column(name = "par_imagenes")
    private String parImagenes;
    @Column(name = "par_base")
    private String parBase;
    @Column(name = "par_activo")
    private Boolean parActivo;
    @Column(name = "par_host")
    private String parHost;
//    @Column(name = "par_rootcrt")
//    private String parRootCrt;
//    @Column(name = "par_intermediatecrt")
//    private String parIntermediateCrt;

    public Parametrizar() {
    }

    public Parametrizar(Integer idParametrizar) {
        this.idParametrizar = idParametrizar;
    }

    public Integer getIdParametrizar() {
        return idParametrizar;
    }

    public void setIdParametrizar(Integer idParametrizar) {
        this.idParametrizar = idParametrizar;
    }

    public String getParDescripcion() {
        return parDescripcion;
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion = parDescripcion;
    }

    public String getParCorreo() {
        return parCorreo;
    }

    public void setParCorreo(String parCorreo) {
        this.parCorreo = parCorreo;
    }

    public String getParContrasena() {
        return parContrasena;
    }

    public void setParContrasena(String parContrasena) {
        this.parContrasena = parContrasena;
    }

    public Integer getParPuerto() {
        return parPuerto;
    }

    public void setParPuerto(Integer parPuerto) {
        this.parPuerto = parPuerto;
    }

    public String getParaProtocolo() {
        return paraProtocolo;
    }

    public void setParaProtocolo(String paraProtocolo) {
        this.paraProtocolo = paraProtocolo;
    }

    public String getParImagenes() {
        return parImagenes;
    }

    public void setParImagenes(String parImagenes) {
        this.parImagenes = parImagenes;
    }

    public String getParBase() {
        return parBase;
    }

    public void setParBase(String parBase) {
        this.parBase = parBase;
    }

    public Boolean getParActivo() {
        return parActivo;
    }

    public void setParActivo(Boolean parActivo) {
        this.parActivo = parActivo;
    }

    public String getParHost() {
        return parHost;
    }

    public void setParHost(String parHost) {
        this.parHost = parHost;
    }
    
    

//    public String getParRootCrt() {
//		return parRootCrt;
//	}
//
//	public void setParRootCrt(String parRootCrt) {
//		this.parRootCrt = parRootCrt;
//	}
//
//	public String getParIntermediateCrt() {
//		return parIntermediateCrt;
//	}
//
//	public void setParIntermediateCrt(String parIntermediateCrt) {
//		this.parIntermediateCrt = parIntermediateCrt;
//	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametrizar != null ? idParametrizar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrizar)) {
            return false;
        }
        Parametrizar other = (Parametrizar) object;
        if ((this.idParametrizar == null && other.idParametrizar != null) || (this.idParametrizar != null && !this.idParametrizar.equals(other.idParametrizar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Parametrizar[ idParametrizar=" + idParametrizar + " ]";
    }
    
}
