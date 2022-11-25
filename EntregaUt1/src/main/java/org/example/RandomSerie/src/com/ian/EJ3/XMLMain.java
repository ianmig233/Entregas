package org.example.RandomSerie.src.com.ian.EJ3;

import org.example.RandomSerie;


import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;



public class XMLMain {
    public static void main(String args[]) {

        try(RandomAccessFile raf = new RandomAccessFile("registro.dat", "r")) {
            ArrayList<Series> ArraySeries = new ArrayList<>();

            for (int i = 1;i<=100;i++){

                raf.seek(((i-1) * 50));
                int cod = raf.readInt();
                String nombre = raf.readUTF();
                int temporadas = raf.readInt();

                if (cod != 0) {
                    Series serie1 = new Series(cod, nombre, temporadas);
                    ArraySeries.add(serie1);
                }
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document documento = implementation.createDocument(null, "xml",null);
            documento.setXmlVersion("1.0");


            //Elemento raiz
            Document doc = builder.newDocument();
            Element SeriesElement = doc.createElement("Series");
            doc.appendChild(SeriesElement);

            for (Series x: ArraySeries) {


                //Primer elemento
                Element cod = doc.createElement("Cod");
                cod.setTextContent(String.valueOf(x.getCod()));
                SeriesElement.appendChild(cod);

            /*Se agrega un atributo al nodo elemento y su valor
            Attr attr = doc.createAttribute("cod");
            attr.setValue("valor del atributo");
            cod.setAttributeNode(attr);*/

                //Sgundo elemento
                Element nombre = doc.createElement("Nombre");
                nombre.setTextContent("Contenido del Nombre");
                SeriesElement.appendChild(nombre);

                //Tercer elemento
                Element temporada = doc.createElement("Temporadas");
                temporada.setTextContent(String.valueOf(x.getTemporadas()));
                SeriesElement.appendChild(temporada);
            }

            //Se escribe el contenido del XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult resultado = new StreamResult(new File("series.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, resultado);

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}