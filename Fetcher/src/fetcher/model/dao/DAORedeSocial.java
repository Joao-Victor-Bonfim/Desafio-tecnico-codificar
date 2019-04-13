package fetcher.model.dao;

import fetcher.model.domain.RedeSocial;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAORedeSocial {
    public List<RedeSocial> listarTodos() throws PersistenceException;
    
    public List<RedeSocial> pesquisarPorId(Integer id) throws PersistenceException;
    
    public List<RedeSocial> pesquisarPorNome(String nome) throws PersistenceException;
    
    public List<RedeSocial> pesquisarPorUrl(String url) throws PersistenceException;
    
    public void inserir(RedeSocial novo) throws PersistenceException;
    
    public void deletar(RedeSocial remover) throws PersistenceException;
    
    public void atualizar(RedeSocial atualizado) throws PersistenceException;
}
