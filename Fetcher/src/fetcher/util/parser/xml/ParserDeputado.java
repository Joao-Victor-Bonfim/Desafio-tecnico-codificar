package fetcher.util.parser.xml;

import fetcher.model.daoimpl.DAOIPartido;
import fetcher.model.domain.Deputado;
import fetcher.model.domain.Partido;
import fetcher.util.parser.ParserGenerico;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class ParserDeputado implements ParserGenerico<Deputado>{
    public static final String DEPUTADO = "contato";
    public static final String ID = "id";
    public static final String NOME = "nomeServidor";
    public static final String APELIDO = "nome";
    public static final String ENDERECO = "endereco";
    public static final String NASCIMENTO = "dataNascimento";
    public static final String PARTIDO = "partido";
    public static final String REDESOCIAL = "redesSociais";
    
    /**
     *  Função que transforma um texto XML em uma lista de objetos
     * <code>Deputado</code>.
     * @param origem <code>InputStream</code> que fornecerá o arquivo XML que
     * será parseado.
     * @return Uma lista contendo todos os deputados em <code>origem</code>
     * na forma de objetos <code>Deputado</code>.
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
            leitorDeEventos = XMLInputFactory.newInstance()
                    .createXMLEventReader(origem);
            while (leitorDeEventos.hasNext()) {
                evento = leitorDeEventos.nextEvent();

                if (evento.isStartElement()) {
                    elementoInicial = evento.asStartElement();
                    if (elementoInicial.getName().getLocalPart().equals(DEPUTADO)) {
                        item = new Deputado();
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
                    
                    if (elementoInicial.getName().getLocalPart().equals(APELIDO)) {
                        item.setApelido(leitorDeEventos.nextEvent()
                                .asCharacters().getData());
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(ENDERECO)) {
                        item.setEndereco(leitorDeEventos.nextEvent()
                                .asCharacters().getData());
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(NASCIMENTO)) {
                        item.setNascimento(new SimpleDateFormat("dd/MM/yyyy")
                                .parse(leitorDeEventos.nextEvent().asCharacters().getData()));
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(PARTIDO)) {
                        List<Partido> partido = DAOIPartido.getInstance().pesquisarPorSigla(leitorDeEventos.nextEvent().asCharacters().getData());
                        if(partido.isEmpty())
                            throw new RuntimeException("O partido do Deputado não existe!");
                        
                        item.setPartido(partido.get(0));
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(REDESOCIAL)) {
                        item.setRedesocialDeputadoList(new ParserRedeSocialDeputado().parse(origem));
                    }
                }
                
                if (evento.isEndElement()) {
                    elementoFinal = evento.asEndElement();
                    if (elementoFinal.getName().getLocalPart().equals(DEPUTADO)) {
                        retorno.add(item);
                    }
                }

            }
        } catch (XMLStreamException | ParseException | RuntimeException e) {
            throw new RuntimeException("Exceção em " + this.getClass().getName() + ":" + System.lineSeparator() + e);
        }
        
        return retorno;
    }
}