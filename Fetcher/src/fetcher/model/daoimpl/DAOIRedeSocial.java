package fetcher.model.daoimpl;

import fetcher.model.dao.DAORedeSocial;
import fetcher.model.domain.RedeSocial;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class DAOIRedeSocial implements DAORedeSocial {
    
    private static final DAOIRedeSocial DAOIRedeSocial = new DAOIRedeSocial();
    
    private DAOIRedeSocial(){}
    
    public static DAOIRedeSocial getInstance() {
        return DAOIRedeSocial;
    }
    
    @Override
    public List<RedeSocial> listarTodos() throws PersistenceException {
        return Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("RedeSocial.findAll")
            .getResultList();
    }

    @Override
    public List<RedeSocial> pesquisarPorId(Integer id) throws PersistenceException {
        return id == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("RedeSocial.findById")
            .setParameter("id", id)
            .getResultList();
    }

    @Override
    public List<RedeSocial> pesquisarPorNome(String nome) throws PersistenceException {
        return nome == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("RedeSocial.findByNome")
            .setParameter("nome", nome)
            .getResultList();
    }

    @Override
    public List<RedeSocial> pesquisarPorUrl(String url) throws PersistenceException {
        return url == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("RedeSocial.findByUrl")
            .setParameter("url", url)
            .getResultList();
    }

    @Override
    public void inserir(RedeSocial novo) throws PersistenceException {
        if(novo == null)
            throw new PersistenceException("Não é possível adicionar uma Rede Social nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.persist(novo);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void deletar(RedeSocial remover) throws PersistenceException {
        if(remover == null)
            throw new PersistenceException("Não é possível remover uma Rede Social nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.remove(remover);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void atualizar(RedeSocial atualizado) throws PersistenceException {
        if(atualizado == null)
            throw new PersistenceException("Não é possível atualizar uma Rede Social nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.refresh(atualizado);
        manager.getTransaction().commit();

        manager.close();
    }
    
}
