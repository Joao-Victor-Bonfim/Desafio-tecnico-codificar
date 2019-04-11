package fetcher.model.dao;

import fetcher.model.domain.VerbasIdenizatorias;
import fetcher.model.domain.VerbasIdenizatoriasPK;
import java.util.ArrayList;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAOVerbasIdenizatorias {
    public ArrayList<VerbasIdenizatorias> listarTodos() throws PersistenceException;
    
    public void inserir(VerbasIdenizatorias novo) throws PersistenceException;
    
    public void deletar(VerbasIdenizatoriasPK id) throws PersistenceException;
    
    public void atualizar(VerbasIdenizatoriasPK id, VerbasIdenizatorias novo) throws PersistenceException;
}
