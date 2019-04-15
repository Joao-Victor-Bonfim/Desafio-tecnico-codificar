package fetcher.util.parser.xml;

import fetcher.model.domain.Partido;
import fetcher.util.parser.ParserGenerico;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author João Victor
 */
public class ParserPartido implements ParserGenerico<Partido> {
    
    public static final String PARTIDO = "partido";
    public static final String ID = "id";
    public static final String SIGLA = "sigla";
    
    /**
     *  Função que transforma um texto XML em uma lista de objetos
     * <code>Partido</code>.
     * @param origem Texto JSON que sofrerá parse. 
     * @return Uma lista contendo todos os partidos em <code>original</code>
     * na forma de objetos <code>Partido</code>.
     */
    @Override
    public List<Partido> parse(InputStream origem) {
        
        List<Partido> retorno = new ArrayList<>();
        XMLEventReader leitorDeEventos;
        Partido item = new Partido();
        XMLEvent evento;
        StartElement elementoInicial;
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance()
                    .createXMLEventReader(origem);
            
            while (leitorDeEventos.hasNext()) {
                evento = leitorDeEventos.nextEvent();

                if (evento.isStartElement()) {
                    
                    elementoInicial = evento.asStartElement();
                    if (elementoInicial.getName().getLocalPart().equals(PARTIDO)) {
                        item = new Partido();
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(ID)) {
                        item.setId(Integer.parseInt(leitorDeEventos.nextEvent()
                                .asCharacters().getData()));
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(SIGLA)) {
                        item.setSigla(leitorDeEventos.nextEvent()
                                .asCharacters().getData());
                        continue;
                    }
                }
                
                if (evento.isEndElement()) {
                    if (evento.asEndElement().
                            getName().getLocalPart().equals(PARTIDO)) {
                        retorno.add(item);
                    }
                }

            }
        } catch (XMLStreamException | RuntimeException e) {
            throw new RuntimeException("Exceção em " + this.getClass().getName() + ":" + System.lineSeparator() + e);
        }
        return retorno;
    }
}
