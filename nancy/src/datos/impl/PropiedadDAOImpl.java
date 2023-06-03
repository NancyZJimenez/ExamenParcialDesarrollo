
package datos.impl;
import dominio.Propiedad;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Conexion;
import java.util.ArrayList;


public class PropiedadDAOImpl {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PropiedadDAOImpl(){
        CON = Conexion.getInstancia();
    }
    
    public List<Propiedad> listar(String texto) {
        List<Propiedad> registros=new ArrayList();
        try {            
            ps = CON.conectar().prepareStatement("SELECT id,nombre,direccion,caracteristicas,estado,precioalquiler from propiedades where nombre like");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add(new Propiedad(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            ps=null;
            rs=null;
        }
        return registros;
    }

    public boolean insertar(Propiedad obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO propiedades (nombre, direccion, caracteristicas, estado, precioalquiler) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDireccion());
            ps.setString(3, obj.getCaracteristicas());
            ps.setString(4, obj.getEstado());
            ps.setDouble(5, obj.getPrecioalquiler());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }

        return resp;
    }

    public boolean actualizar(Propiedad obj) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement(
            "UPDATE categorias SET nombre=? WHERE id =  ?");
            ps.setString(1, obj.getNombre());
            ps.setInt(2, obj.getId());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
        
    }

    public boolean eliminar(int id) {
       resp=false;
        try {
            ps = CON.conectar().prepareStatement("DELETE FROM propiedades WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    
}
