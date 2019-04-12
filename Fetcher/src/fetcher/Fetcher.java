
package fetcher;

import fetcher.model.domain.Partido;
import fetcher.util.parser.ParserXMLPartido;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author João Victor
 */
public class Fetcher {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://dadosabertos.almg.gov.br/ws/representacao_partidaria/partidos");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/xml");
            
            if (con.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + con.getResponseCode());
            }
            
            ParserXMLPartido teste1 = new ParserXMLPartido();
            List<Partido> teste2 = teste1.parse(con.getInputStream());
            
            Iterator<Partido> teste3 = teste2.iterator();
            while(teste3.hasNext()) {
                System.out.println(teste3.next());
            }
            
            con.disconnect();
            
        } catch (IOException | RuntimeException e) {
            System.err.println("Exceção em Fetcher:" + System.lineSeparator() + e);
        }
    }
    
}
