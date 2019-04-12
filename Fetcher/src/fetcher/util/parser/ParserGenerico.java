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
     *
     * @param origem
     * @return
     */
    public List<Tipo> parse(InputStream origem);
}
