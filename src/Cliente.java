import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

public class Cliente {
    public Cliente() {

    }

    // este método insere um novo quarto
    public void inserirQuarto(String hotel, Quarto quarto, String nomeServidor) {

        String urlservidor = pesquisarServidor(nomeServidor);
        if (urlservidor != null) {
            Response response = this.alvo(urlservidor).path(quarto.getNumero_do_Quarto()+"").request()
                    .post(Entity.entity(quarto, MediaType.APPLICATION_JSON));
            System.out.println("Resposta do servidor: " + response.getStatus());
        } else {
            System.out.print("O Servidor não existe\n");
        }
    }

    // este método cria um cliente REST
    private Client cliente() {
        ClientConfig config = new ClientConfig();
        return ClientBuilder.newClient(config);
    }

    // este método busca o sevidor alvo
    private WebTarget alvo(String urlservidor) {
        return cliente().target(urlservidor);
    }

    // este método pesquisa servidor
    private String pesquisarServidor(String nomeServidor) {
        return new Descoberta().pesquisar(nomeServidor, 4000);
    }
    public void busca_qaurtos_vazios(String urlservidor){
        Response response = this.alvo(urlservidor).path("quartosvazioshoteis").request().get();
    }
    public static void main(String[] args) {
        Quarto quarto = new Quarto();
        quarto.setOcupado(true);
        quarto.setNr_do_quarto(400);

        new Cliente().inserirQuarto("plaza",quarto, "aissaussene");
    }
}