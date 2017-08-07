/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyController;

import Models.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 *
 * @author rinzin
 */


@Controller
public class ControllerWithAnnotation {
     
    
    @RequestMapping(value="/admi", method=RequestMethod.GET) 
    public String admin(HttpServletRequest request){
    String uri = request.getRequestURI();
    return "admi";  
            }
    
    @RequestMapping(value="/loghome", method=RequestMethod.GET)
    public String loghome(HttpServletRequest req)
    {
        String uri=req.getRequestURI();
        return "homy";
    }
    
        @RequestMapping(value="/myhome" ,method=RequestMethod.GET)
    public String home(HttpServletRequest req){
        String uri=req.getRequestURI();
    //      String aname,apassword;
    int flag=0;
        String aname=req.getParameter("name");
        String apassword=req.getParameter("password");

        int uid = 0;
        ServletContext context = req.getServletContext();
         System.out.println("------------------ context : "+context+" -----------"); 
         //if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            MemberDAO mdao=(MemberDAO)ctx.getBean("memberDAOImpl");  
            
              
        List<Members> members=null;        
        try
        {
            members=(List<Members>)mdao.listMembers();
        
        }catch(org.hibernate.HibernateException e)
        {
            System.out.println(e);
        }
        
        for(Members m : members)
        {
              uid=m.getId();
        if(aname.equals(m.getUsername()) & apassword.equals(m.getPassword()) )
        {
        flag=1;
       break;
        }
        }
        if(flag==1)
        {
            HttpSession session=req.getSession();
            session.setAttribute("aname", aname);
            session.setAttribute("uid", uid);
            System.out.println("the user id is in login page "+uid);
            return "homy";
        }
        else
        {
            System.out.println("invalid login ......");
            return "error";
        }   
    }
    
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(HttpServletRequest req){
        HttpSession session=req.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "admi";
    }
    
    
    @RequestMapping(value="/profile",method=RequestMethod.GET)
    public String profile(HttpServletRequest req){
        
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImpl");  
            Members e = new Members();
            HttpSession session=req.getSession();
            int mid=(int)session.getAttribute("uid");
            System.out.println("user id"+mid);
//            String mid=req.getParameter("id");
//            int mid;
//            int mid = Integer.parseInt(id);
            e=dao.getMember(mid);
            req.setAttribute("mem",e);
        }
            return "profile";
        
    }
@RequestMapping(value="/addingMem" ,method=RequestMethod.GET)
    public String AddMember(HttpServletRequest request, HttpServletResponse response)
     {
         System.out.println("this is username"+request.getParameter("user"));
         ServletContext context = request.getServletContext();
         ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImpl");  
            Members m = new Members();
            m.setAddress(request.getParameter("address"));
            m.setName(request.getParameter("user"));
            m.setDob(request.getParameter("dob"));
           
            System.out.println(request.getParameter("dob"));
            System.out.println(request.getParameter("dom"));
            m.setDom(request.getParameter("dom"));
            m.setEmail(request.getParameter("email"));
            m.setPassword(request.getParameter("pass"));
            m.setPhoneno(request.getParameter("phone"));
            m.setStatus("Active");
            m.setUsername(request.getParameter("username"));
            dao.createMember(m);
            List<Members> list = dao.listMembers();
            request.setAttribute("memList", list);
         return "viewMem";
     }
    
    @RequestMapping(value="/changePassword" ,method=RequestMethod.GET)
    public String ChangePassword(HttpServletRequest request, HttpServletResponse response)
     {
          return "changePassword";
     }
    
    @RequestMapping(value="/changePass" ,method=RequestMethod.GET)
    public String ChangePass(HttpServletRequest request, HttpServletResponse response)
     {
         String oldp,newp1,newp2;
         oldp=request.getParameter("oldp");
         newp1=request.getParameter("newp1");
         newp2=request.getParameter("newp2");
         if(!newp1.equals(newp2))
         {
             return "changePassword?msg='Password and Confirm Password Do Not Match'";
         }
         else
             return "changePassword";
     }
    
    
    @RequestMapping(value="/updateMem" ,method=RequestMethod.GET)
    public String updateMember(HttpServletRequest request, HttpServletResponse response)
    {
         ServletContext context = request.getServletContext();
ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
         MemberDAO mdao=(MemberDAO)ctx.getBean("memberDAOImpl");  
         Members m=new Members();
         int id=Integer.parseInt(request.getParameter("id"));
         Members mem=mdao.getMember(id);
         System.out.println("inside update mem controller....");
         System.out.println(request.getParameter("address"));
         System.out.println(request.getParameter("username"));
         m.setId(mem.getId());
         m.setDom(mem.getDom());
         m.setAddress(request.getParameter("address"));
         m.setUsername(request.getParameter("username"));
         m.setPassword(request.getParameter("pass"));
         m.setDob(request.getParameter("dob"));
         m.setEmail(request.getParameter("email"));
         m.setName(request.getParameter("name"));
         m.setPhoneno(request.getParameter("phone"));
         m.setStatus(request.getParameter("status"));
         mdao.update(m);
         List<Members> list = mdao.listMembers();
         request.setAttribute("memList", list);
         return "viewMem";
    
    }
   
    
    @RequestMapping(value="/viewUsers" ,method=RequestMethod.GET)
    public String viewUsers(HttpServletRequest req, HttpServletResponse res)
    {
        
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImpl");  
            Members e = new Members();
             System.out.println("List Of Employees  :");
            List<Members> list = dao.listMembers();
            req.setAttribute("memList", list);
            for (int i = 0; i < list.size(); i++) {
                Members e1 = list.get(i);
                 System.out.println(""+i+" : \t"+e1.getName()+""); 
               //   out.println("<br>"+i+" : \t"+e1.getName()+""); 
            }
        }
        return "viewMem"; 
    }
    
    @RequestMapping(value="/statusBtn" ,method=RequestMethod.GET)
    public String activate(HttpServletRequest req, HttpServletResponse res)
    {   
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImpl");  
            Members m1=new Members();
            String[] mems=req.getParameterValues("choose");
            
            for(String mid : mems)
            {
                System.out.println("member id chosen ==="+mid);
                int id=Integer.parseInt(mid);
                                System.out.println("member id integer ==="+id);

                m1=dao.getMember(id);
                if(!m1.getStatus().equals("Main Admin"))
                {
                    if(req.getParameter("activate")!=null)
                    {
                        m1.setStatus("Active");
                        dao.update(m1);   
                    }
                    if(req.getParameter("inactivate")!=null)
                    {
                        m1.setStatus("inactive");
                        dao.update(m1);
                    }
                }
            List<Members> list=dao.listMembers();
            req.setAttribute("memList", list);
        }
        }
         return "viewMem";
    }
    
    @RequestMapping(value="/delMember" ,method=RequestMethod.GET)
    public String DelMember(HttpServletRequest req, HttpServletResponse res)
    {
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImpl");  
            Members e = new Members();
            int id=Integer.parseInt(req.getParameter("id"));
            System.out.println(id);
            e=dao.getMember(id);
            dao.changeStatus(e);
            List<Members> list = dao.listMembers();
            req.setAttribute("memList", list);
        }  
     return "viewMem";   
    }

    
    
    @RequestMapping(value="/viewAmember" ,method=RequestMethod.GET)
    public String viewAmember(HttpServletRequest req, HttpServletResponse res)
    {
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImpl");  
            Members e = new Members();
            String mid=req.getParameter("id");
            int id=Integer.parseInt(mid);
            e=dao.getMember(id);
            req.setAttribute("mem",e);
        }
            return "viewAmember";
        
    }
    
     @RequestMapping(value="/memberDetail" ,method=RequestMethod.GET)
    public String memberDetail(HttpServletRequest request, HttpServletResponse response)
    {
        ServletContext context = request.getServletContext();
         System.out.println("------------------ context : "+context+" -----------"); 
         //if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            MemberDAO mdao=(MemberDAO)ctx.getBean("memberDAOImpl");  
   
        String mid=request.getParameter("id");
        int id=Integer.parseInt(mid);
        System.out.println("userId from viw page .."+id);
        Members mem=mdao.getMember(id);
        System.out.println("id from view Page is ..."+mid);
        System.out.println("After UserId location "+mem.getAddress());

        request.setAttribute("mid",mem);
        return "MemDetails";
    }
    

    
     @RequestMapping(value="/AddMem" ,method=RequestMethod.GET)
     public String ShowAddMember(HttpServletRequest req){
        
        String uri=req.getRequestURI();
    
        return "AddMember";
    }
     
    
    @RequestMapping(value="/cust_orders",method=RequestMethod.GET)
    public String cust_orders(HttpServletRequest req){
        ServletContext context=req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            OrdersDao dao=(OrdersDao)ctx.getBean("ordersDaoImpl");
            CustomerDao cdao=(CustomerDao)ctx.getBean("customerDaoImpl");
            long cust_id=0;
            List<Orders> ord=null;
            System.out.println("this is the selected customer" +Long.parseLong(req.getParameter("cid")));
           
            cust_id=Long.parseLong(req.getParameter("cid"));
            
            ord=dao.getCustorder(cust_id);
            
        }
        
        return "customer";
    }
    
    @RequestMapping(value="/orders" ,method=RequestMethod.GET)
    public String orders(HttpServletRequest req){
        
        ServletContext context= req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            OrdersDao dao=(OrdersDao)ctx.getBean("ordersDaoImpl");  
            Orders order = new Orders();
                   
            try{
                 List<Orders> orders=(List<Orders>)dao.listOrders();
                req.setAttribute("li", orders);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
            String uri=req.getRequestURI();
        
        }
                return "orders";

    }
    
    @RequestMapping(value="/new_order",method=RequestMethod.GET)
    public String new_order(HttpServletRequest req){
        ServletContext context= req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            OrdersDao dao=(OrdersDao)ctx.getBean("ordersDaoImpl");  
            Orders order = new Orders();
                   List<Orders> new_order=(List<Orders>)dao.newOrders();
                   req.setAttribute("li",new_order);
        }
        return "new_order";
        
    }
    
    @RequestMapping(value="/view_order",method=RequestMethod.GET)
    public String view_order(HttpServletRequest req){
        
        ServletContext context= req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            OrdersDao odao=(OrdersDao)ctx.getBean("ordersDaoImpl");
            Orders ord=new Orders();
            long oid=Long.parseLong(req.getParameter("oid"));
            ord=odao.getOrder(oid);
            try{
                req.setAttribute("li",ord);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
            
            
        }
        return "view_order";
    }    
    
    @RequestMapping(value="/order_items",method=RequestMethod.GET)
    public String order_items(HttpServletRequest req){
        long id=0;
        ServletContext context =req.getServletContext();
        if(context!=null){
            ApplicationContext ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            OrdersDao odao=(OrdersDao)ctx.getBean("ordersDaoImpl");
            Orders ord=new Orders();
            ProductDAO pdao=(ProductDAO)ctx.getBean("productDAOImpl");
            
            
            long o_id=0;
            o_id=Long.parseLong(req.getParameter("oid"));
            ord=odao.getOrder(o_id);
            String list=ord.getItemlist();
            list=list.replace("[", "").replace("]", "");
            List<String> li=Arrays.asList(list.split(","));
            Product prod=new Product();
                       
            List<Product> myprod=new ArrayList();
            for(String it:li){
                it=it.trim();
                id=Long.parseLong(it);
                prod=pdao.getProduct(id);
                myprod.add(prod);
            }
                req.setAttribute("prodlist",myprod);      
            }
                    
                     
          
        
      return "order_items";  
        
    }
    
    @RequestMapping(value="/edit_order", method=RequestMethod.GET)
    public String edit_order(HttpServletRequest req){
       
        //long id=0;
        ServletContext context =req.getServletContext();
        if(context!=null){
            ApplicationContext ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            OrdersDao odao=(OrdersDao)ctx.getBean("ordersDaoImpl");
            Orders ord=new Orders();
            long o_id=0;
            o_id=Long.parseLong(req.getParameter("oid"));
            ord=odao.getOrder(o_id);
            req.setAttribute("order",ord);
            
        }  
        return "edit_order";
    }
    
    @RequestMapping(value="/change_ord_status", method=RequestMethod.GET)
    public String change_ord_status(HttpServletRequest req){
       
        ServletContext context =req.getServletContext();
        if(context!=null){
            ApplicationContext ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            OrdersDao odao=(OrdersDao)ctx.getBean("ordersDaoImpl");
            Orders ord=new Orders();
            long o_id=0;
            o_id=Long.parseLong(req.getParameter("oid"));
            ord=odao.getOrder(o_id);
            String status=req.getParameter("status");
            ord.setStatus(status);
            String stat_date=req.getParameter("stat_date");
            ord.setStat_date(stat_date);
            odao.update(ord);
            List<Orders> orders=null;
            orders=odao.listOrders();
            
             try{
                req.setAttribute("li",orders);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
            
        }
        return "orders";
    }
     
    @RequestMapping(value = "/add_cat", method = RequestMethod.GET)
     public String add_cat(HttpServletRequest req) {
     
       
        String uri=req.getRequestURI();
      System.out.println("code to open add cat page");
      
        

     return "add_cat";
   }
     
       @RequestMapping(value="/EditCategory" ,method=RequestMethod.GET)
     public String editcategory(HttpServletRequest req){
    
         ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDao dao=(CategoryDao)ctx.getBean("categoryDaoImpl");  
            String sid=req.getParameter("id");
            Long id=Long.parseLong(sid);
            Category cat=dao.getCategory(id);
            req.setAttribute("li", cat);
        String uri=req.getRequestURI();
        }
        return "EditCategory";
    }
	
     
    
	
	
	
     
      @RequestMapping(value="/editcat" ,method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA)
     public String editcat(HttpServletRequest req, HttpServletResponse response){
         ServletContext context = req.getServletContext();
//         @RequestParam CommonsMultipartFile[] fileUpload
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDAO dao=(CategoryDAO)ctx.getBean("categoryDAOImpl");  
            String catname=req.getParameter("catname");
              System.out.println("the category name==="+catname);

            String status=req.getParameter("status");
             System.out.println("the status==="+status);

            String id=req.getParameter("cid");
             System.out.println("long type id ==="+id);

            String url=req.getParameter("img");
            System.out.println("url=="+url);
            
            long cid=Long.parseLong(id);
//            long cid=3;
            System.out.println("long type id ==="+cid);
            
            Category cat=dao.getCategory(cid);
            System.out.println("the category object==="+cat);
            cat.setCat_name(catname);
            cat.setStatus(status);
            cat.setImage(url);
            dao.update(cat);
            String uri=req.getRequestURI();
           
            // To save Image Uploaded in project folder
            try{
                boolean isMultipart = ServletFileUpload.isMultipartContent(req);
                response.setContentType("text/html");
                File file;
                String fileName;
                String filePath= "E:\\TRAINING\\My Project\\MySpringWeb\\web\\Uploads\\";  
         
          DiskFileItemFactory factory = new DiskFileItemFactory();
          ServletFileUpload upload =new ServletFileUpload(factory);
//        List fileItems;
          
          List fileItems = upload.parseRequest(req);
          Iterator i=fileItems.iterator();
          while (i.hasNext()) 
          {
          FileItem fi=(FileItem)i.next();
              System.out.println("======"+fi);
          if (!fi.isFormField())	
          {
              System.out.println("field name="+fi.getFieldName());
            // Get the uploaded file parameters
          if(fi.getFieldName().equals("product"))
          {
              String item=fi.getString();
              System.out.println("product="+item);
          }
            String fieldName = fi.getFieldName();
            fileName = fi.getName();
            String contentType = fi.getContentType();

            // Write the file
            if( fileName.lastIndexOf("\\")>=0){
               file=new File(filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
               
               System.out.println("1.");
            }
            else{
               file=new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
               System.out.println("2.");
            }
            fi.write(file);
          }}
        }catch(Exception ex)
        {
        ex.printStackTrace();
        }
            
            List<Category> clist=dao.getCategories();
            req.setAttribute("catList", clist);
        }
        return "ViewCategories";
    }


     
     @RequestMapping(value="add_category",method=RequestMethod.GET)
     public String add_category(HttpServletRequest req){
         
         ServletContext context=req.getServletContext();
         if(context!=null){
              ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDao cdao=(CategoryDao)ctx.getBean("categoryDaoImpl");     

      String name = req.getParameter("name");
      String status = req.getParameter("status");
      
      Category cat = new Category();
      cat.setName(name);
      cat.setStatus(status);
      
      cdao.addCategory(cat);
      System.out.println("added category this time");
             
         }
         return "added_categ";
     }
  
    
    @RequestMapping(value="/category" ,method=RequestMethod.GET)  
    public String category(HttpServletRequest req){
        
        ServletContext context =req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDao cdao=(CategoryDao)ctx.getBean("categoryDaoImpl");
            List<Category> categ=null;
            try{
                categ=(List<Category>)cdao.listCategory();
                req.setAttribute("li",categ);
                
                System.out.println("listed new categories");
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
            String uri=req.getRequestURI();
        }
        return "category";
  }
    
    @RequestMapping(value="/edit_subcat",method=RequestMethod.GET)
    public String edit_subcat(HttpServletRequest req){
        
         ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            SubcategoryDao dao=(SubcategoryDao)ctx.getBean("subcategoryDaoImpl");  
            Long scat_id=Long.parseLong(req.getParameter("cid"));
//            req.setAttribute("scat_id",scat_id);
            Subcategory sc=dao.getSubcategory(scat_id);
            req.setAttribute("ob",sc);
        }
        
        return "edit_subcat";
    }
    
    
    
    @RequestMapping(value="/editsubcat",method=RequestMethod.GET)
    public String editsubcat(HttpServletRequest req){
        
         ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            SubcategoryDao dao=(SubcategoryDao)ctx.getBean("subcategoryDaoImpl");  
            String scatname=req.getParameter("scatname");
            String status=req.getParameter("status");
            String cid=req.getParameter("cid");
       
            Long id=Long.parseLong(cid);
            Subcategory cat=dao.getSubcategory(id);
            cat.setName(scatname);
            cat.setStatus(status);

            dao.update(cat);
            List<Subcategory> clist=dao.listSubcategory();
            req.setAttribute("li", clist);
        String uri=req.getRequestURI();
        }
        return "subcategory";
    }
    
    @RequestMapping(value = "/add_subcat", method = RequestMethod.GET)
     public String add_subcateg(HttpServletRequest req) {
     
        
         ServletContext context =req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        CategoryDao dao=(CategoryDao)ctx.getBean("categoryDaoImpl");
        Category c=new Category();
//         long catid=req.getParameter("");
         try{
              List<Category> cat=null;
              cat=dao.listCategory();
              req.setAttribute("li",cat);
             
        
               
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
        
        }
     return "add_subcat";
   }
     
     @RequestMapping(value="/add_subcat_from_cat",method=RequestMethod.GET)
     public String add_subcat_from_cat(HttpServletRequest req)
     {
         
        ServletContext context=req.getServletContext();
        if(context!=null){
              ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDao dao=(CategoryDao)ctx.getBean("categoryDaoImpl");
            Long cat_id=Long.parseLong(req.getParameter("cid"));
            req.setAttribute("cat_id",cat_id);
        }
         return "add_subcat_from_cat";
     }

     @RequestMapping(value="/add_sub_from_cat",method=RequestMethod.GET)
     public String add_sub_from_cat(HttpServletRequest req){
         
          
        ServletContext context =req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            SubcategoryDao cdao=(SubcategoryDao)ctx.getBean("subcategoryDaoImpl");     
            CategoryDao dao=(CategoryDao)ctx.getBean("categoryDaoImpl");
      String name = req.getParameter("name");
      String status = req.getParameter("status");
      
      System.out.println(req.getParameter("cid"));
      Long id=Long.parseLong(req.getParameter("cid"));
      
      Subcategory cat = new Subcategory();
      cat.setName(name);
      cat.setStatus(status);
      Category c=new Category();
      c=dao.getCategory(id);
      
      cat.setCategory(c); 
      
      cdao.addSubcategory(cat);
    }
        return "added_subcat";
     }
     
      @RequestMapping(value="/changesubstatus", method=RequestMethod.GET)
    public String changesubstatus(HttpServletRequest req){
              
        ServletContext context= req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            SubcategoryDao dao=(SubcategoryDao)ctx.getBean("subcategoryDaoImpl");  
            List<Subcategory> category=(List<Subcategory>)dao.listSubcategory();
            Subcategory c1 = new Subcategory();
            long cid=0;
            String subid=req.getParameter("cid");
            cid=Long.parseLong(subid);
            c1=dao.getSubcategory(cid);
            
            if(c1.getStatus().equals("active")){
                c1.setStatus("inactive");
                dao.changeStatus(c1);
            }
            else{
                c1.setStatus("active");
                dao.changeStatus(c1);
            }
        
        Subcategory s = new Subcategory();
            
            try{
               List<Subcategory> cust=(List<Subcategory>)dao.listSubcategory();
                req.setAttribute("li", cust);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
       
    }
        return "subcategory";
    }
     
    @RequestMapping(value="/add_subcategory",method=RequestMethod.GET)
    public String add_subcategory(HttpServletRequest req){
        
        ServletContext context =req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            SubcategoryDao cdao=(SubcategoryDao)ctx.getBean("subcategoryDaoImpl");     
            CategoryDao dao=(CategoryDao)ctx.getBean("categoryDaoImpl");
      String name = req.getParameter("name");
      String status = req.getParameter("status");
      
      
      Long id=Long.parseLong(req.getParameter("cat_id"));
      
      Subcategory cat = new Subcategory();
      cat.setName(name);
      cat.setStatus(status);
      Category c=new Category();
      c=dao.getCategory(id);
      
      cat.setCategory(c); 
      
      cdao.addSubcategory(cat);
    }
        return "subcategory";
    }
  
    
    @RequestMapping(value="/subcategory",method=RequestMethod.GET)
    public String subcategory(HttpServletRequest req){
         
        ServletContext context =req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            SubcategoryDao cdao=(SubcategoryDao)ctx.getBean("subcategoryDaoImpl");
            List<Subcategory> scat=null;
            try{
                scat=(List<Subcategory>)cdao.listSubcategory();
                System.out.println("printing out the list");
                //System.out.println(scat.get(0).getCategory());
                req.setAttribute("li",scat);
                System.out.println("list printed");
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
            
        }
        return "subcategory";   
    }
    
    @RequestMapping(value="/view_subcat",method=RequestMethod.GET)
    public String view_subcat(HttpServletRequest req){
        ServletContext context=req.getServletContext();
        if(context!=null){
              ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            SubcategoryDao cdao=(SubcategoryDao)ctx.getBean("subcategoryDaoImpl");
            List<Subcategory> scat=null;
            long scat_id=0;
            scat_id=Long.parseLong(req.getParameter("cid"));
            Subcategory cat=new Subcategory();
            cat=cdao.getSubcategory(scat_id);
            try{
                req.setAttribute("li",cat);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
            String uri=req.getRequestURI();
        }
            
        
        
        
        return "view_subcat";
    }
    
    ///TO VIEW CATEGORIES(UPDATED)
    
     @RequestMapping(value="/viewCategory" ,method=RequestMethod.GET)
     public String viewcategory(HttpServletRequest req){
    
         ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDao dao=(CategoryDao)ctx.getBean("categoryDaoImpl");  
            List<Category> catli=dao.listCategory();
            req.setAttribute("catList", catli);
        String uri=req.getRequestURI();
        }
        return "ViewCategories";
    }
    
    
    
       
    @RequestMapping(value="/view_cat",method=RequestMethod.GET)
    public String view_cat(HttpServletRequest req)
    {
        ServletContext context=req.getServletContext();
        if(context!=null){
              ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDao cdao=(CategoryDao)ctx.getBean("categoryDaoImpl");
            List<Category> categ=null;
            long cat_id=0;
            cat_id=Long.parseLong(req.getParameter("cid"));
            Category cat=new Category();
            cat=cdao.getCategory(cat_id);
            try{
                
                req.setAttribute("li",cat);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
            String uri=req.getRequestURI();
        }
            
        
        
        
        return "view_cat";
    }
    
    //Controller with annotation 

//For Products Page that shows all Products

@RequestMapping(value="/viewAllProducts" ,method=RequestMethod.GET)
     public String viewAllProducts(HttpServletRequest req){
    
         ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");  
            List<Product> prodli=dao.listProducts();
            req.setAttribute("prodList", prodli);
        String uri=req.getRequestURI();
        }
        return "ViewAllProducts";
    }
     
     

    //to add a product
     @RequestMapping(value="/AddProduct" ,method=RequestMethod.GET)
     public String AddProducts(HttpServletRequest req){
    ServletContext context=req.getServletContext();
        if(context!=null){
              ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            SubcategoryDao cdao=(SubcategoryDao)ctx.getBean("subcategoryDaoImpl");
            List<Subcategory> scat=null;
//            long scat_id=Long.parseLong(req.getParameter("subcategory"));
            Subcategory cat=new Subcategory();
//            cat=cdao.getSubcategory(scat_id);
            try{
                scat=(List<Subcategory>)cdao.listSubcategory();
                req.setAttribute("subcatli",scat);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }}
         
        return "AddProduct";
    }
     
     @RequestMapping(value="/addProd" ,method=RequestMethod.GET)
     public String addProd(HttpServletRequest req)
     {
         ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");  
            Product p = new Product();
            ApplicationContext ctx1 =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            String sub=req.getParameter("subcategory");
            String name=req.getParameter("prodname");
            String status=req.getParameter("status");
            String url=req.getParameter("img");
            String pr=req.getParameter("price");
            int price=Integer.parseInt(pr);
            String st=req.getParameter("stock");
            int stock=Integer.parseInt(st);
            long id=Long.parseLong(sub);
            SubcategoryDao sdao=(SubcategoryDao)ctx1.getBean("subcategoryDaoImpl");  
            Subcategory subcat= sdao.getSubcategory(id);
          
          Category cat=subcat.getCategory();
            p.setSubcategory(subcat);

            p.setStatus(status);
            p.setPrice(price);
            p.setPname(name);
            p.setStock(stock);
            p.setCategory(cat);
            dao.addProduct(p);
            
            System.out.println("Added a Product");
            List<Product> list = dao.listProducts();
            req.setAttribute("prodList", list);
        }
        
      return "ViewAllProducts";   
     
     }
     
       @RequestMapping(value="/viewAProd" ,method=RequestMethod.GET)
    public String viewAProd(HttpServletRequest req, HttpServletResponse res)
    {
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");  
            Product p = new Product();
            String pid=req.getParameter("id");
            long id=Long.parseLong(pid);
            p=dao.getProduct(id);
            req.setAttribute("prod",p);
        }
            return "ViewAProd";
    }
    
    //EDIT THE PRODUCT
    
    @RequestMapping(value="/editProd" ,method=RequestMethod.GET)
    public String editProd(HttpServletRequest req, HttpServletResponse res)
    {
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");  
            Product p = new Product();
            String pid=req.getParameter("id");
            long id=Long.parseLong(pid);
            p=dao.getProduct(id);
            req.setAttribute("prod",p);
        }
            return "EditProd";
    }
    
     @RequestMapping(value="/editProduct" ,method=RequestMethod.POST)
     public String editProduct(HttpServletRequest req, HttpServletResponse response){
         ServletContext context = req.getServletContext();
//         @RequestParam CommonsMultipartFile[] fileUpload
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");  
            String prodname=req.getParameter("prodname");
              System.out.println("the Product name==="+prodname);

            String status=req.getParameter("status");
             System.out.println("the status==="+status);

             String stock=req.getParameter("stock");
//             int st=Integer.parseInt(stock);
             System.out.println("the stock==="+stock);

            String price=req.getParameter("price");
//            int pr=Integer.parseInt(price);
             System.out.println("price ==="+price);

//            String url=req.getParameter("img");
//            System.out.println("url=="+url);
//            
//            long pid=Long.parseLong(req.getParameter("pid"));
//            long cid=3;
//            System.out.println("long type id ==="+pid);
            
//            Product p=dao.getProduct(pid);
////            System.out.println("the category object==="+p);
////            p.setPname(prodname);
//            p.setStatus(status);
////            p.setPrice(pr);
////            p.setStock(st);
////            String url1="E:\\TRAINING\\My Project\\MySpringWeb\\web\\Uploads\\"+url;
////            p.
//            dao.update(p);
            String uri=req.getRequestURI();
           
//             To save Image Uploaded in project folder
//            try{
//                boolean isMultipart = ServletFileUpload.isMultipartContent(req);
//                response.setContentType("text/html");
//                File file;
//                String fileName;
//                String filePath= "E:\\TRAINING\\My Project\\MySpringWeb\\web\\Uploads\\";  
//         
//          DiskFileItemFactory factory = new DiskFileItemFactory();
//          ServletFileUpload upload =new ServletFileUpload(factory);
////        List fileItems;
//          
//          List fileItems = upload.parseRequest(req);
//          Iterator i=fileItems.iterator();
//          while (i.hasNext()) 
//          {
//          FileItem fi=(FileItem)i.next();
//              System.out.println("======"+fi);
//          if (!fi.isFormField())	
//          {
//              System.out.println("field name="+fi.getFieldName());
//            // Get the uploaded file parameters
//          if(fi.getFieldName().equals("product"))
//          {
//              String item=fi.getString();
//              System.out.println("product="+item);
//          }
//            String fieldName = fi.getFieldName();
//            fileName = fi.getName();
//            String contentType = fi.getContentType();
//
//            // Write the file
//            if( fileName.lastIndexOf("\\")>=0){
//               file=new File(filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
//               
//               System.out.println("1.");
//            }
//            else{
//               file=new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
//               System.out.println("2.");
//            }
//            fi.write(file);
//          }else{
//              
//          }
//          }//end of while
//        }catch(Exception ex)
//        {
//        ex.printStackTrace();
//        }
//            
            List<Product> plist=dao.listProducts();
            req.setAttribute("prodList", plist);
        }
        return "ViewAllProducts";
    }
     
     //TO CHANGE PRODUCT STATUS
     
      @RequestMapping(value="/changeProdStatus" ,method=RequestMethod.GET)
    public String changeProdStatus(HttpServletRequest req, HttpServletResponse res)
    {
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");  
            Product p = new Product();
            long id=Long.parseLong(req.getParameter("id"));
            System.out.println(id);
            p=dao.getProduct(id);
            dao.changeStatus(p);
            List<Product> list = dao.listProducts();
            req.setAttribute("prodList", list);
        }  
     return "ViewAllProducts";   
    }
    
    @RequestMapping(value="/prodStatusCheck" ,method=RequestMethod.GET)
    public String prodStatusCheck(HttpServletRequest req, HttpServletResponse res)
    {   
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");  
            Product m1=new Product();
            String[] cats=req.getParameterValues("choose");
            
            for(String mid : cats)
            {
                System.out.println("category id chosen ==="+mid);
                int id=Integer.parseInt(mid);
                System.out.println("category id integer ==="+id);

                m1=dao.getProduct(id);
                    if(req.getParameter("activate")!=null)
                    {
                        m1.setStatus("Active");
                        dao.update(m1);   
                    }
                    if(req.getParameter("inactivate")!=null)
                    {
                        m1.setStatus("inactive");
                        dao.update(m1);
                    }
            }
            List<Product> list=dao.listProducts();
            req.setAttribute("prodList", list);
        
        }
         return "ViewAllProducts";
    }




    
   



     

//For settings Page of category ... which has activate/inactivate, view and edit options

//Change status by clicking on tick & cross buttons

@RequestMapping(value="/changeCatStatus" ,method=RequestMethod.GET)
    public String changeCatStatus(HttpServletRequest req, HttpServletResponse res)
    {
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDao dao=(CategoryDao)ctx.getBean("categoryDaoImpl");  
            Category c = new Category();
            long id=Long.parseLong(req.getParameter("id"));
            System.out.println(id);
            c=dao.getCategory(id);
            dao.changeStatus(c);
            List<Category> list = dao.listCategory();
            req.setAttribute("catList", list);
        }  
     return "ViewCategories";   
    }

	
	
	
	// Activate/ inactivate multiple categories using checkbox
	
	@RequestMapping(value="/catStatusCheck" ,method=RequestMethod.GET)
    public String catStatusCheck(HttpServletRequest req )
    {   
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CategoryDao dao=(CategoryDao)ctx.getBean("categoryDaoImpl");  
            Category m1=new Category();
            String[] cats=req.getParameterValues("choose");
            
            for(String mid : cats)
            {
                System.out.println("category id chosen ==="+mid);
                int id=Integer.parseInt(mid);
                System.out.println("category id integer ==="+id);

                m1=dao.getCategory(id);
                    if(req.getParameter("activate")!=null)
                    {
                        m1.setStatus("active");
                        dao.update(m1);   
                    }
                    if(req.getParameter("inactivate")!=null)
                    {
                        m1.setStatus("inactive");
                        dao.update(m1);
                    }
            }
            List<Category> list=dao.listCategory();
            req.setAttribute("catList", list);
        
        }
         return "ViewCategories";
    }
    
    
    @RequestMapping(value="/customer" ,method=RequestMethod.GET)
    public String customer(HttpServletRequest req){
        
        ServletContext context= req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CustomerDao dao=(CustomerDao)ctx.getBean("customerDaoImpl");  
            List<Customer> customer=null;
            
            try{
                customer=(List<Customer>)dao.listCustomer();
                req.setAttribute("li", customer);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
            String uri=req.getRequestURI();
        
        }
                return "customer";

    }
    
    @RequestMapping(value="/new_customer",method=RequestMethod.GET)
    public String new_customer(HttpServletRequest req){
       
        ServletContext context=req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CustomerDao dao=(CustomerDao)ctx.getBean("customerDaoImpl");
            Customer c=new Customer();
            List<Customer> customer=null;
           
            try{customer=(List<Customer>)dao.newCustomer();
                req.setAttribute("li", customer);
                            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
             }
        }
        return "new_customer";
        
    }
    
   
    @RequestMapping(value="/view_cust", method=RequestMethod.GET)
    public String view_cust(HttpServletRequest req){
        ServletContext context=req.getServletContext();
        if(context!=null){
            ApplicationContext ctx=WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CustomerDao dao=(CustomerDao)ctx.getBean("customerDaoImpl");
            Customer c=new Customer();
            List<Customer> customer=null;
            long cus_id=0;
            cus_id=Long.parseLong(req.getParameter("cid"));
            c=dao.getCustomer(cus_id);
            try{
                req.setAttribute("li",c);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
             }
            
            String uri=req.getRequestURI();
        }
        return "view_cust";
        
        
    }

    
    @RequestMapping(value="/update", method=RequestMethod.GET)
    public String update(HttpServletRequest req){
              
        ServletContext context= req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CustomerDao dao=(CustomerDao)ctx.getBean("customerDaoImpl");  
            List<Customer> customer=(List<Customer>)dao.listCustomer();
            Customer c1 = new Customer();
            long cid=0;

            String selected[]=req.getParameterValues("check");
            
           
                 for(String e1: selected)
                 {
                     cid=Long.parseLong(e1);
                     c1=dao.getCustomer(cid);
                   
                        if(req.getParameter("active")!=null){
                         String s=""+c1.getId();
                        if(e1.equals(s))
                    {
                           c1.setStatus("active");
                            System.out.println("the status of "+c1.getId() + "is" +c1.getStatus());
                            dao.update(c1);
                    }
                
                Customer c = new Customer();
            
            try{
                customer=(List<Customer>)dao.listCustomer();
                req.setAttribute("li", customer);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
                 }
                     
             if(req.getParameter("inactive")!=null)    {
                      
                 
                     String s=""+c1.getId();
                
                
                    if(e1.equals(s))
                    {
                        System.out.println("this is getId()"+c1.getId());
                        
                        c1.setStatus("inactive");
                        dao.update(c1);
                    }
                
                Customer c = new Customer();
            
            try{
                customer=(List<Customer>)dao.listCustomer();
                req.setAttribute("li", customer);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
                     
                 }
                 
             }
             
             
            Customer c = new Customer();
            
            try{
                customer=(List<Customer>)dao.listCustomer();
                req.setAttribute("li", customer);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
}
        return "customer";
}

    @RequestMapping(value="/change", method=RequestMethod.GET)
    public String change(HttpServletRequest req){
              
        ServletContext context= req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            CustomerDao dao=(CustomerDao)ctx.getBean("customerDaoImpl");  
            List<Customer> customer=(List<Customer>)dao.listCustomer();
            Customer c1 = new Customer();
            long cid=0;
            String selid=req.getParameter("cid");
            cid=Long.parseLong(selid);
            c1=dao.getCustomer(cid);
            
            if(c1.getStatus().equals("active")){
                c1.setStatus("inactive");
                dao.changeStatus(c1);
            }
            else{
                c1.setStatus("active");
                dao.changeStatus(c1);
            }
        
        Customer c = new Customer();
            
            try{
               List<Customer> cust=(List<Customer>)dao.listCustomer();
                req.setAttribute("li", cust);
            }catch(org.hibernate.HibernateException e){
                System.out.println(e);
            }
       
    }
        return "customer";
    }
    
    @RequestMapping(value="/stock_over",method=RequestMethod.GET)
    public String stock_over(HttpServletRequest req){
        ServletContext context = req.getServletContext();
        if(context != null){
          ApplicationContext ctx =  
                    WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            ProductDAO dao=(ProductDAO)ctx.getBean("productDAOImpl");  
            List<Product> prodli=dao.stock_over();
            req.setAttribute("prodList", prodli);
        String uri=req.getRequestURI();
        }
        return "stock_over";

        
    }
}

           
      

