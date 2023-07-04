/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.alpha.entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "estado_proceso")

public class EstadoProceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_proceso")
    private Integer idEstadoProceso;
    @Column(name = "est_descripcion")
    private String estDescripcion;
    @Column(name = "est_sigla")
    private String estSigla;
    @OneToMany(mappedBy = "idEstadoProceso")
    private Collection<Solicitud> solicitudCollection;

    public EstadoProceso() {
    }

    public EstadoProceso(Integer idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public Integer getIdEstadoProceso() {
        return idEstadoProceso;
    }

    public void setIdEstadoProceso(Integer idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public String getEstDescripcion() {
        return estDescripcion;
    }

    public void setEstDescripcion(String estDescripcion) {
        this.estDescripcion = estDescripcion;
    }

    public String getEstSigla() {
        return estSigla;
    }

    public void setEstSigla(String estSigla) {
        this.estSigla = estSigla;
    }

    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoProceso != null ? idEstadoProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProceso)) {
            return false;
        }
        EstadoProceso other = (EstadoProceso) object;
        if ((this.idEstadoProceso == null && other.idEstadoProceso != null) || (this.idEstadoProceso != null && !this.idEstadoProceso.equals(other.idEstadoProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EstadoProceso[ idEstadoProceso=" + idEstadoProceso + " ]";
    }
    
}
