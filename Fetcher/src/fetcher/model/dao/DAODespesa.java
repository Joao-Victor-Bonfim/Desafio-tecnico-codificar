package fetcher.model.dao;

import fetcher.model.domain.Despesa;
import java.util.ArrayList;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAODespesa {
    public ArrayList<Despesa> listarTodos() throws PersistenceException;
    
    public void inserir(Despesa novo) throws PersistenceException;
    
    public void deletar(Integer id) throws PersistenceException;
    
    public void atualizar(Integer id, Despesa novo) throws PersistenceException;
}
