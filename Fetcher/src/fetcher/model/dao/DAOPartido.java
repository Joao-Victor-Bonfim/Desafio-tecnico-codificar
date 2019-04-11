package fetcher.model.dao;

import fetcher.model.domain.Partido;
import java.util.ArrayList;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAOPartido {
    public ArrayList<Partido> listarTodos() throws PersistenceException;
    
    public void inserir(Partido novo) throws PersistenceException;
    
    public void deletar(Integer id) throws PersistenceException;
    
    public void atualizar(Integer id, Partido novo) throws PersistenceException;
}
