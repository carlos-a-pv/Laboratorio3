package laboratorio3.controller;

import laboratorio3.services.IModelFactoryService;
import tcp.EchoTCPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;

public class ModelFactoryController implements IModelFactoryService {

    public static final String SERVER = "2.tcp.ngrok.io";
    public static final int PORT = 11386;
    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;
    private Socket socket;

    public void startConection() throws IOException {
        socket = new Socket(SERVER, PORT);
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    @Override
    public boolean iniciarSesion(String user, String password) {
        String response;
        toNetwork.println(String.format("iniciarsesion,%s,%s", user, password));
        try {
            response = fromNetwork.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.equals("true");
    }

    @Override
    public boolean reservarLibro(String idLibro) {
        String response;
        toNetwork.println(String.format("reservarlibro,%s", idLibro));
        try{
            response = fromNetwork.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.equals("true");
    }

    @Override
    public String cargarLibros() {
        String response;
        toNetwork.println("cargarlibros");
        try{
            String respuesta = fromNetwork.readLine();
            if(respuesta.equals("null")){
                throw new RuntimeException("Error al cargar los libros");
            }
            response = fromNetwork.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public String buscarLibro(String filtro) {
        String response;
        toNetwork.println(String.format("buscarlibros,%s", filtro));
        try{
            response = fromNetwork.readLine();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public boolean cambiarPassword(String password) {
        String response;
        toNetwork.println(String.format("cambiarpassword,%s",password));
        try{
            response = fromNetwork.readLine();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return response.equals("true");
    }
}
