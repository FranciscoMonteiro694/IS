import javax.xml.bind.annotation.*;
import java.util.ArrayList;



@XmlRootElement(name="ListaDonos") // Para dar o nome "class" ao root element
@XmlAccessorType(XmlAccessType.FIELD)
public class Lista_donos {

    @XmlElement(name="owner") // Para dar o nome "owner" aos filhos do root element
    private ArrayList<Dono> list_owners = null;

    public ArrayList<Dono> getList_Owners() {
        return list_owners;
    }

    public void setList_Owners(ArrayList<Dono> list_owners) {
        this.list_owners = list_owners;
    }

}