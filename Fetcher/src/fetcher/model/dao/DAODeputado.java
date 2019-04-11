package fetcher.model.dao;

import fetcher.model.domain.Deputado;
import java.util.ArrayList;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAODeputado {
    
    public ArrayList<Deputado> listarTodos() throws PersistenceException;
    
    public void inserir(Deputado novo) throws PersistenceException;
    
    public void deletar(Integer id) throws PersistenceException;
    
    public void atualizar(Integer id, Deputado novo) throws PersistenceException;
}