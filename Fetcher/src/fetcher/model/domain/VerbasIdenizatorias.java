package fetcher.model.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author João Victor
 */
@Entity
@Table(name = "verbas_idenizatorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VerbasIdenizatorias.findAll", query = "SELECT v FROM VerbasIdenizatorias v")
    , @NamedQuery(name = "VerbasIdenizatorias.findByDeputado", query = "SELECT v FROM VerbasIdenizatorias v WHERE v.verbasIdenizatoriasPK.idDeputado = :Deputado")
    , @NamedQuery(name = "VerbasIdenizatorias.findByDespesa", query = "SELECT v FROM VerbasIdenizatorias v WHERE v.verbasIdenizatoriasPK.idDespesa = :Despesa")
    , @NamedQuery(name = "VerbasIdenizatorias.findByValor", query = "SELECT v FROM VerbasIdenizatorias v WHERE v.valor = :valor")
    , @NamedQuery(name = "VerbasIdenizatorias.findByData", query = "SELECT v FROM VerbasIdenizatorias v WHERE v.data = :data")})
public class VerbasIdenizatorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VerbasIdenizatoriasPK verbasIdenizatoriasPK;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "id_deputado", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Deputado deputado;
    @JoinColumn(name = "tipo_despesa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Despesa tipoDespesa;

    public VerbasIdenizatorias() {
    }

    public VerbasIdenizatorias(VerbasIdenizatoriasPK verbasIdenizatoriasPK) {
        this.verbasIdenizatoriasPK = verbasIdenizatoriasPK;
    }

    public VerbasIdenizatorias(VerbasIdenizatoriasPK verbasIdenizatoriasPK, double valor, Date data) {
        this.verbasIdenizatoriasPK = verbasIdenizatoriasPK;
        this.valor = valor;
        this.data = data;
    }

    public VerbasIdenizatorias(int idDeputado, int idDespesa) {
        this.verbasIdenizatoriasPK = new VerbasIdenizatoriasPK(idDeputado, idDespesa);
    }

    public VerbasIdenizatoriasPK getVerbasIdenizatoriasPK() {
        return verbasIdenizatoriasPK;
    }

    public void setVerbasIdenizatoriasPK(VerbasIdenizatoriasPK verbasIdenizatoriasPK) {
        this.verbasIdenizatoriasPK = verbasIdenizatoriasPK;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Deputado getDeputado() {
        return deputado;
    }

    public void setDeputado(Deputado deputado) {
        this.deputado = deputado;
    }

    public Despesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(Despesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (verbasIdenizatoriasPK != null ? verbasIdenizatoriasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VerbasIdenizatorias)) {
            return false;
        }
        VerbasIdenizatorias other = (VerbasIdenizatorias) object;
        return !((this.verbasIdenizatoriasPK == null && other.verbasIdenizatoriasPK != null) || (this.verbasIdenizatoriasPK != null && !this.verbasIdenizatoriasPK.equals(other.verbasIdenizatoriasPK)));
    }

    @Override
    public String toString() {
        return "fetcher.model.domain.VerbasIdenizatorias[ verbasIdenizatoriasPK=" + verbasIdenizatoriasPK + " ]";
    }
    
}
