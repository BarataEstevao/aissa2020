import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class Servidor {
    //porto de conexão no servidor
    private static final int PORTO = 8050;



    public static void main(String[] args) {
        try {
            ResourceConfig config = new ResourceConfig();
            //regista recurso servidor
            config.register(new RecursosServidor());
            //regista recurso quarto
            config.register(new RecursosQuartos());
            //Porto do servidor
            //constroi a base do URI -- basicamente IP e
            URI uriBase = UriBuilder.fromUri("http://0.0.0.0/").port(PORTO).build();

            //informa a base do URI do servidor e os
            //recursos a serem servidos registados na variável config
            //desde este ponto o URI base e os recursos,
            //são disponibilizados para soicitação
            //já torna-se possível usar
            //http://IPdoServidor:PORTO/caminhoDorecurso no cliente para solicitar o
            //recurso pretendido
            JdkHttpServerFactory.createHttpServer(uriBase, config);
            //consola do computador
            //obtem ip do servidor para impressão na
            String ip = InetAddress.getLocalHost().getHostAddress();

            Descoberta descoberta = new Descoberta();
            descoberta.publicar("aissaussene",uriBase.toString());

           // System.out.printf("Servidor REST aguarda pedidos em: http://%s:%d\n", ip, PORTO);
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

}