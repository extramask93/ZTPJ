package Model;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name="ipracownikList")
@XmlAccessorType(XmlAccessType.FIELD)
public class IPracownikList {
    @XmlElements(@XmlElement(name="ipracownik"))
    List<IPracownik> list = null;
    public IPracownikList() {

    }
    public IPracownikList(List<IPracownik> list) {
        this.list = list;
    }

    public List<IPracownik> getList() {
        return list;
    }

    public void setList(List<IPracownik> list) {
        this.list = list;
    }
}
