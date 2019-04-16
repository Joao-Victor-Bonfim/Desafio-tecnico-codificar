package fetcher.model.daoimpl;

import fetcher.model.dao.DAODespesa;
import fetcher.model.domain.Despesa;
import fetcher.util.exception.BusinessException;
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
        if(id == null)
            return new ArrayList<>();
        List<Despesa> resultado = (List<Despesa>) Persistence
                                    .createEntityManagerFactory("FetcherPU")
                                    .createEntityManager()
                                    .createNamedQuery("Despesa.findById")
                                    .setParameter("id", id)
                                    .getResultList();
        if(!resultado.isEmpty() && resultado.size() != 1)
            throw new BusinessException("Não podem existir mais de uma despesa com mesmo id.");
        return resultado;
            
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
