import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quarto {

    private int nr_do_quarto;

    private boolean ocupado;


    public Quarto(String nome_do_hotel, int nr_do_quarto, boolean ocupado) {
        this.nr_do_quarto = nr_do_quarto;
        this.ocupado = ocupado;
        System.out.println("NELDO");
    }

    public Quarto() {

    }

    public int getNumero_do_Quarto() {
        return nr_do_quarto;
    }

    public void setNr_do_quarto(int nr_do_quarto) {
        this.nr_do_quarto = nr_do_quarto;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

}
