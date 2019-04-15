package fetcher.model.daoimpl;

import fetcher.model.dao.DAOPartido;
import fetcher.model.domain.Partido;
import fetcher.util.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class DAOIPartido implements DAOPartido {
    
    private static final DAOIPartido DAOIPartido = new DAOIPartido();
    
    private DAOIPartido(){}
    
    public static DAOIPartido getInstance() {
        return DAOIPartido;
    }
    
    @Override
    public List<Partido> listarTodos() throws PersistenceException {
        return (ArrayList<Partido>) Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("Partido.findAll")
            .getResultList();
    }

    @Override
    public List<Partido> pesquisarPorSigla(String sigla) throws PersistenceException {
        if(sigla == null)
            return new ArrayList<>();
        else {
            List<Partido> resultado = Persistence.createEntityManagerFactory("FetcherPU")
                                .createEntityManager()
                                .createNamedQuery("Partido.findBySigla")
                                .setParameter("sigla", sigla)
                                .getResultList();
            
            int tam = resultado.size();
            
            if(tam != 1 && tam != 0)
                throw new BusinessException("Não pode existir mais de um partido com a mesma sigla.");
            
            return resultado;
        }
    }

    @Override
    public List<Partido> pesquisrPorId(Integer id) throws PersistenceException {
        return id == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("Partido.findById")
            .setParameter("id", id)
            .getResultList();
    }

    @Override
    public void inserir(Partido novo) throws PersistenceException {
        if(novo == null)
            throw new PersistenceException("Não é possível adicionar um Partido nulo.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.persist(novo);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void deletar(Partido remover) throws PersistenceException {
        if(remover == null)
            throw new PersistenceException("Não é possível remover um Partido nulo.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.remove(remover);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void atualizar(Partido atualizado) throws PersistenceException {
        if(atualizado == null)
            throw new PersistenceException("Não é possível atualizar um Partido nulo.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.refresh(atualizado);
        manager.getTransaction().commit();

        manager.close();
    }
    
}
