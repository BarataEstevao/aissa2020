import javax.sound.sampled.Port;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Descoberta {

    private final Map<String, String> urlMap;
    private final int PORT = 8080;

    public Descoberta(){
        urlMap = new HashMap<>();
    }

    //Procura um grupo e junta-se ao grupo.
    public String pesquisar(String nomeServidor, int timeout) {

        StringBuilder msg = new StringBuilder();
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            InetAddress group = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(group);
            System.out.println("Multicast  Group Joined");

            byte[] buffer = nomeServidor.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 8050);

            socket.send(packet);
            socket.receive(packet);
            String received = new String(packet.getData());
            msg.append(received.trim()).append(" ");
            Thread.sleep(timeout);

            socket.leaveGroup(group);
        } catch (IOException ex) {
            System.out.println("Error Client: "+ ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg.toString();
    }


    public void publicar(String nomeServidor, String URL){
        urlMap.put(nomeServidor, URL);

        DatagramSocket serverSocket;
        try {
            serverSocket = new DatagramSocket();

            System.out.println("Servidor REST aguarda pedidos");
            while (true) {
                byte[] buffer = new byte[256];

                InetAddress group = InetAddress.getByName("230.0.0.0");
                DatagramPacket packetRecv = new DatagramPacket(buffer, buffer.length, group, PORT);

                if ((nomeServidor.equals("") && URL.equals(""))) {
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    serverSocket.receive(packetRecv);
                    nomeServidor = new String(packetRecv.getData()).trim();

                    if (!urlMap.get(nomeServidor).isEmpty()) {
                        buffer = urlMap.get(nomeServidor).getBytes();
                    } else {
                        buffer = "".getBytes();
                    }
                }
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
                serverSocket.send(packet);
                Thread.sleep(1000);
            }
            // Handle exception
        } catch (IOException ex) {
            // Handle exception
            System.out.println("Error: " + ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
