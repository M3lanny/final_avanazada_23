package Modelo;

import Config.Conexion;
import java.sql.*;
import java.util.*;


public class ClienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Cliente buscar(String dni){
        Cliente c=new Cliente();
        String sql="select * from cliente where Dni="+dni;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setDir(rs.getString(4));
                c.setEs(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error: "+ e.getMessage());
        }
        return c;
    }
    
    //******Operaciones CRUD*****
    public List listar(){
        String sql="select * from cliente";
        List<Cliente> lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente cl= new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEs(rs.getString(5));
                lista.add(cl);
            }
        } catch (SQLException e) {
            System.out.println("Error en listar: "+ e.getMessage());
        }
        return lista;
    }
   
    public int agregar(Cliente cli){
        String sql="insert into cliente(Dni,Nombres,Direccion,Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDir());
            ps.setString(4, cli.getEs());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en agregar: "+ e.getMessage());
        }
        return r;
    }
    
    public Cliente listarId(int id){
        Cliente cli=new Cliente();
        String sql="select * from cliente where IdCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
             while(rs.next()){
               cli.setDni(rs.getString(2));
               cli.setNom(rs.getString(3));
               cli.setDir(rs.getString(4));
               cli.setEs(rs.getString(5));
           }
        } catch (SQLException e) {
            System.out.println("Error en listar id: "+ e.getMessage());
        }
        return cli;
    }
    
    public int actualizar(Cliente cli){
        String sql="update cliente set Dni=?, Nombres=?, Direccion=?, Estado=? where IdCliente=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDir());
            ps.setString(4, cli.getEs());
            ps.setInt(5, cli.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en actualizar: "+ e.getMessage());
        }
        return r; 
    }    
   
    public void delete(int id){
        String sql="delete from cliente where IdCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en eliminar: "+ e.getMessage());
        }
    }
}
