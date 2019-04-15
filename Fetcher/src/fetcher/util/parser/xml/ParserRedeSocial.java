package fetcher.util.parser.xml;

import fetcher.model.domain.RedeSocial;
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
public class ParserRedeSocial implements ParserGenerico<RedeSocial>{
    public static final String REDESOCIAL = "redesocial";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String URL = "url";
    
    /**
     *  Função que transforma um arquivo XML em uma lista de objetos
     * <code>RedeSocial</code>.
     * @param origem <code>InputStream</code> que fornecerá o arquivo XML que
     * será parseado. 
     * @return Uma lista contendo todas as redes sociais em <code>origem</code>
     * na forma de objetos <code>RedeSocial</code>.
     */
    @Override
    public List<RedeSocial> parse(InputStream origem) {
        
        List<RedeSocial> retorno = new ArrayList<>();
        XMLEventReader leitorDeEventos;
        RedeSocial item = new RedeSocial();
        XMLEvent evento;
        StartElement elementoInicial;
        boolean fora = false;
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance()
                    .createXMLEventReader(origem);
            
            while (leitorDeEventos.hasNext()) {
                evento = leitorDeEventos.nextEvent();

                if (evento.isStartElement()) {
                    elementoInicial = evento.asStartElement();
                    if (elementoInicial.getName().getLocalPart().equals(REDESOCIAL)) {
                        item = new RedeSocial();
                        fora = false;
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
                        if(fora)
                            return retorno;
                        
                        item.setUrl(leitorDeEventos.nextEvent()
                                .asCharacters().getData());
                        continue;
                    }
                }
                
                if (evento.isEndElement()) {
                    if (evento.asEndElement()
                            .getName().getLocalPart().equals(REDESOCIAL)) {
                        retorno.add(item);
                        fora = true;
                    }
                }

            }
        } catch (XMLStreamException | RuntimeException e) {
            throw new RuntimeException("Exceção em " + this.getClass().getName() + ":" + System.lineSeparator() + e);
        }
        return retorno;
    }
}
