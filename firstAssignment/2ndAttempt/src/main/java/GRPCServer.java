import io.grpc.Server;
import io.grpc.ServerBuilder;
import user.UserService;

import java.io.IOException;

public class GRPCServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9092).addService(new UserService()).build();
        server.start();
        System.out.println("Servidor iniciado na porta " + server.getPort());
        server.awaitTermination();
    }
}
