/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyController;

/**
 *
 * @author rinzin
 */
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component
public class ControllerWithoutAnnotation implements Controller {

   public ModelAndView callHome() {
        return new ModelAndView("admi");
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter responseWriter = response.getWriter();
        responseWriter.print("test");
        String uri = request.getRequestURI();
        System.out.println("-------------------without annotation------------------------");
        System.out.println("getRequestURI : "+uri);
         System.out.println("------------------------------------------");
         
         if(uri.endsWith("admi.com"))
//            return "forward:/ReportServlet";
           return new ModelAndView("admi","msg","Welcome");
         else
           return new ModelAndView("index","msg","Error");
    }
}

