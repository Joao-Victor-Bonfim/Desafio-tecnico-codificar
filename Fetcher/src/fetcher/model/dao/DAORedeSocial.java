package fetcher.model.dao;

import fetcher.model.domain.RedeSocial;
import java.util.ArrayList;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAORedeSocial {
    public ArrayList<RedeSocial> listarTodos() throws PersistenceException;
    
    public void inserir(RedeSocial novo) throws PersistenceException;
    
    public void deletar(Integer id) throws PersistenceException;
    
    public void atualizar(Integer id, RedeSocial novo) throws PersistenceException;
}
