package fetcher.model.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author João Victor
 */
@Embeddable
public class RedesocialDeputadoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_deputado")
    private int idDeputado;
    @Basic(optional = false)
    @Column(name = "id_redesocial")
    private int idRedesocial;

    public RedesocialDeputadoPK() {
    }

    public RedesocialDeputadoPK(int idDeputado, int idRedesocial) {
        this.idDeputado = idDeputado;
        this.idRedesocial = idRedesocial;
    }

    public int getIdDeputado() {
        return idDeputado;
    }

    public void setIdDeputado(int idDeputado) {
        this.idDeputado = idDeputado;
    }

    public int getIdRedesocial() {
        return idRedesocial;
    }

    public void setIdRedesocial(int idRedesocial) {
        this.idRedesocial = idRedesocial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDeputado;
        hash += (int) idRedesocial;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedesocialDeputadoPK)) {
            return false;
        }
        RedesocialDeputadoPK other = (RedesocialDeputadoPK) object;
        return this.idDeputado == other.idDeputado && this.idRedesocial == other.idRedesocial;
    }

    @Override
    public String toString() {
        return "fetcher.model.domain.RedesocialDeputadoPK[ idDeputado=" + idDeputado + ", idRedesocial=" + idRedesocial + " ]";
    }
    
}
