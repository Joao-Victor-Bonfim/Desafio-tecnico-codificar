package fetcher.model.dao;

import fetcher.model.domain.Despesa;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAODespesa {
    public List<Despesa> listarTodos() throws PersistenceException;
    
    public List<Despesa> procurarPorId(Integer id) throws PersistenceException;
    
    public void inserir(Despesa novo) throws PersistenceException;
    
    public void deletar(Despesa remover) throws PersistenceException;
    
    public void atualizar(Despesa atualizado) throws PersistenceException;
}
