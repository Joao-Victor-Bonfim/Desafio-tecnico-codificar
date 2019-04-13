package fetcher.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author João Victor
 */
@Entity
@Table(name = "deputado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deputado.findAll", query = "SELECT d FROM Deputado d")
    , @NamedQuery(name = "Deputado.findByApelido", query = "SELECT d FROM Deputado d WHERE d.apelido = :apelido")
    , @NamedQuery(name = "Deputado.findByNome", query = "SELECT d FROM Deputado d WHERE d.nome = :nome")
    , @NamedQuery(name = "Deputado.findById", query = "SELECT d FROM Deputado d WHERE d.id = :id")})
public class Deputado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "apelido")
    private String apelido;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "nascimento")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deputado")
    private List<RedesocialDeputado> redesocialDeputadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deputado")
    private List<VerbasIdenizatorias> verbasIdenizatoriasList;
    @JoinColumn(name = "partido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Partido partido;

    public Deputado() {
    }

    public Deputado(Integer id) {
        this.id = id;
    }

    public Deputado(Integer id, String apelido, String nome, String endereco, Date nascimento) {
        this.id = id;
        this.apelido = apelido;
        this.nome = nome;
        this.endereco = endereco;
        this.nascimento = nascimento;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<RedesocialDeputado> getRedesocialDeputadoList() {
        return redesocialDeputadoList;
    }

    public void setRedesocialDeputadoList(List<RedesocialDeputado> redesocialDeputadoList) {
        this.redesocialDeputadoList = redesocialDeputadoList;
    }

    @XmlTransient
    public List<VerbasIdenizatorias> getVerbasIdenizatoriasList() {
        return verbasIdenizatoriasList;
    }

    public void setVerbasIdenizatoriasList(List<VerbasIdenizatorias> verbasIdenizatoriasList) {
        this.verbasIdenizatoriasList = verbasIdenizatoriasList;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
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
        if (!(object instanceof Deputado)) {
            return false;
        }
        Deputado other = (Deputado) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "fetcher.model.domain.Deputado[ id=" + id + "; nome=" + nome + "; apelido=" + apelido + "; endereço=" + endereco + "; nascimento=" + nascimento + "; partido=" + partido + " ]";
    }
    
}
