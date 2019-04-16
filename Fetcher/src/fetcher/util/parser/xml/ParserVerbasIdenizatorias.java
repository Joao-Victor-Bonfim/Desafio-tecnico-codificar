package fetcher.util.parser.xml;

import fetcher.model.daoimpl.DAOIDeputado;
import fetcher.model.daoimpl.DAOIDespesa;
import fetcher.model.domain.Deputado;
import fetcher.model.domain.Despesa;
import fetcher.model.domain.VerbasIdenizatorias;
import fetcher.util.parser.ParserGenerico;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author João Victor
 */
public class ParserVerbasIdenizatorias implements ParserGenerico<VerbasIdenizatorias> {
    
    
    public static final String VERBAIDENIZATORIA = "despesa";
    public static final String VALOR = "valor";
    public static final String DATA = "dataReferencia";
    public static final String TDESPESA = "codTipoDespesa";
    public static final String DEPUTADO = "idDeputado";

    /**
     *  Função que transforma um arquivo XML em uma lista de objetos
     * <code>VerbasIdenizatorias</code>.
     * @param origem <code>InputStream</code> que fornecerá o arquivo XML que
     * será parseado.
     * @return Uma lista contendo todas as verbas idenizatorias em <code>origem</code>
     * na forma de objetos <code>VerbasIdenizatorias</code>.
     */
    @Override
    public List<VerbasIdenizatorias> parse(InputStream origem) {
        List<VerbasIdenizatorias> retorno = new ArrayList<>();
        XMLEventReader leitorDeEventos;
        VerbasIdenizatorias item = new VerbasIdenizatorias();
        XMLEvent evento;
        StartElement elementoInicial;
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance()
                    .createXMLEventReader(origem);
            while (leitorDeEventos.hasNext()) {
                evento = leitorDeEventos.nextEvent();

                if (evento.isStartElement()) {
                    
                    elementoInicial = evento.asStartElement();
                    if (elementoInicial.getName().getLocalPart().equals(VERBAIDENIZATORIA)) {
                        item = new VerbasIdenizatorias();
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(VALOR)) {
                        item.setValor(NumberFormat.getNumberInstance(new Locale("pt", "br"))
                                                .parse(elementoInicial.asCharacters().getData())
                                                .doubleValue());
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(DATA)) {
                        item.setData(new SimpleDateFormat("yyy-MM-dd")
                                            .parse( leitorDeEventos.nextEvent().asCharacters().getData() ) );
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(TDESPESA)) {
                        List<Despesa> despesa = DAOIDespesa.getInstance().procurarPorId(
                                Integer.parseInt( leitorDeEventos.nextEvent().asCharacters().getData() ) );
                        
                        if(despesa.size() != 1)
                            throw new RuntimeException("O tipo de despesa dado não existe.");
                        
                        item.getVerbasIdenizatoriasPK().setIdDespesa(despesa.get(0).getId());
                        item.setTipoDespesa(despesa.get(0));
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(DEPUTADO)) {
                        
                        item.getVerbasIdenizatoriasPK().setIdDeputado(
                                Integer.parseInt( leitorDeEventos.nextEvent().asCharacters().getData() ) );
                        continue;
                    }
                }
                
                if (evento.isEndElement()) {
                    if (evento.asEndElement()
                            .getName().getLocalPart().equals(VERBAIDENIZATORIA)) {
                        retorno.add(item);
                    }
                }

            }
        } catch (XMLStreamException | RuntimeException | ParseException e) {
            throw new RuntimeException("Exceção em " + this.getClass().getName() + ":" + System.lineSeparator() + e);
        }
        
        return retorno;
    }
    
}
