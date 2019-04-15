package fetcher.util.parser.xml;

import fetcher.model.domain.Despesa;
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
public class ParserDespesa implements ParserGenerico<Despesa>{
    
    public static final String DESPESA = "despesa";
    public static final String ID = "id";
    public static final String DESCRICAO = "descricao";

    /**
     *  Função que transforma um texto XML em uma lista de objetos
     * <code>Despesa</code>.
     * @param origem <code>InputStream</code> que fornecerá o arquivo XML que
     * será parseado.
     * @return Uma lista contendo todas as despesas em <code>origem</code>
     * na forma de objetos <code>Despesa</code>.
     */
    @Override
    public List<Despesa> parse(InputStream origem) {
        List<Despesa> retorno = new ArrayList<>();
        XMLEventReader leitorDeEventos;
        Despesa item = new Despesa();
        XMLEvent evento;
        StartElement elementoInicial;
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance()
                    .createXMLEventReader(origem);
            while (leitorDeEventos.hasNext()) {
                evento = leitorDeEventos.nextEvent();

                if (evento.isStartElement()) {
                    elementoInicial = evento.asStartElement();
                    if (elementoInicial.getName().getLocalPart().equals(DESPESA)) {
                        item = new Despesa();
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(ID)) {
                        item.setId(Integer.parseInt(leitorDeEventos.nextEvent()
                                .asCharacters().getData()));
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(DESCRICAO)) {
                        item.setDescricao(leitorDeEventos.nextEvent()
                                .asCharacters().getData());
                        continue;
                    }
                }
                
                if (evento.isEndElement()) {
                    if (evento.asEndElement()
                            .getName().getLocalPart().equals(DESPESA)) {
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
