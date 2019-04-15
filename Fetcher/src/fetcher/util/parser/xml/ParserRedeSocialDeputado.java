package fetcher.util.parser.xml;

import fetcher.model.domain.Deputado;
import fetcher.model.domain.RedeSocial;
import fetcher.model.domain.RedesocialDeputado;
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
public class ParserRedeSocialDeputado implements ParserGenerico<RedesocialDeputado>{
    
    //TO-DO
    public static final String REDESOCIALDEPUTADO = "redeSocialDeputado";
    public static final String DEPUTADO = "deputado";
    public static final String REDESOCIAL = "redeSocial";
    public static final String URL = "url";
    
    /**
     *  Função que transforma um texto XML em uma lista de objetos
     * <code>RedesocialDeputado</code>.
     * @param origem <code>InputStream</code> que fornecerá o arquivo XML que
     * será parseado.
     * @return Uma lista contendo todas as relações RedeSocial - Deputado em
     * <code>original</code> na forma de objetos <code>RedesocialDeputado</code>.
     */
    @Override
    public List<RedesocialDeputado> parse(InputStream origem) {
        List<RedesocialDeputado> retorno = new ArrayList<>();
        XMLEventReader leitorDeEventos;
        RedesocialDeputado item = new RedesocialDeputado();
        XMLEvent evento;
        StartElement elementoInicial;
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance()
                    .createXMLEventReader(origem);
            
            while (leitorDeEventos.hasNext()) {
                evento = leitorDeEventos.nextEvent();

                if (evento.isStartElement()) {
                    elementoInicial = evento.asStartElement();
                    if(elementoInicial.getName().getLocalPart().equals(REDESOCIALDEPUTADO)) {
                        item = new RedesocialDeputado();
                        continue;
                    }

                    if(elementoInicial.getName().getLocalPart().equals(REDESOCIAL)) {
                        List<RedeSocial> redesSociais = new ParserRedeSocial().parse(origem);
                        if(redesSociais.size() != 1)
                            throw new RuntimeException("Mais de uma rede social em uma relação rede social - deputado.");
                        item.setRedeSocial(redesSociais.get(0));
                        continue;
                    }

                    if(elementoInicial.getName().getLocalPart().equals(URL)) {
                        item.setUrl(leitorDeEventos.nextEvent().asCharacters().getData());
                        continue;
                    }
                    
                    if(elementoInicial.getName().getLocalPart().equals(DEPUTADO)) {
                        throw new UnsupportedOperationException("A operção de parse com a key Deputado ainda não é suportada.");
                    }
                }

                if (evento.isEndElement()) {
                    if (evento.asEndElement().
                            getName().getLocalPart().equals(REDESOCIALDEPUTADO)) {
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
