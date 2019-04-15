package fetcher.util.parser;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author João Victor
 * @param <Tipo>
 */
public interface ParserGenerico <Tipo> {

    /**
     *  Uma função que recebe uma <code>InputStream</code> e a transforma o
     * conteúdo que ela lê em uma lista de objetos <code>Tipo</code>.
     * @param origem Uma String contendo o texto a ser parseado.
     * @return Uma lista contendo objetos <code>Tipo</code> a partir de original.
     */
    public List<Tipo> parse(InputStream origem);
}
