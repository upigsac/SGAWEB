package Beans;


import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.utilDAO;

@ManagedBean(name = "Sarchivo")
public class Sarchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Sarchivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametroOpcion=request.getParameter("opcion");
		String parametroCodigo=request.getParameter("codigo");
		System.out.println(parametroOpcion);
		System.out.println(parametroCodigo);
		
		
		 ServletOutputStream out2 = null;

	        byte[] imag = new utilDAO().obtenerArchivo(parametroOpcion, parametroCodigo);
	        if (imag != null) {

	            out2 = response.getOutputStream();
	            out2.write(imag);
	            out2.flush();
	            out2.close();
	        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
