package fetcher.model.daoimpl;

import fetcher.model.dao.DAORedesocialDeputado;
import fetcher.model.domain.RedesocialDeputado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class DAOIRedesocialDeputado implements DAORedesocialDeputado {

    @Override
    public List<RedesocialDeputado> listarTodos() throws PersistenceException {
        return Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("RedesocialDeputado.findAll")
            .getResultList();
    }

    @Override
    public List<RedesocialDeputado> pesquisarPorDeputado(Integer idDeputado) throws PersistenceException {
        return idDeputado == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("RedesocialDeputado.findByDeputado")
            .setParameter("idDeputado", idDeputado)
            .getResultList();
    }

    @Override
    public List<RedesocialDeputado> pesquisarPorRedeSocial(Integer idRedeSocial) throws PersistenceException {
        return idRedeSocial == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("RedesocialDeputado.findByRedeSocial")
            .setParameter("idRedeSocial", idRedeSocial)
            .getResultList();
    }

    @Override
    public List<RedesocialDeputado> pesquisarPorUrl(String url) throws PersistenceException {
        return url == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("RedesocialDeputado.findByUrl")
            .setParameter("url", url)
            .getResultList();
    }

    @Override
    public void inserir(RedesocialDeputado novo) throws PersistenceException {
        if(novo == null)
            throw new PersistenceException("Não é possível inserir uma relação Rede Social com Deputado nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.persist(novo);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void deletar(RedesocialDeputado remover) throws PersistenceException {
        if(remover == null)
            throw new PersistenceException("Não é possível deletar uma relação Rede Social com Deputado nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.remove(remover);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void atualizar(RedesocialDeputado atualizado) throws PersistenceException {
        if(atualizado == null)
            throw new PersistenceException("Não é possível atualizar uma relação Rede Social com Deputado nula.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.refresh(atualizado);
        manager.getTransaction().commit();

        manager.close();
    }
    
}
