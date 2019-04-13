package fetcher.util.parser;

import fetcher.model.domain.RedeSocial;
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
public class ParserXMLRedeSocial implements ParserGenerico<RedeSocial>{
    
    public static final String REDESOCIAL = "redesocial";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String URL = "url";
    
    /**
     *
     * @param origem InputStream de onde virá o XML que sofrerá parse. 
     * @return Uma lista contendo todos os partidos encontrados em origem na
     * forma de objetos.
     */
    @Override
    public List<RedeSocial> parse(InputStream origem) {
        
        List<RedeSocial> retorno = new ArrayList<>();
        XMLEventReader leitorDeEventos;
        RedeSocial item = new RedeSocial();
        XMLEvent evento;
        StartElement elementoInicial;
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance().createXMLEventReader(origem);
            while (leitorDeEventos.hasNext()) {
                evento = leitorDeEventos.nextEvent();

                if (evento.isStartElement()) {
                    elementoInicial = evento.asStartElement();
                    if (elementoInicial.getName().getLocalPart().equals(REDESOCIAL)) {
                        item = new RedeSocial();
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(ID)) {
                        item.setId(Integer.parseInt(leitorDeEventos.nextEvent()
                                .asCharacters().getData()));
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(NOME)) {
                        item.setNome(leitorDeEventos.nextEvent()
                                .asCharacters().getData());
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(URL)) {
                        item.setUrl(leitorDeEventos.nextEvent()
                                .asCharacters().getData());
                        continue;
                    }
                }
                
                if (evento.isEndElement()) {
                    if (evento.asEndElement()
                            .getName().getLocalPart().equals(REDESOCIAL)) {
                        retorno.add(item);
                    }
                }

            }
        } catch (XMLStreamException e) {
            System.err.println("Exceção em ParserXMLRedeSocial:" + System.lineSeparator() + e);
        }
        return retorno;
    }
    
}
