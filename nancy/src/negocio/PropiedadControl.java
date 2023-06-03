
package negocio;
import dominio.Propiedad;
import datos.PropiedadDAO;
import datos.impl.PropiedadDAOImpl;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class PropiedadControl {
    private final PropiedadDAO DATOS;
    private Propiedad obj;
    private DefaultTableModel modeloTabla;
    
    public PropiedadControl(){
        this.DATOS = (PropiedadDAO) new PropiedadDAOImpl();
        this.obj = new Propiedad();
    }
    
   public DefaultTableModel listar(String texto) {
        List<Propiedad> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        //Establecemos la columna del tableModel
        String[] titulos = {"ID", "NOMBRE","DIRECCION", "CARACTERISTICAS","ESTADO","PRECIO"};
        //Declaramos un vector que será el que agreguemos 
        //como registro al DefaultTableModel
        String[] registro = new String[2];
        //agrego los títulos al DefaultTableModel
        this.modeloTabla = new DefaultTableModel(null, titulos);
        //Recorrer toda mi lista y la pasare al DefaultTableModel
        for (Propiedad item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
   
   
}
