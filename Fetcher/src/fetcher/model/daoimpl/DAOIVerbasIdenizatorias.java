package fetcher.model.daoimpl;

import fetcher.model.dao.DAOVerbasIdenizatorias;
import fetcher.model.domain.VerbasIdenizatorias;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class DAOIVerbasIdenizatorias implements DAOVerbasIdenizatorias {
    
    private static final DAOIVerbasIdenizatorias DAOIVerbasIdenizatorias = new DAOIVerbasIdenizatorias();
    
    private DAOIVerbasIdenizatorias(){}
    
    public static DAOIVerbasIdenizatorias getInstance() {
        return DAOIVerbasIdenizatorias;
    }

    @Override
    public List<VerbasIdenizatorias> listarTodos() throws PersistenceException {
        return Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("VerbasIdenizatorias.findAll")
            .getResultList();
    }

    @Override
    public List<VerbasIdenizatorias> procurarPorDeputado(Integer idDeputado) throws PersistenceException {
        return idDeputado == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("VerbasIdenizatorias.findByDeputado")
            .setParameter("Deputado", idDeputado)
            .getResultList();
    }

    @Override
    public List<VerbasIdenizatorias> procurarPorDespesa(Integer idDespesa) throws PersistenceException {
        return idDespesa == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("VerbasIdenizatorias.findByDespesa")
            .setParameter("Despesa", idDespesa)
            .getResultList();
    }

    @Override
    public List<VerbasIdenizatorias> procurarPorValor(Double valor) throws PersistenceException {
        return valor == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("VerbasIdenizatorias.findByValor")
            .setParameter("valor", valor)
            .getResultList();
    }

    @Override
    public List<VerbasIdenizatorias> procurarPorData(Date data) throws PersistenceException {
        return data == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("VerbasIdenizatorias.findByData")
            .setParameter("data", data)
            .getResultList();
    }

    @Override
    public void inserir(VerbasIdenizatorias novo) throws PersistenceException {
        if(novo == null)
            throw new PersistenceException("Não é possível atualizar uma Verba Idenizatória nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.persist(novo);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void deletar(VerbasIdenizatorias remover) throws PersistenceException {
        if(remover == null)
            throw new PersistenceException("Não é possível atualizar uma Verba Idenizatória nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.refresh(remover);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void atualizar(VerbasIdenizatorias atualizado) throws PersistenceException {
        if(atualizado == null)
            throw new PersistenceException("Não é possível atualizar uma Verba Idenizatória nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.refresh(atualizado);
        manager.getTransaction().commit();

        manager.close();
    }
    
}
