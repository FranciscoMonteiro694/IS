package user;


import assignment.CarandOwners;
import assignment.UserGrpc.UserImplBase;
import io.grpc.stub.StreamObserver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import


public class UserService extends UserImplBase {
    @Override
    public void requestXML(CarandOwners.CapsulaXML request, StreamObserver<CarandOwners.CapsulaXML> responseObserver) {
        // Recebe o XML em string
        String pedido = request.getStringXML();
        System.out.println(pedido);
        File ficheiroAuxiliar = new File("ficheiroAuxiliar.xml");
        try {
            FileWriter fw = new FileWriter(ficheiroAuxiliar);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write(pedido);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Passar de XML para objetos
        try {
            XMLtoObject(ficheiroAuxiliar);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void request(CarandOwners.OwnerList request, StreamObserver<CarandOwners.CarList> responseObserver)  {
        System.out.println("Dentro do request");
        request.getDonosList();
        // Debug
//        for (CarandOwners.Owner dono: request.getDonosList()){
//            System.out.println("Owner ID: "+dono.getOwnerid());
//            System.out.println("Name: "+dono.getName());
//            System.out.println("Telephone: "+dono.getTelephone());
//            System.out.println("Address: "+dono.getAddress());
//        }
        // Resposta
        CarandOwners.CarList.Builder response = CarandOwners.CarList.newBuilder();
        // Está na worker
//        responseObserver.onNext(response.build());
//        responseObserver.onCompleted();
        try {
            worker(request,responseObserver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void worker(CarandOwners.OwnerList request,StreamObserver<CarandOwners.CarList> responseObserver) throws IOException {
        File ficheiroCarros = new File("/Users/Franciscomonteiro/Desktop/UC/Mestrado/4º ano/IS/2ndAttempt/src/main/java/user/Carros.txt");
        BufferedReader br = new BufferedReader(new FileReader(ficheiroCarros));
        String st;

        // Cria a lista de carros
        CarandOwners.CarList.Builder carros = CarandOwners.CarList.newBuilder();
        while ((st = br.readLine()) != null) {// Para cada carro
            String[] tokens = st.split("\t");
            //Percorrer a lista de donos
            for (CarandOwners.Owner dono: request.getDonosList()){
                // Se o id do dono do carro for igual ao id da pessoa
                if(dono.getOwnerid()==Integer.parseInt(tokens[7])){
                    // uniqueID  brand    model    engineSize   power   comsuption  plate   ownerID
                    CarandOwners.Car.Builder carro = CarandOwners.Car.newBuilder();
                    carro.setId(Integer.parseInt(tokens[0]));
                    carro.setBrand(tokens[1]);
                    carro.setModel(tokens[2]);
                    carro.setEngineSize(tokens[3]);
                    carro.setPower(tokens[4]);
                    carro.setComsuption(tokens[5]);
                    carro.setPlate(tokens[6]);
                    carro.setOwnerid(Integer.parseInt(tokens[7]));
                    carros.addCarros(carro);
                }
            }
        }
        //

        responseObserver.onNext(carros.build());
        responseObserver.onCompleted();

    }
    @Override
    public void pedido(CarandOwners.Owner request, StreamObserver<CarandOwners.CarList> responseObserver) {
        System.out.println("Dentro do pedido");
        request.getName();
        System.out.println("Nome"+request.getName());
        CarandOwners.CarList.Builder response = CarandOwners.CarList.newBuilder();

        // Tenho de ver isto melhor
        //response.setCarros(0, CarandOwners.Car.newBuilder().setId(23).setBrand("Mazda").build());
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();

    }

    public static void XMLtoObject(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Lista_carros.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Lista_carros L_C_1 = (Lista_carros) jaxbUnmarshaller.unmarshal(file);
        ArrayList<Carro> lista;
        lista=L_C_1.getList_cars();
        System.out.println("Conteudo:");
        for(Carro c : lista){
            System.out.println(c.getModel());
        }
    }
}
