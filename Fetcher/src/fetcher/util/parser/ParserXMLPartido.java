package fetcher.util.parser;

import fetcher.model.domain.Partido;
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
public class ParserXMLPartido implements ParserGenerico<Partido>{
    
    public static final String PARTIDO = "partido";
    public static final String ID = "id";
    public static final String SIGLA = "sigla";
    
    /**
     *
     * @param origem InputStream de onde virá o XML que sofrerá parse. 
     * @return Uma lista contendo todos os partidos encontrados em origem na
     * forma de objetos.
     */
    @Override
    public List<Partido> parse(InputStream origem) {
        
        List<Partido> retorno = new ArrayList<>();
        XMLEventReader leitorDeEventos;
        Partido item = new Partido();
        XMLEvent evento;
        StartElement elementoInicial;
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance().createXMLEventReader(origem);
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
        } catch (XMLStreamException e) {
            System.err.println("Exceção em ParserXMLPartido:" + System.lineSeparator() + e);
        }
        return retorno;
    }
    
}
