package fetcher.model.daoimpl;

import fetcher.model.dao.DAODespesa;
import fetcher.model.domain.Despesa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class DAOIDespesa implements DAODespesa {
    
    private static final DAOIDespesa DAOIDespesa = new DAOIDespesa();
    
    private DAOIDespesa(){}
    
    public static DAOIDespesa getInstance() {
        return DAOIDespesa;
    }
    
    @Override
    public List<Despesa> listarTodos() throws PersistenceException {
        return Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("Despesa.findAll")
            .getResultList();
    }

    @Override
    public List<Despesa> procurarPorId(Integer id) throws PersistenceException {
        return id == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("Despesa.findById")
            .setParameter("id", id)
            .getResultList();
    }

    @Override
    public void inserir(Despesa novo) throws PersistenceException {
        if(novo == null)
            throw new PersistenceException("Não é possível adicionar uma Despesa nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.persist(novo);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void deletar(Despesa remover) throws PersistenceException {
        if(remover == null)
            throw new PersistenceException("Não é possível remover uma Despesa nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.remove(remover);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void atualizar(Despesa atualizado) throws PersistenceException {
        if(atualizado == null)
            throw new PersistenceException("Não é possível atualizar uma Despesa nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.refresh(atualizado);
        manager.getTransaction().commit();

        manager.close();
    }
    
}
