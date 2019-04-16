package fetcher.model.daoimpl;

import fetcher.model.dao.DAODeputado;
import fetcher.model.domain.Deputado;
import fetcher.util.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public class DAOIDeputado implements DAODeputado{
    
    private static final DAOIDeputado DAOIDeputado = new DAOIDeputado();
    
    private DAOIDeputado(){}
    
    public static DAOIDeputado getInstance() {
        return DAOIDeputado;
    }
    
    @Override
    public List<Deputado> listarTodos() throws PersistenceException {
        return (ArrayList<Deputado>) Persistence
                .createEntityManagerFactory("FetcherPU")
                .createEntityManager()
                .createNamedQuery("Deputado.findAll")
                .getResultList();
    }
    
    @Override
    public List<Deputado> procurarPorNome(String nome) throws PersistenceException {
        return nome == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("Deputado.findByNome")
            .setParameter("nome", nome)
            .getResultList();
    }

    @Override
    public List<Deputado> procurarPorId(Integer id) throws PersistenceException {
        if(id == null)
            return new ArrayList<>();
        List<Deputado> resultado = (List<Deputado>) Persistence
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
    public List<Deputado> procurarPorApelido(String apelido) throws PersistenceException {
        return apelido == null ? new ArrayList<>():
            Persistence.createEntityManagerFactory("FetcherPU")
            .createEntityManager()
            .createNamedQuery("Deputado.findByApelido")
            .setParameter("apelido", apelido)
            .getResultList();
    }

    @Override
    public void inserir(Deputado novo) throws PersistenceException {
        if(novo == null)
            throw new PersistenceException("Não é possível adicionar um Deputado nulo.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.persist(novo);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void deletar(Deputado remover) throws PersistenceException {
        if(remover == null)
            throw new PersistenceException("Não é possível remover um Deputado nulo.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.remove(remover);
        manager.getTransaction().commit();

        manager.close();
    }

    @Override
    public void atualizar(Deputado atualizado) throws PersistenceException {
        if(atualizado == null)
            throw new PersistenceException("Não é possível atualizar um Deputado nulo.");
        
        EntityManager manager = Persistence
            .createEntityManagerFactory("FetcherPU")
            .createEntityManager();

        manager.getTransaction().begin();
        manager.refresh(atualizado);
        manager.getTransaction().commit();

        manager.close();
    }
    
}
