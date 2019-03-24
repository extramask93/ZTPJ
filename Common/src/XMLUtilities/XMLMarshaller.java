package XMLUtilities;

import Model.Dyrektor;
import Model.Handlowiec;
import Model.IPracownik;
import Model.IPracownikList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class XMLMarshaller implements IMarshaller<IPracownikList> {
    @Override
    public IPracownikList unmarshall(String source) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(IPracownikList.class, Dyrektor.class,IPracownik.class, Handlowiec.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader rader = new StringReader(source);
        IPracownikList pracownicy = (IPracownikList) jaxbUnmarshaller.unmarshal(rader);
        return pracownicy;
    }

    @Override
    public String marshall(IPracownikList source) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(IPracownikList.class,Dyrektor.class,IPracownik.class, Handlowiec.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        jaxbMarshaller.marshal(source, writer);
        return writer.toString();
    }
}
