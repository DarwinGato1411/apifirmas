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
@Table(name = "estado_firma")

public class EstadoFirma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_firma")
    private Integer idEstadoFirma;
    @Column(name = "est_descripcion")
    private String estDescripcion;
    @Column(name = "est_sigla")
    private String estSigla;
    @OneToMany(mappedBy = "idEstadoFirma")
    private Collection<Solicitud> solicitudCollection;

    public EstadoFirma() {
    }

    public EstadoFirma(Integer idEstadoFirma) {
        this.idEstadoFirma = idEstadoFirma;
    }

    public Integer getIdEstadoFirma() {
        return idEstadoFirma;
    }

    public void setIdEstadoFirma(Integer idEstadoFirma) {
        this.idEstadoFirma = idEstadoFirma;
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
        hash += (idEstadoFirma != null ? idEstadoFirma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoFirma)) {
            return false;
        }
        EstadoFirma other = (EstadoFirma) object;
        if ((this.idEstadoFirma == null && other.idEstadoFirma != null) || (this.idEstadoFirma != null && !this.idEstadoFirma.equals(other.idEstadoFirma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.EstadoFirma[ idEstadoFirma=" + idEstadoFirma + " ]";
    }
    
}
