package fetcher.util.parser.json;

import fetcher.model.daoimpl.DAOIPartido;
import fetcher.model.domain.Deputado;
import fetcher.util.parser.ParserGenerico;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author João Victor
 */
public class ParserDeputado implements ParserGenerico<Deputado>{
    
    public static final String ID = "id";
    public static final String NOME = "nomeServidor";
    public static final String APELIDO = "nome";
    public static final String ENDERECO = "endereco";
    public static final String NASCIMENTO = "dataNascimento";
    public static final String PARTIDO = "partido";
    
    /**
     *  Função que transforma um arquvio JSON em uma lista de objetos Deputado.
     * @param origem <code>InputStream</code> que fornecerá o arquivo JSON que
     * será parseado.
     * @return Uma lista contendo todos os deputados encontrados em origem na
     * forma de objetos.
     */
    @Override
    public List<Deputado> parse(InputStream origem) {
        
        List<Deputado> retorno = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Deputado item;
        
        try {
            JSONArray array = (JSONArray) parser.parse(new InputStreamReader(origem));
            Iterator I = array.iterator();

            while(I.hasNext()) {
                JSONObject jsonObject = (JSONObject) I.next();
                
                item = new Deputado(Integer.parseInt((String) jsonObject.get(ID)),
                    (String) jsonObject.get(APELIDO),
                    (String) jsonObject.get(NOME),
                    (String) jsonObject.get(ENDERECO),
                    SimpleDateFormat.getDateInstance(DateFormat.SHORT, new Locale( "PT", "BR")).parse((String) jsonObject.get(NASCIMENTO)));
                
                item.setPartido(DAOIPartido.getInstance().pesquisarPorSigla((String) jsonObject.get(PARTIDO)).get(0));
                
                //TO-DO: Criar código que relaciona redes sociais e despesas com o Deputado
                
                retorno.add(item);
            }
        } catch (ParseException | java.text.ParseException | IOException e) {
            System.err.println("Exceção em " + this.getClass().getName() + ":" + System.lineSeparator() + e);
            throw new RuntimeException("Exceção em " + this.getClass().getName());
        }
        
        return retorno;
    }
}
