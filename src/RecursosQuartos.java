import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/recursoquarto")
public class RecursosQuartos {

    List<Quarto> hotesQuarto = new ArrayList<>();

    //mapeia um hotel e seus respectivos quartos
    private final Map<String, List<Quarto>> hoteis;
    RecursosQuartos() {
        hoteis = new HashMap<>();
    }

    @GET
    public String get() {

        return "Recurso Quarto";
    }
    //retorna todos os quartos de todos hoteis

    @GET
    @Path("/todosquartohoteis")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Quarto> todos_Quartos() {

        return hotesQuarto;
    }
    //retorna todos os quartos vazios de todos os hoteis
    //descriminado por hotel
    @GET
    @Path("/quartosvazioshoteis")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<Quarto>> quartos_vazios() {

        return hoteis;
    }
    //retorna os quartos de um determinado hotel
    @GET
    @Path("/quartoshotel/{nome_do_hotel}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Quarto> quartos_Do_Hotel( @PathParam("nome_do_hotel") String nome_do_hotel) {
        return hoteis.get(nome_do_hotel);
    }
    //retorna os quartos vazios de um determinado hotel
    @GET
    @Path("/quartosvazioshotel/{nome_do_hotel}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Quarto> quartos_Vazios_Do_Hotel( @PathParam("nome_do_hotel") String nome_do_hotel) {
        List<Quarto> quartoList = new ArrayList<>();
        for(Quarto quarto: hoteis.get(nome_do_hotel)){
            if(!quarto.isOcupado()){
                quartoList.add(quarto);
            }
        }
        return quartoList;
    }

    //insere um novo quarto num determinado hotel
    @POST
    @Path("/novoquartohotel/{nome_do_hotel}/{ocupado}")
    public String novo_Quarto( @PathParam("nome_do_hotel") String nome_do_hotel, @PathParam("ocupado") String ocupado) {

        Quarto quarto1 = new Quarto();

        if(ocupado.equals("1")){
            quarto1.setOcupado(true);
        }else{
            quarto1.setOcupado(false);
        }
        if(hoteis.get(nome_do_hotel) != null){
            int nr_do_quarto = hoteis.get(nome_do_hotel).size()+1;
            quarto1.setNr_do_quarto(nr_do_quarto);
            hoteis.get(nome_do_hotel).add(quarto1);

        }else{

            List<Quarto> quartoList = new ArrayList<>();
            quarto1.setNr_do_quarto(1);

            quartoList.add(quarto1);

            hoteis.put(nome_do_hotel, quartoList);
        }
        hotesQuarto.add(quarto1);

        return "Adicionado Novo quarto no: " + nome_do_hotel;
    }
}