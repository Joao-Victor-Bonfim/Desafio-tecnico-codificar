package fetcher.util.parser.json;

import fetcher.model.domain.RedeSocial;
import fetcher.util.parser.ParserGenerico;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author João Victor
 */
public class ParserRedeSocial implements ParserGenerico<RedeSocial>{
    
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String URL = "url";
    
    /**
     *  Função que transforma um arquivo JSON em uma lista de objetos
     * <code>RedeSocial</code>.
     * @param origem <code>InputStream</code> que fornecerá o arquivo JSON que
     * será parseado.
     * @return Uma lista contendo todos os partidos encontrados em origem na
     * forma de objetos.
     */
    @Override
    public List<RedeSocial> parse(InputStream origem) {
        
        List<RedeSocial> retorno = new ArrayList<>();
        JSONParser parser = new JSONParser();
        RedeSocial item;
        
        try {
            JSONArray array = (JSONArray) parser.parse(new InputStreamReader(origem));
            Iterator I = array.iterator();

            while(I.hasNext()) {
                JSONObject jsonObject = (JSONObject) I.next();
                
                item = new RedeSocial(Integer.parseInt((String) jsonObject.get(ID)),
                                        (String) jsonObject.get(NOME),
                                        (String) jsonObject.get(URL));
                
                retorno.add(item);
            }
        } catch (ParseException | IOException e) {
            System.err.println("Exceção em " + this.getClass().getName() + ":" + System.lineSeparator() + e);
            throw new RuntimeException("Exceção em " + this.getClass().getName());
        }
        
        return retorno;
    }
    
}
