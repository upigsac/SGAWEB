
package Beans;

import DAO.personaDAO;


import java.io.IOException;


import javax.faces.bean.ManagedBean;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@ManagedBean(name = "svImagenAlumno")
public class SVerImagenAlumno extends HttpServlet {


	private static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/jpeg");
       
        String codigo = request.getParameter("alumno").toUpperCase();

        ServletOutputStream out2 = null;

        byte[] imag = new personaDAO().obtenImagenPersonal(codigo);
        if (imag != null) {

            out2 = response.getOutputStream();
            out2.write(imag);
            out2.flush();
            out2.close();
            /*
            InputStream in = new ByteArrayInputStream(imag);
			BufferedImage bImageFromConvert = ImageIO.read(in);

			ImageIO.write(bImageFromConvert, "jpg", new File("c:/IMAGEN/"+codigo+".jpg"));
		*/
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
        util.consolaCliente( "post");
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
