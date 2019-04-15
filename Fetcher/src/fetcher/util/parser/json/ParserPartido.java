package fetcher.util.parser.json;

import fetcher.model.domain.Partido;
import fetcher.util.parser.ParserGenerico;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author João Victor
 */
public class ParserPartido implements ParserGenerico<Partido>{
    
    public static final String ID = "id";
    public static final String SIGLA = "sigla";
    
    /**
     *  Função que transforma um texto JSON em uma lista de objetos
     * <code>Partido</code>
     * @param origem <code>InputStream</code> que fornecerá o arquivo JSON que
     * será parseado.
     * @return Uma lista contendo todos os partidos em <code>original</code>
     * na forma de objetos <code>Partido</code>
     */
    @Override
    public List<Partido> parse(InputStream origem) {
        
        List<Partido> retorno = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Partido item;
        
        try {
            JSONArray array = (JSONArray) parser.parse(new InputStreamReader(origem));
            Iterator I = array.iterator();

            while(I.hasNext()) {
                JSONObject jsonObject = (JSONObject) I.next();
                
                item = new Partido(Integer.parseInt((String) jsonObject.get(ID)),
                        (String) jsonObject.get(SIGLA));
                
                retorno.add(item);
            }
            
        } catch (ParseException | IOException e) {
            System.err.println("Exceção em " + this.getClass().getName() + ":" + System.lineSeparator() + e);
            throw new RuntimeException("Exceção em " + this.getClass().getName());
        }
        
        return retorno;
    }
    
}
