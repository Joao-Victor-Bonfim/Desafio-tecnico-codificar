package fetcher.model.dao;

import fetcher.model.domain.Deputado;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAODeputado {
    
    public List<Deputado> listarTodos() throws PersistenceException;
    
    public List<Deputado> procurarPorNome(String nome) throws PersistenceException;
    
    public List<Deputado> procurarPorId(Integer id) throws PersistenceException;
    
    public List<Deputado> procurarPorApelido(String apelido) throws PersistenceException;
    
    public void inserir(Deputado novo) throws PersistenceException;
    
    public void deletar(Deputado remover) throws PersistenceException;
    
    public void atualizar(Deputado atualizado) throws PersistenceException;
}