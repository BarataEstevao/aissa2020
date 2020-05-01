import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/recursoservidor")
public class RecursosServidor {
    //apenas retorna o texo referido no enunciado
    @GET
    public String get() {
        return "Um pedido REST foi envido para mim";
    }
    //apenas retorna o texto referido no enunciado
    @GET
    @Path("/{id}")
    public String get(@PathParam("id") String id) {
        return "Foi enviado para mim o recurso " + id;
    }
}