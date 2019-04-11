package fetcher.model.dao.impl;

import fetcher.model.dao.DAODeputado;
import fetcher.model.domain.Deputado;
import java.util.ArrayList;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public class DAOIDeputado implements DAODeputado{

    @Override
    public ArrayList<Deputado> listarTodos() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserir(Deputado novo) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Integer id) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Integer id, Deputado novo) throws PersistenceException {
        
    }
    
}
