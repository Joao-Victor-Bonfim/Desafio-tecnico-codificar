package fetcher.util.parser;

import fetcher.model.daoimpl.DAOIPartido;
import fetcher.model.domain.Deputado;
import fetcher.model.domain.Partido;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author João Victor
 */
public class ParserXMLDeputado implements ParserGenerico<Deputado>{
    public static final String DEPUTADO = "contato";
    public static final String ID = "id";
    public static final String NOME = "nomeServidor";
    public static final String APELIDO = "nome";
    public static final String ENDERECO = "endereco";
    public static final String NASCIMENTO = "dataNascimento";
    public static final String PARTIDO = "partido";
    
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
        
        try {
            leitorDeEventos = XMLInputFactory.newInstance().createXMLEventReader(origem);
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
                        item.setNascimento(new SimpleDateFormat().parse(leitorDeEventos.nextEvent()
                                .asCharacters().getData()));
                        continue;
                    }
                    
                    if (elementoInicial.getName().getLocalPart().equals(PARTIDO)) {
                        List<Partido> partido = DAOIPartido.getInstance().pesquisarPorSigla(leitorDeEventos.nextEvent().asCharacters().getData());
                        
                        if(partido.size() > 1)
                            throw new PersistenceException("Não podem existir mais de um partido com a mesma sigla." + System.lineSeparator() + "Por favor informe isto ao administrador do sistema.");
                        if(partido.isEmpty())
                            throw new PersistenceException("O partido dado não existe." + System.lineSeparator() + "Por favor informe isto ao administrador do sistema.");
                        
                        item.setPartido(partido.get(0));
                        continue;
                    }
                }
                
                if (evento.isEndElement()) {
                    if (evento.asEndElement()
                            .getName().getLocalPart().equals(DEPUTADO)) {
                        retorno.add(item);
                    }
                }

            }
        } catch (XMLStreamException | ParseException e) {
            System.err.println("Exceção em ParserXMLDeputado:" + System.lineSeparator() + e);
        }
        
        return retorno;
    }
}
