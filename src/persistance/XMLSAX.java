package persistance;

import core.PersistanceTache;
import core.Tache;
import core.TacheList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class XMLSAX implements PersistanceTache{
    PrintWriter pw=null;

    public XMLSAX() {
    }

    @Override
    public void charger(TacheList tacheList) {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();//pour lire le fichier xml;
        SAXParser parser = null;
        try {
            parser = parserFactor.newSAXParser();
            SAXHandler handler = new SAXHandler(tacheList);
            parser.parse(ClassLoader.getSystemResourceAsStream("persistance/Task.xml"), handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enregistrer(TacheList tacheList) {
        boolean openedHere = false;
        if(pw==null){
            try {
                pw = new PrintWriter(new FileWriter ("./src/persistance/Task.xml"));//ouverture du fichier
                openedHere=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pw.println("<taches>");//ouverture de tag xml
        for(Tache tache: tacheList.getTacheList()){
            ajouter(tache);
        }
        pw.println("</taches>");//fermeture de la tag
        if(openedHere){
            pw.close();
            pw=null;
        }
    }

    @Override
    public void ajouter(Tache tache) {
        boolean openedHere = false;
        if(pw==null){
            try {
                pw = new PrintWriter(new FileWriter ("./src/persistance/Task.xml"));
                openedHere=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pw.println("<tache>");
        pw.println("<id>"+tache.getId()+"</id>");
        pw.println("<titre>"+tache.getTitre()+"</titre>");
        pw.println("<description>"+tache.getDescription()+"</description>");
        pw.println("<beforeTime>"+tache.getBeforeTime()+"</beforeTime>");
        pw.println("<status>"+tache.getStatus()+"</status>");
        pw.println("</tache>");
        if(openedHere){
            pw.close();
            pw=null;
        }//ajouter une tache
    }

    @Override
    public void supprimer(long id) {

    }

    @Override
    public void modifier(Tache tache, long id) {

    }
    private class SAXHandler extends DefaultHandler{
        TacheList tacheList;
        Tache tache;
        String content;

        public SAXHandler(TacheList tacheList) {
            this.tacheList = tacheList;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName){
                case "tache":
                    tache = new Tache();
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName){
                case "id":
                    tache.setId(Long.parseLong(content));
                    break;
                case "titre":
                    tache.setTitre(content);
                    break;
                case "description":
                    tache.setDescription(new StringBuffer(content));
                    break;
                case "beforeTime":
                    tache.setBeforeTime(LocalDateTime.parse(content));
                    break;
                case "status":
                    tache.setStatus(Integer.parseInt(content));
                    break;
                case "tache":
                    tacheList.ajouterTache(tache);
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            content=String.copyValueOf(ch,start,length);
        }
    }
}
