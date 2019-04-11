package fetcher.model.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author João Victor
 */
@Entity
@Table(name = "rede_social")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RedeSocial.findAll", query = "SELECT r FROM RedeSocial r")
    , @NamedQuery(name = "RedeSocial.findById", query = "SELECT r FROM RedeSocial r WHERE r.id = :id")
    , @NamedQuery(name = "RedeSocial.findByNome", query = "SELECT r FROM RedeSocial r WHERE r.nome = :nome")
    , @NamedQuery(name = "RedeSocial.findByUrl", query = "SELECT r FROM RedeSocial r WHERE r.url = :url")})
public class RedeSocial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "redeSocial")
    private List<RedesocialDeputado> redesocialDeputadoList;

    public RedeSocial() {
    }

    public RedeSocial(Integer id) {
        this.id = id;
    }

    public RedeSocial(Integer id, String nome, String url) {
        this.id = id;
        this.nome = nome;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public List<RedesocialDeputado> getRedesocialDeputadoList() {
        return redesocialDeputadoList;
    }

    public void setRedesocialDeputadoList(List<RedesocialDeputado> redesocialDeputadoList) {
        this.redesocialDeputadoList = redesocialDeputadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedeSocial)) {
            return false;
        }
        RedeSocial other = (RedeSocial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fetcher.model.domain.RedeSocial[ id=" + id + " ]";
    }
    
}
