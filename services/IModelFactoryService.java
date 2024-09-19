package laboratorio3.services;

public interface IModelFactoryService {

    public boolean iniciarSesion(String user, String password);

    public boolean reservarLibro(String idLibro);

    public String cargarLibros();

    public String buscarLibro(String filtro);

    public boolean cambiarPassword(String password);

}
