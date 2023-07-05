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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "tipo_firma")
public class TipoFirma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_firma")
    private Integer idTipoFirma;
    @Column(name = "tip_descripcion")
    private String tipDescripcion;
    @Column(name = "tip_estado")
    private String tipEstado;
    
    @OneToMany(mappedBy = "idTipoFirma")
    private Collection<DetalleTipoFirma> detalleTipoFirmaCollection;

    public TipoFirma() {
    }

    public TipoFirma(Integer idTipoFirma) {
        this.idTipoFirma = idTipoFirma;
    }

    public Integer getIdTipoFirma() {
        return idTipoFirma;
    }

    public void setIdTipoFirma(Integer idTipoFirma) {
        this.idTipoFirma = idTipoFirma;
    }

    public String getTipDescripcion() {
        return tipDescripcion;
    }

    public void setTipDescripcion(String tipDescripcion) {
        this.tipDescripcion = tipDescripcion;
    }

    public String getTipEstado() {
        return tipEstado;
    }

    public void setTipEstado(String tipEstado) {
        this.tipEstado = tipEstado;
    }


    public Collection<DetalleTipoFirma> getDetalleTipoFirmaCollection() {
        return detalleTipoFirmaCollection;
    }

    public void setDetalleTipoFirmaCollection(Collection<DetalleTipoFirma> detalleTipoFirmaCollection) {
        this.detalleTipoFirmaCollection = detalleTipoFirmaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoFirma != null ? idTipoFirma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFirma)) {
            return false;
        }
        TipoFirma other = (TipoFirma) object;
        if ((this.idTipoFirma == null && other.idTipoFirma != null) || (this.idTipoFirma != null && !this.idTipoFirma.equals(other.idTipoFirma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.TipoFirma[ idTipoFirma=" + idTipoFirma + " ]";
    }

}
