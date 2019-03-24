package XMLUtilities;

import javax.xml.bind.JAXBException;

public interface IMarshaller<T> {
    T unmarshall(String source) throws JAXBException;
    String marshall(T source) throws JAXBException;
}
