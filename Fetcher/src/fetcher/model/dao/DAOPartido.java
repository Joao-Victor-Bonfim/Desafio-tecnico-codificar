package fetcher.model.dao;

import fetcher.model.domain.Partido;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAOPartido {
    public List<Partido> listarTodos() throws PersistenceException;
    
    public List<Partido> pesquisarPorSigla(String sigla) throws PersistenceException;
    
    public List<Partido> pesquisrPorId(Integer id) throws PersistenceException;
    
    public void inserir(Partido novo) throws PersistenceException;
    
    public void deletar(Partido remover) throws PersistenceException;
    
    public void atualizar(Partido atualizado) throws PersistenceException;
}
