
package Controlador;

//import Config.GenerarSerie;
import Modelo.*;
import Config.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Controlador1 extends HttpServlet {
     Empleado em= new Empleado();
     EmpleadoDAO edao=new EmpleadoDAO();
     Cliente c= new Cliente();
     ClienteDAO cdao=new ClienteDAO();
     Producto p= new Producto();
     ProductoDAO pdao=new ProductoDAO();
     Venta v= new Venta();
     VentaDAO vdao=new VentaDAO();
     List<Venta> lista= new ArrayList<>();
     String descripcion, numeroserie;
     double precio, subtotal, totalPagar;
     int ide, idc, idp, item,cod,cant;
     
    
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String menu=request.getParameter("menu");
            String accion=request.getParameter("accion");
            if(menu.equals("Principal")){
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
            if(menu.equals("Empleado")){
                switch(accion){
                    case "Listar":
                        List lista = edao.listar();
                        request.setAttribute("empleados", lista);
                        break;
                    case "Agregar":
                        String dni=request.getParameter("txtDni");
                        String nom=request.getParameter("txtNombres");
                        String tel=request.getParameter("txtTel");
                        String est=request.getParameter("txtEstado");
                        String user=request.getParameter("txtUser");
                        em.setDni(dni);
                        em.setNom(nom);
                        em.setTel(tel);
                        em.setEstado(est);
                        em.setUser(user);
                        edao.agregar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        ide=Integer.parseInt(request.getParameter("id"));
                        Empleado e=edao.listarId(ide);
                        request.setAttribute("empleado", e);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1=request.getParameter("txtDni");
                        String nom1=request.getParameter("txtNombres");
                        String tel1=request.getParameter("txtTel");
                        String est1=request.getParameter("txtEstado");
                        String user1=request.getParameter("txtUser");
                        em.setDni(dni1);
                        em.setNom(nom1);
                        em.setTel(tel1);
                        em.setEstado(est1);
                        em.setUser(user1);
                        em.setId(ide);
                        edao.actualizar(em);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Delete":
                        ide=Integer.parseInt(request.getParameter("id"));
                        edao.delete(ide);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
            }
            
            if(menu.equals("Clientes")){
                switch(accion){
                    case "Listar":
                        List lista1=cdao.listar();
                        request.setAttribute("clientes", lista1);
                    break;
                    case "Agregar":
                        String dni=request.getParameter("txtDni");
                        String nom=request.getParameter("txtNombres");
                        String dir=request.getParameter("txtDir");
                        String est=request.getParameter("txtEstado");
                        c.setDni(dni);
                        c.setNom(nom);
                        c.setDir(dir);
                        c.setEs(est);
                        cdao.agregar(c);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                    case "Editar":
                        idc=Integer.parseInt(request.getParameter("id"));
                        Cliente cc = cdao.listarId(idc);
                        request.setAttribute("cliente", cc);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1=request.getParameter("txtDni");
                        String nom1=request.getParameter("txtNombres");
                        String dir1=request.getParameter("txtDir");
                        String est1=request.getParameter("txtEstado");
                        c.setDni(dni1);
                        c.setNom(nom1);
                        c.setDir(dir1);
                        c.setEs(est1);
                        c.setId(idc);
                        cdao.actualizar(c);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                    case "Delete":
                        idc=Integer.parseInt(request.getParameter("id"));
                        cdao.delete(idc);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                    default:
                        throw new AssertionError();
                    }
                    request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            }
            
            if(menu.equals("Producto")){
                 switch(accion){
                    case "Listar":
                        List lista2=pdao.listar();
                        request.setAttribute("productos", lista2);
                        break;
                    case "Agregar":
                        String nom =request.getParameter("txtNombres");
                        double pre =Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock = Integer.parseInt(request.getParameter("txtStock"));
                        String estado =request.getParameter("txtEstado");
                        p.setNombre(nom);
                        p.setPrecio(pre);
                        p.setStock(stock);
                        p.setEstado(estado);
                        
                        pdao.agregar(p);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;

                    case "Editar":
                        idp=Integer.parseInt(request.getParameter("id"));
                        Producto po =pdao.listarId(idp);
                        request.setAttribute("productos", po);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String nom1 =request.getParameter("txtNombres");
                        double pre1 =Double.parseDouble(request.getParameter("txtPrecio"));
                        int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                        String estado1 =request.getParameter("txtEstado");
                        p.setNombre(nom1);
                        p.setPrecio(pre1);
                        p.setStock(stock1);
                        p.setEstado(estado1);
                        p.setId(idp);
                        pdao.actualizar(p);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Delete":
                        idp=Integer.parseInt(request.getParameter("id"));
                        pdao.delete(idp);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }
            if(menu.equals("NuevaVenta")){
                switch(accion){
                    case "BuscarCliente":
                        String dni=request.getParameter("codigocliente");
                        c.setDni(dni);
                        c = cdao.buscar(dni);
                        request.setAttribute("c", c);//envia datos del cliente al formulario
                        break;
                    case "BuscarProducto":
                        int id=Integer.parseInt(request.getParameter("codigoproducto"));
                        p=pdao.listarId(id);
                        request.setAttribute("c", c);
                        request.setAttribute("producto", p);
                        request.setAttribute("lista", lista);  
                        request.setAttribute("totalpagar", totalPagar);
                        break;
                    case "Agregar":
                        request.setAttribute("c", c);
                        totalPagar=0.0;
                        item+=1;
                        cod=p.getId();
                        descripcion=request.getParameter("nomproducto");
                        precio=Double.parseDouble(request.getParameter("precio"));
                        cant=Integer.parseInt(request.getParameter("cant"));
                        subtotal = precio * cant;
                        v = new Venta();
                        v.setItem(item);
                        v.setIdproducto(cod);
                        v.setDescripcionP(descripcion);
                        v.setPrecio(precio);
                        v.setCantidad(cant);
                        v.setSubtotal(subtotal);
                        lista.add(v);
                        for (int i = 0; i < lista.size(); i++) {
                            totalPagar = totalPagar + lista.get(i).getSubtotal();
                        }
                        request.setAttribute("totalPagar", totalPagar);
                        request.setAttribute("lista", lista);
                        break;
                    case "GenerarVenta":
                        //Actualizar Stock
                        for (int i = 0; i < lista.size(); i++) {
                            Producto pr = new Producto();
                            int cantidad = lista.get(i).getCantidad();
                            int idproducto = lista.get(i).getIdproducto();
                            ProductoDAO aO = new ProductoDAO();
                            pr = aO.buscar(idproducto);
                            int  sac = pr.getStock() - cantidad;
                            aO.actualizarStock(idproducto, sac);
                                    
                        }
                        //Guardar venta
                        v.setIdcliente(c.getId());
                        v.setIdempleado(2);
                        v.setNumserie(numeroserie);
                        v.setFecha("2022-06-14");
                        v.setMonto(totalPagar);
                        v.setEstado("1");
                        vdao.guardarVentas(v);
                        //Guardar detalle venta
                        int idv=Integer.parseInt(vdao.IdVentas());
                        for (int i = 0; i < lista.size(); i++) {
                            v= new Venta();
                            v.setId(idv);
                            v.setIdproducto(lista.get(i).getIdproducto());
                            v.setCantidad(lista.get(i).getCantidad());
                            v.setPrecio(lista.get(i).getPrecio());
                            vdao.guardarDetallesventas(v);
                        }
                        break;
                    default:
                        v= new Venta();
                        lista= new ArrayList<>();
                        item=0;
                        totalPagar=0.0;
                        
                        numeroserie = vdao.GenerarSerie();
                        if(numeroserie == null){
                            numeroserie="00000001";
                            request.setAttribute("nserie", numeroserie);
                        }else{
                            int incrementar =Integer.parseInt(numeroserie);
                            GenerarSerie gs = new GenerarSerie();
                            numeroserie=gs.NumeroSerie(incrementar);
                            request.setAttribute("nserie",numeroserie);
                        }                        
                        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                }
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
       }
    
          
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
