import Model.Dyrektor;
import Model.Handlowiec;
import Model.IPracownik;
import Model.IPracownikList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadedHandler implements Runnable {
    private Socket s;
    public ThreadedHandler(Socket s) {
        this.s = s;
    }
    @Override
    public void run() {
        try {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();
            try (Scanner in = new Scanner(inStream, "UTF-8")) {
                PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
                boolean done = false;
                out.println(marshall());
            }
        }
        catch (IOException | JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
    public String marshall() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(IPracownikList.class,Dyrektor.class,IPracownik.class, Handlowiec.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        jaxbMarshaller.marshal(getTest(), writer);
        return writer.toString();
    }
    public IPracownikList getTest() {
        Dyrektor pracownik = new Dyrektor();
        pracownik.setPesel("1111111");
        pracownik.setCardNumber("123-456-892");
        pracownik.setFirstName("Karol");
        pracownik.setSalary(BigDecimal.valueOf(1234.4));
        Handlowiec pracownik2 = new Handlowiec();
        pracownik2.setPesel("1122211");
        pracownik2.setFirstName("Marcin");
        pracownik2.setSalary(BigDecimal.valueOf(1234.4));
        List<IPracownik> list = new ArrayList<IPracownik>();
        list.add(pracownik);
        list.add(pracownik2);
        IPracownikList list2 = new IPracownikList();
        list2.setList(list);
        return list2;
    }
}
