package fetcher.model.dao;

import fetcher.model.domain.RedesocialDeputado;
import fetcher.model.domain.RedesocialDeputadoPK;
import java.util.ArrayList;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAORedesocialDeputado {
    public ArrayList<RedesocialDeputado> listarTodos() throws PersistenceException;
    
    public void inserir(RedesocialDeputado novo) throws PersistenceException;
    
    public void deletar(RedesocialDeputadoPK id) throws PersistenceException;
    
    public void atualizar(RedesocialDeputadoPK id, RedesocialDeputado novo) throws PersistenceException;
}
