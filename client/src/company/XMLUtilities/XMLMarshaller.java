package company.XMLUtilities;

import company.Models.Dyrektor;
import company.Models.Handlowiec;
import company.Models.IPracownik;
import company.Models.IPracownikList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

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
