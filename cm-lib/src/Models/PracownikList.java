package Models;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@XmlRootElement(name="ipracownikList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PracownikList implements Serializable, Remote {
    @XmlElements(@XmlElement(name="ipracownik"))
    List<Pracownik> list = new ArrayList<>();
    public PracownikList() {
    }
    public HashMap<String, Pracownik> toHashMap() {
        HashMap<String, Pracownik> map = new HashMap<>();
        for(Pracownik elem : list) {
            map.putIfAbsent(elem.getPesel(),elem);
        }
        return map;
    }
    public PracownikList(List<Pracownik> list) {
        this.list = list;
    }

    public List<Pracownik> getList() {
        return list;
    }

    public void setList(List<Pracownik> list) {
        this.list = list;
    }
}
