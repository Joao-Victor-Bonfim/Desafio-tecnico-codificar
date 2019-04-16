package fetcher.controller.startup;

import fetcher.model.daoimpl.DAOIDeputado;
import fetcher.model.daoimpl.DAOIDespesa;
import fetcher.model.daoimpl.DAOIPartido;
import fetcher.model.daoimpl.DAOIRedeSocial;
import fetcher.model.daoimpl.DAOIVerbasIdenizatorias;
import fetcher.model.domain.Deputado;
import fetcher.model.domain.Despesa;
import fetcher.model.domain.Partido;
import fetcher.model.domain.RedeSocial;
import fetcher.model.domain.VerbasIdenizatorias;
import fetcher.util.parser.xml.ParserDeputado;
import fetcher.util.parser.xml.ParserDespesa;
import fetcher.util.parser.xml.ParserPartido;
import fetcher.util.parser.xml.ParserRedeSocial;
import fetcher.util.parser.xml.ParserVerbasIdenizatorias;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityExistsException;

/**
 *
 * @author João Victor
 */
public class RotinaDeStartup {
    
    private static final String ENDPARTIDOS = "http://dadosabertos.almg.gov.br/ws/representacao_partidaria/partidos";
    private static final String ENDREDESSOCIAIS = "";
    private static final String ENDDESPESAS = "";
    private static final String ENDVERBAS = "http://dadosabertos.almg.gov.br/ws/prestacao_contas/verbas_indenizatorias/legislatura_atual/deputados/";
    private static final String ENDDEPUTADOS = "http://dadosabertos.almg.gov.br/ws/deputados/lista_telefonica";

    private RotinaDeStartup() {}
    
    public static void startup() {
        loadPartidos();
        loadRedesSociais();
        loadDespesas();
        loadVerbasIdenizatorias();
        loadDeputados();
    }
    
    private static void loadPartidos() {
        try {
            URL url = new URL(ENDPARTIDOS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/XML");
            
            if (con.getResponseCode() != 200) {
                throw new RuntimeException("Falhou!" + System.lineSeparator()
                            + "Código de erro HTTP:" + System.lineSeparator()
                            + con.getResponseCode());
            }
            
            ParserPartido parser = new ParserPartido();
            List<Partido> partidos = parser.parse(con.getInputStream());
            Iterator<Partido> I = partidos.iterator();
            
            while(I.hasNext()) {
                Partido item = I.next();
                try {
                    DAOIPartido.getInstance().inserir(item);
                } catch (EntityExistsException e) {
                    DAOIPartido.getInstance().atualizar(item);
                }
            }
            
            con.disconnect();
            
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Exceção em RotinaDeStartup:" + System.lineSeparator()
                                        + e);
        }
    }
    
    private static void loadRedesSociais() {
        try {
            
            File arquivo = new File(ENDREDESSOCIAIS);
            
            ParserRedeSocial parser = new ParserRedeSocial();
            List<RedeSocial> partidos = parser.parse(new FileInputStream(arquivo));
            Iterator<RedeSocial> I = partidos.iterator();
            
            while(I.hasNext()) {
                RedeSocial item = I.next();
                try {
                    DAOIRedeSocial.getInstance().inserir(item);
                } catch (EntityExistsException e) {
                    DAOIRedeSocial.getInstance().atualizar(item);
                }
            }
            
            
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Exceção em RotinaDeStartup:" + System.lineSeparator()
                                        + e);
        }
    }
    
    private static void loadDespesas() {
        try {
            
            File arquivo = new File(ENDDESPESAS);
            
            ParserDespesa parser = new ParserDespesa();
            List<Despesa> partidos = parser.parse(new FileInputStream(arquivo));
            Iterator<Despesa> I = partidos.iterator();
            
            while(I.hasNext()) {
                Despesa item = I.next();
                try {
                    DAOIDespesa.getInstance().inserir(item);
                } catch (EntityExistsException e) {
                    DAOIDespesa.getInstance().atualizar(item);
                }
            }
            
            
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Exceção em RotinaDeStartup:" + System.lineSeparator()
                                        + e);
        }
    }
    
    private static void loadVerbasIdenizatorias() {
        
        Iterator<Deputado> I = DAOIDeputado.getInstance().listarTodos().iterator();
        Iterator<VerbasIdenizatorias> J;
        int mes, ano, anoAtual = Calendar.getInstance().get(Calendar.YEAR), idDeputado;
        URL url;
        HttpURLConnection con;
        ParserVerbasIdenizatorias parser;
        
        try {
            url = new URL(ENDVERBAS + "0/0/0");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/XML");

            if (con.getResponseCode() != 200) {
                throw new RuntimeException("Falhou!" + System.lineSeparator()
                            + "Código de erro HTTP:" + System.lineSeparator()
                            + con.getResponseCode());
            }
            
        } catch(IOException e) {
           throw new RuntimeException("Exceção em RotinaDeStartup:"
                                    + System.lineSeparator() + e);
        }
        
        
        try {
            while(I.hasNext()) {
                
                idDeputado = I.next().getId();                
                ano = 1970;
                while(ano < anoAtual) {
                    
                    mes = 1;
                    while(mes < 12) {

                        url = new URL(ENDVERBAS + idDeputado + "/" + ano + "/" + mes);
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("Accept", "application/XML");

                        parser = new ParserVerbasIdenizatorias();
                        J = parser.parse(con.getInputStream()).iterator();

                        while(J.hasNext()) {
                            VerbasIdenizatorias item = J.next();
                            try {
                                DAOIVerbasIdenizatorias.getInstance().inserir(item);
                            } catch (EntityExistsException e) {
                                DAOIVerbasIdenizatorias.getInstance().atualizar(item);
                            }
                        }

                        con.disconnect();
                        mes++;
                    }
                    ano++;
                }
            }
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Exceção em RotinaDeStartup:" + System.lineSeparator()
                                        + e);
        }
    }
    
    private static void loadDeputados() {
        try {
            URL url = new URL(ENDDEPUTADOS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/XML");
            
            if (con.getResponseCode() != 200) {
                throw new RuntimeException("Falhou!" + System.lineSeparator()
                            + "Código de erro HTTP:" + System.lineSeparator()
                            + con.getResponseCode());
            }
            
            ParserDeputado parser = new ParserDeputado();
            List<Deputado> deputados = parser.parse(con.getInputStream());
            Iterator<Deputado> I = deputados.iterator();
            
            while(I.hasNext()) {
                Deputado item = I.next();
                try {
                    DAOIDeputado.getInstance().inserir(item);
                } catch (EntityExistsException e) {
                    DAOIDeputado.getInstance().atualizar(item);
                }
            }
            
            con.disconnect();
            
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Exceção em RotinaDeStartup:" + System.lineSeparator()
                                        + e);
        }
    }
}