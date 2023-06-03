package prueba;

import datos.impl.PropiedadDAOImpl;
import dominio.Propiedad;

public class PropiedadTest {
    public static void main(String[] args) {
        insertarPropiedad("Mario","sauces","nose","siiiiii",16.3);
        
    }
     private static void insertarPropiedad(String nombre,String direccion,String caracteristicas,String estado,Double precioalquiler){
        Propiedad obj=new Propiedad();
        PropiedadDAOImpl datos=new PropiedadDAOImpl();
        obj.setNombre(nombre);
        obj.setDireccion(direccion);
        obj.setCaracteristicas(caracteristicas);
        obj.setEstado(estado);
        obj.setPrecioalquiler(precioalquiler);
        boolean resp;
        resp=datos.insertar(obj);
        System.out.println("Insertado: "+resp);
     }
}