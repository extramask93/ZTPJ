package company.Models;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@XmlRootElement(name="ipracownikList")
@XmlAccessorType(XmlAccessType.FIELD)
public class IPracownikList implements Serializable, Remote {
    @XmlElements(@XmlElement(name="ipracownik"))
    List<IPracownik> list = new ArrayList<>();
    public IPracownikList() {
    }
    public HashMap<String, IPracownik> toHashMap() {
        HashMap<String, IPracownik> map = new HashMap<>();
        for(IPracownik elem : list) {
            map.putIfAbsent(elem.getPesel(),elem);
        }
        return map;
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
