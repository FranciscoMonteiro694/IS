import javax.xml.bind.annotation.*;
import java.util.ArrayList;



@XmlRootElement(name="class",namespace="http://www.dei.uc.pt/EAI") // Para dar o nome "class" ao root element
@XmlAccessorType(XmlAccessType.FIELD)
public class Lista_carros {

    @XmlElement(name="Car") // Para dar o nome "Car" aos filhos do root element
    private ArrayList<Carro> list_cars = null;

    public ArrayList<Carro> getList_cars() {
        return list_cars;
    }

    public void setList_cars(ArrayList<Carro> list_cars) {
        this.list_cars = list_cars;
    }

}