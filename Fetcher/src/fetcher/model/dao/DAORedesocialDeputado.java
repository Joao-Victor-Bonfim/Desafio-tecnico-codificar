package fetcher.model.dao;

import fetcher.model.domain.RedesocialDeputado;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAORedesocialDeputado {
    public List<RedesocialDeputado> listarTodos() throws PersistenceException;
    
    public List<RedesocialDeputado> pesquisarPorDeputado(Integer idDeputado) throws PersistenceException;
    
    public List<RedesocialDeputado> pesquisarPorRedeSocial(Integer idRedeSocial) throws PersistenceException;
    
    public List<RedesocialDeputado> pesquisarPorUrl(String url) throws PersistenceException;
    
    public void inserir(RedesocialDeputado novo) throws PersistenceException;
    
    public void deletar(RedesocialDeputado remover) throws PersistenceException;
    
    public void atualizar(RedesocialDeputado atualizado) throws PersistenceException;
}
