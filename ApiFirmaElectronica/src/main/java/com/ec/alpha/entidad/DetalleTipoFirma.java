/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.alpha.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Darwin
 */
@Entity
public class DetalleTipoFirma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_tipo_firma")
    private Integer idDetalleTipoFirma;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "det_precio_compra")
    private BigDecimal detPrecioCompra;
    @Column(name = "det_precio_venta")
    private BigDecimal detPrecioVenta;
    @Column(name = "det_estado")
    private Boolean detEstado;
    @Column(name = "det_descripcion")
    private String detDescripcion;
    @JoinColumn(name = "id_tipo_firma", referencedColumnName = "id_tipo_firma")
    @ManyToOne
    private TipoFirma idTipoFirma;
    
    @OneToMany(mappedBy = "idDetalleTipoFirma")
    private Collection<Solicitud> solicitudCollection;

    public DetalleTipoFirma() {
    }

    public DetalleTipoFirma(Integer idDetalleTipoFirma) {
        this.idDetalleTipoFirma = idDetalleTipoFirma;
    }

    public Integer getIdDetalleTipoFirma() {
        return idDetalleTipoFirma;
    }

    public void setIdDetalleTipoFirma(Integer idDetalleTipoFirma) {
        this.idDetalleTipoFirma = idDetalleTipoFirma;
    }

    public BigDecimal getDetPrecioCompra() {
        return detPrecioCompra;
    }

    public void setDetPrecioCompra(BigDecimal detPrecioCompra) {
        this.detPrecioCompra = detPrecioCompra;
    }

    public BigDecimal getDetPrecioVenta() {
        return detPrecioVenta;
    }

    public void setDetPrecioVenta(BigDecimal detPrecioVenta) {
        this.detPrecioVenta = detPrecioVenta;
    }

    public Boolean getDetEstado() {
        return detEstado;
    }

    public void setDetEstado(Boolean detEstado) {
        this.detEstado = detEstado;
    }

    public TipoFirma getIdTipoFirma() {
        return idTipoFirma;
    }

    public void setIdTipoFirma(TipoFirma idTipoFirma) {
        this.idTipoFirma = idTipoFirma;
    }

    public String getDetDescripcion() {
        return detDescripcion;
    }

    public void setDetDescripcion(String detDescripcion) {
        this.detDescripcion = detDescripcion;
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
        hash += (idDetalleTipoFirma != null ? idDetalleTipoFirma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleTipoFirma)) {
            return false;
        }
        DetalleTipoFirma other = (DetalleTipoFirma) object;
        if ((this.idDetalleTipoFirma == null && other.idDetalleTipoFirma != null) || (this.idDetalleTipoFirma != null && !this.idDetalleTipoFirma.equals(other.idDetalleTipoFirma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DetalleTipoFirma[ idDetalleTipoFirma=" + idDetalleTipoFirma + " ]";
    }
    
}
