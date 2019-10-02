import assignment.CarandOwners;
import assignment.UserGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import user.UserService;
import assignment.UserGrpc.UserBlockingStub;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GRPCClient {


    public static void main(String[] args) throws IOException, InterruptedException, JAXBException {
        // Cria o channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9092).usePlaintext().build();
        // Cria o Stub
        UserBlockingStub userStub = UserGrpc.newBlockingStub(channel);

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Escolher tipo de dados:");
        System.out.println("1. Protobuff");
        System.out.println("2. XML");
        int opcao = keyboard.nextInt();
        if (opcao == 1) {
            //System.out.println("Protobuff escolhido");
            // Chama a função que carrega a lista de owners e a envia para o servidor
            worker(channel,userStub);
        } else if (opcao == 2) {
            System.out.println("XML escolhido");
            workerXML(channel,userStub);

        } else {
            System.out.println("Cliente a terminar!");
            System.exit(0);
        }

    }
    public static void workerXML(ManagedChannel channel ,UserBlockingStub userStub) throws IOException, JAXBException {
        ArrayList<Dono> lista_donos= new ArrayList<Dono>();
        // Ler do ficheiro Owners.txt e carregar a lista
        File ficheiroOwners = new File("/Users/Franciscomonteiro/Desktop/UC/Mestrado/4º ano/IS/2ndAttempt/src/main/java/Owners.txt");
        BufferedReader br = new BufferedReader(new FileReader(ficheiroOwners));
        String st;
        // Carrega os donos para um ArrayList de donos
        while ((st = br.readLine()) != null) {
            //System.out.println(st);
            String[] tokens = st.split("\t");
            Dono d = new Dono();
            d.setUnique_id(Integer.parseInt(tokens[0]));
            d.setName(tokens[1]);
            d.setTelephone(tokens[2]);
            d.setAddress(tokens[3]);
            lista_donos.add(d);
        }
        // Cria o XML de Donos para enviar ao servidor
        JAXBContext contextObj = JAXBContext.newInstance(Lista_donos.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Lista_donos lista = new Lista_donos();
        lista.setList_Owners(lista_donos);
        marshallerObj.marshal(lista, new FileOutputStream("listaDonos.xml"));

        // Passar o XML para string
        Worker w = new Worker();
        String pedido = w.XMLtoString();

        // Pedir ao servidor
        CarandOwners.CapsulaXML.Builder capsula = CarandOwners.CapsulaXML.newBuilder();
        capsula.setStringXML(pedido);
        CarandOwners.CapsulaXML resposta = userStub.requestXML(capsula.build());

    }


    public static void worker(ManagedChannel channel ,UserBlockingStub userStub) throws IOException {
        File ficheiroOwners = new File("/Users/Franciscomonteiro/Desktop/UC/Mestrado/4º ano/IS/2ndAttempt/src/main/java/Owners.txt");
        BufferedReader br = new BufferedReader(new FileReader(ficheiroOwners));
        String st;

        // Cria a lista de donos
        CarandOwners.OwnerList.Builder donos = CarandOwners.OwnerList.newBuilder();
        while ((st = br.readLine()) != null) {
            System.out.println(st);
            // Mete os tokens separados por \t num array
            String[] tokens = st.split("\t");
            // Percorre o array dos tokens
            // ownerID  name    Telephone    address
            CarandOwners.Owner.Builder dono = CarandOwners.Owner.newBuilder();
            //System.out.println("Tokens: "+tokens[0]+" "+tokens[1]+" "+tokens[2]+" "+tokens[3]);
            dono.setOwnerid(Integer.parseInt(tokens[0]));
            dono.setName(tokens[1]);
            dono.setTelephone(tokens[2]);
            dono.setAddress(tokens[3]);
            donos.addDonos(dono);
        }

        // Request
        CarandOwners.CarList resposta = userStub.request(donos.build());
        // uniqueID  brand    model    engineSize   power   comsuption  plate   ownerID
        for (CarandOwners.Car carro: resposta.getCarrosList()){
            System.out.println("ID carro: "+carro.getId());
            System.out.println("Brand: "+carro.getBrand());
            System.out.println("Model: "+carro.getModel());
            System.out.println("Engine size: "+carro.getEngineSize());
            System.out.println("Power: "+carro.getPower());
            System.out.println("Comsuption: "+carro.getComsuption());
            System.out.println("Plate: "+carro.getPlate());
            System.out.println("Plate: "+carro.getPlate());
            System.out.println("Owner ID: "+carro.getOwnerid());

        }
    }

}
