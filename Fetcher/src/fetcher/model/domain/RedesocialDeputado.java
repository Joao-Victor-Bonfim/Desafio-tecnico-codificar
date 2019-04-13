package fetcher.model.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author João Victor
 */
@Entity
@Table(name = "redesocial_deputado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RedesocialDeputado.findAll", query = "SELECT r FROM RedesocialDeputado r")
    , @NamedQuery(name = "RedesocialDeputado.findByDeputado", query = "SELECT r FROM RedesocialDeputado r WHERE r.redesocialDeputadoPK.idDeputado = :idDeputado")
    , @NamedQuery(name = "RedesocialDeputado.findByRedeSocial", query = "SELECT r FROM RedesocialDeputado r WHERE r.redesocialDeputadoPK.idRedesocial = :idRedeSocial")
    , @NamedQuery(name = "RedesocialDeputado.findByUrl", query = "SELECT r FROM RedesocialDeputado r WHERE r.url = :url")})
public class RedesocialDeputado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RedesocialDeputadoPK redesocialDeputadoPK;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "id_deputado", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Deputado deputado;
    @JoinColumn(name = "id_redesocial", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RedeSocial redeSocial;

    public RedesocialDeputado() {
    }

    public RedesocialDeputado(RedesocialDeputadoPK redesocialDeputadoPK) {
        this.redesocialDeputadoPK = redesocialDeputadoPK;
    }

    public RedesocialDeputado(RedesocialDeputadoPK redesocialDeputadoPK, String url) {
        this.redesocialDeputadoPK = redesocialDeputadoPK;
        this.url = url;
    }

    public RedesocialDeputado(int idDeputado, int idRedesocial) {
        this.redesocialDeputadoPK = new RedesocialDeputadoPK(idDeputado, idRedesocial);
    }

    public RedesocialDeputadoPK getRedesocialDeputadoPK() {
        return redesocialDeputadoPK;
    }

    public void setRedesocialDeputadoPK(RedesocialDeputadoPK redesocialDeputadoPK) {
        this.redesocialDeputadoPK = redesocialDeputadoPK;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Deputado getDeputado() {
        return deputado;
    }

    public void setDeputado(Deputado deputado) {
        this.deputado = deputado;
    }

    public RedeSocial getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(RedeSocial redeSocial) {
        this.redeSocial = redeSocial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (redesocialDeputadoPK != null ? redesocialDeputadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedesocialDeputado)) {
            return false;
        }
        RedesocialDeputado other = (RedesocialDeputado) object;
        return !((this.redesocialDeputadoPK == null && other.redesocialDeputadoPK != null) || (this.redesocialDeputadoPK != null && !this.redesocialDeputadoPK.equals(other.redesocialDeputadoPK)));
    }

    @Override
    public String toString() {
        return "fetcher.model.domain.RedesocialDeputado[ redesocialDeputadoPK=" + redesocialDeputadoPK + " ]";
    }
    
}
