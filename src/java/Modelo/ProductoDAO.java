 package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Producto buscar(int id){
        Producto p = new Producto();
      String sql="select * from producto where IdProducto="+id; 
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error en listar: "+ e.getMessage());
        }
        return p;
    } 
    
    public int actualizarStock(int id, int stock){
        String sql="update producto set Stock=? where IdProducto=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en actualizar stock: "+ e.getMessage());
        }
        return r; 
    }    
    
           
       //******Operaciones CRUD*****
    public List listar(){
        String sql="select * from producto";
        List<Producto> lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto cl= new Producto();
                cl.setId(rs.getInt(1));
                cl.setNombre(rs.getString(2));
                cl.setPrecio(rs.getDouble(3));
                cl.setStock(rs.getInt(4));
                cl.setEstado(rs.getString(5));
                lista.add(cl);
            }
        } catch (SQLException e) {
            System.out.println("Error en listar: "+ e.getMessage());
        }
        return lista;
    }
   
    public int agregar(Producto pr){
        String sql="insert into producto(Nombres, Precio, Stock, Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, pr.getNombre());
            ps.setDouble(2, pr.getPrecio());
            ps.setInt(3, pr.getStock());
            ps.setString(4, pr.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en agregar: "+ e.getMessage());
        }
        return r;
    }
    
    public Producto listarId(int id){
        Producto pr=new Producto();
        String sql="select * from producto where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
             while(rs.next()){
               pr.setId(rs.getInt(1));
               pr.setNombre(rs.getString(2)); 
               pr.setPrecio(rs.getDouble(3));
               pr.setStock(rs.getInt(4));
               pr.setEstado(rs.getString(5));
           }
        } catch (SQLException e) {
            System.out.println("Error en listar id: "+ e.getMessage());
        }
        return pr;
    }
    
    public int actualizar(Producto pr){
        String sql="update producto set Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, pr.getNombre());
            ps.setDouble(2, pr.getPrecio());
            ps.setInt(3, pr.getStock());
            ps.setString(4, pr.getEstado());
            ps.setInt(5, pr.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en actualizar: "+ e.getMessage());
        }
        return r; 
    }    
   
    public void delete(int id){
        String sql="delete from producto where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en eliminar: "+ e.getMessage());
        }
    }
}
