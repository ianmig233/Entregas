

package org.example.RandomSerie.src.com.ian.EJ4;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class JAXBMain {
    public static void main(String[] args) {

        Series a1 = new Series(1,"Paco",3);
        Series a2 = new Series(1,"Feo",3);
        Series a3 = new Series(1,"Juan",3);
        Seriess serie = new Seriess();
        serie.addSeries(a1);
        serie.addSeries(a2);
        serie.addSeries(a3);

        try{
            JAXBContext contexto = JAXBContext.newInstance(serie.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(serie, new File("seriesJAXB.xml"));
        }catch (JAXException e){
            throw new RuntimeException(e);

        }

        Series otraserie;
        try{
            JAXBcontext contexto2 = JAXBContext.newInstance(Seriess.class);
            Unmarshaller unmarshaller = contexto2.createUnmarshaller();
            otraserie = (Serie) unmarshaller.unmarshal(new File("serieJAXB.xml"));
            otraserie.muestraSeries();

        }catch (JAXBException e){
            throw new RuntimeException(e);

        }
    }
}


































