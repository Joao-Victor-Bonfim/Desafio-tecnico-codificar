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
public class VerbasIdenizatoriasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_deputado")
    private int idDeputado;
    @Basic(optional = false)
    @Column(name = "id_despesa")
    private int idDespesa;

    public VerbasIdenizatoriasPK() {
    }

    public VerbasIdenizatoriasPK(int idDeputado, int idDespesa) {
        this.idDeputado = idDeputado;
        this.idDespesa = idDespesa;
    }

    public int getIdDeputado() {
        return idDeputado;
    }

    public void setIdDeputado(int idDeputado) {
        this.idDeputado = idDeputado;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDeputado;
        hash += (int) idDespesa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VerbasIdenizatoriasPK)) {
            return false;
        }
        VerbasIdenizatoriasPK other = (VerbasIdenizatoriasPK) object;
        if (this.idDeputado != other.idDeputado) {
            return false;
        }
        if (this.idDespesa != other.idDespesa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fetcher.model.domain.VerbasIdenizatoriasPK[ idDeputado=" + idDeputado + ", idDespesa=" + idDespesa + " ]";
    }
    
}
