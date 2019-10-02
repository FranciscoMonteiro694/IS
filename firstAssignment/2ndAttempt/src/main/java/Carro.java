import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder={"unique_id", "brand", "model", "engineSize", "power", "consumption", "plate", "ownerIdentifier"})
public class Carro {
    private int unique_id;
    private String brand;
    private String model;
    private String engineSize;
    private String power;
    private String consumption;
    private String plate;
    private int ownerIdentifier;

    public Carro(){

    }
    @XmlElement
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @XmlElement
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @XmlElement
    public String getEngineSize() {
        return engineSize;
    }

    public void setEngine_Size(String engineSize) {
        this.engineSize = engineSize;
    }

    @XmlElement
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }


    @XmlElement
    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    @XmlElement
    public int getOwnerIdentifier() {
        return ownerIdentifier;
    }

    public void setOwnerIdentifier(int ownerIdentifier) {
        this.ownerIdentifier = ownerIdentifier;
    }

    @XmlAttribute
    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    @XmlAttribute
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
