package fetcher.model.dao;

import fetcher.model.domain.VerbasIdenizatorias;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author João Victor
 */
public interface DAOVerbasIdenizatorias {
    public List<VerbasIdenizatorias> listarTodos() throws PersistenceException;
    
    public List<VerbasIdenizatorias> procurarPorDeputado(Integer idDeputado) throws PersistenceException;
    
    public List<VerbasIdenizatorias> procurarPorDespesa(Integer idDespesa) throws PersistenceException;
    
    public List<VerbasIdenizatorias> procurarPorValor(Double valor) throws PersistenceException;
    
    public List<VerbasIdenizatorias> procurarPorData(Date data) throws PersistenceException;
    
    public void inserir(VerbasIdenizatorias novo) throws PersistenceException;
    
    public void deletar(VerbasIdenizatorias remover) throws PersistenceException;
    
    public void atualizar(VerbasIdenizatorias atualizado) throws PersistenceException;
}
