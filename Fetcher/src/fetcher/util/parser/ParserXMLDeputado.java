package fetcher.util.parser;

import fetcher.model.domain.Deputado;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author João Victor
 */
public class ParserXMLDeputado implements ParserGenerico<Deputado>{
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
    public List<Deputado> parse(InputStream origem) {
        
        List<Deputado> retorno = new ArrayList<>();
        XMLEventReader leitorDeEventos;
        Deputado item = new Deputado();
        XMLEvent evento;
        StartElement elementoInicial;
        EndElement elementoFinal;
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance().createXMLEventReader(origem);
            while (leitorDeEventos.hasNext()) {
                evento = leitorDeEventos.nextEvent();

                if (evento.isStartElement()) {
                    elementoInicial = evento.asStartElement();
                    if (elementoInicial.getName().getLocalPart().equals(PARTIDO)) {
                        item = new Deputado();
                    }
                    
                    if (evento.asStartElement().getName().getLocalPart().equals(ID)) {
                        evento = leitorDeEventos.nextEvent();
                        item.setId(Integer.parseInt(evento.asCharacters().getData()));
                        continue;
                    }
                    
                    if (evento.asStartElement().getName().getLocalPart().equals(SIGLA)) {
                        evento = leitorDeEventos.nextEvent();
                        item.setSigla(evento.asCharacters().getData());
                        continue;
                    }
                }
                
                if (evento.isEndElement()) {
                    elementoFinal = evento.asEndElement();
                    if (elementoFinal.getName().getLocalPart().equals(PARTIDO)) {
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
