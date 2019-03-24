package Model;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@XmlRootElement(name="ipracownikList")
@XmlAccessorType(XmlAccessType.FIELD)
public class IPracownikList {
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
