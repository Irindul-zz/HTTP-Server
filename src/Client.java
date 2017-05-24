import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Shauny on 24-May-17.
 */
public class Client
{
    private String adr;
    private int port;
    private Socket socket;

        //String adr = "192.168.0.1";
        //int port = 80;


    public Client() {

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.readAdr();
        client.readPort();

        try{
            client.socket = new Socket(InetAddress.getByName(client.adr), client.port);
        }
        catch (IOException e){
            System.err.println("Impossible de se connecter à l'adresse " + client.adr + ":" + client.port + " : " + e.getMessage());
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(client.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("GET / HTTP/1.1 \r\n\r\n");
        pw.flush();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String t;

        try {
            while ((t = br.readLine()) != null) System.out.println(t);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void readAdr(){
        System.out.println("Enter the Host's IP Address");
        String adr;
        Scanner S=new Scanner(System.in);
        adr=S.nextLine();
        this.adr=adr;
    }

    public void readPort(){
        System.out.println("Enter the Host's Port");
        int port;
        Scanner S=new Scanner(System.in);
        port=S.nextInt();
        this.port=port;
    }


}
