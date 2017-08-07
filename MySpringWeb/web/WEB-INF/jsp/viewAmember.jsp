<%-- 
    Document   : viewMem
    Created on : Nov 17, 2016, 12:23:59 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADMIN PANEL</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="css/clockp.js"></script>
<script type="text/javascript" src="css/clockh.js"></script> 
<script type="text/javascript" src="css/jquery.min.js"></script>
<script type="text/javascript" src="css/ddaccordion.js"></script>
<script type="text/javascript">
ddaccordion.init({
	headerclass: "submenuheader", //Shared CSS class name of headers group
	contentclass: "submenu", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["suffix", "<img src='images/plus.gif' class='statusicon' />", "<img src='images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})
</script>

<script type="text/javascript" src="css/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
	
</script>

<script language="javascript" type="text/javascript" src="css/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/niceforms-default.css" />

</head>
<body>
<div id="main_container">

	<div class="header">
    <div class="logo"></div>
    
    <div class="right_header"></div>
    <div id="clock_a"></div>
    </div>
    
    <div class="main_content">
    
                    <div class="menu">
                   <ul>
                    <li><a class="current" href="loghome.com">Admin Home</a></li>
                    <li><a href="viewCategory.com"> Categories<!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                        <ul>
                                <li><a href="add_cat.com" title="">Add Categories</a></li>
                                <li><a href="viewCategory.com" title="">Manage Categories</a></li>
                        </ul>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                    </li>
                    <li><a href="subcategory.com">Sub-Categories<!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                        <ul>
                        <li><a href="add_subcat.com" title="">Add Sub-Categories</a></li>
                        <li><a href="subcategory.com" title="">Manage Sub-Categories</a></li>
                        <li><a href="viewAllProducts.com" title="">Manage Products</a></li>
            
                        </ul>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                    </li>
                    
                    <li><a href="viewUsers.com"> Members<!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                        <ul>
                        <li><a href="viewUsers.com" title="">View Users</a></li>
                        <li><a href="AddMem.com" title="">Add New User</a></li>
                                              
                                 </ul>
                    <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                    </li>
                    <li><a href="orders.com"> Orders<!--[if IE 7]><!--></a><!--<![endif]-->
                    <!--[if lte IE 6]><table><tr><td><![endif]-->
                                          </li>
                    
                    <!--<li><a href="profile.com">Profile</a></li>-->
                    <li><a href="customer.com">Customer Details</a></li>
                    <!--<li><a href="">Contact</a></li>-->
                    </ul>
                     </div> 
    <div class="center_content">  
    
    <div class="left_content">
    
<!--    		<div class="sidebar_search">
            <form>
            <input type="text" name="" class="search_input" value="search keyword" onclick="this.value=''" />
            <input type="image" class="search_submit" src="images/search.png" />
            </form>            
            </div>
    -->
             <ul style="list-style-type: none;
    margin: 0;
    padding: 0;
    width: 200px;
        background-color: #66b3ff;
        /*position: fixed;  Make it stick, even on scroll*/ 
       overflow: auto;
       border-color: white;
       border-radius: 5px;
       ">
            
            <li>
                <img src="images/suzy.png" width="200px" height="200px"/>
            </li>
            <li style="display: block;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    
    border-bottom-style:groove; 
       border-bottom-color: white;
       border-radius: 5px;
    "> Welcome <%=session.getAttribute("aname")%>!!
    </li>
            <li><div class="hovering-item"> <a href="" style="display: block;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    border-bottom-style: groove;
       border-bottom-color: white;
       border-radius: 5px;">
                    
		
	Profile</a></div>
                </li>
            <li>
               <div class="hovering-item"><a href="" style="display: block;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    border-bottom-style: groove;
       border-bottom-color: white;
       border-radius: 5px;">
                    
		
	Change Password</a> </div>
            </li>
            <li>
               <div class="hovering-item"> <a href="" style="display: block;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    border-bottom-style: groove;
       border-bottom-color: white;
       border-radius: 5px;"> 
		
	Stock</a></div>
            </li>
            <li>
               <div class="hovering-item"> <a href="" style="display: block;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    border-bottom-style: groove;
       border-bottom-color: white;
       border-radius: 5px;
    "> 
		
	Logout</a></div>
            </li>
  
                          </ul>
                
              
    

    
    </div>
  
      
    <jsp:useBean id="li" class="java.util.ArrayList" scope="session"></jsp:useBean>
    <div class="right_content">            
        
    <h2>Profile</h2> 
                    
        <form action="">
                    
<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
    <thead>
<!--    	<tr>
            <th> </th>
            <th scope="col" class="rounded">Member Name</th>
            <th scope="col" class="rounded">Member ID</th>
            <th scope="col" class="rounded">Status</th>
            <th scope="col" class="rounded">view</th>
            <th scope="col" class="rounded">Edit</th>
            <th scope="col" class="rounded-q4">Delete</th>
        </tr>-->
    </thead>
        
    <tbody>
<!--   	<tr>
        	<td><input type="checkbox" name="" /></td>
                    <th> Member Id  </th>
                    <th> Member Name</th>
                    <th> status </th>
                    <th> Edit </th>
                    <th> Delete</th>
        </tr>           -->
<!--            <td><a href="#"><img src="images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask"><img src="images/trash.png" alt="" title="" border="0" /></a></td>
        </tr>
        -->
    <%--<c:forEach var="ob" items="${memList}" >--%>                  
<!--                    <tr>
                        <td><input type="checkbox" name="choose" value="${ob.id}"/></td>
                        <td> ${ob.name}</td>
                        <td> ${ob.id} </td>
                        <td> ${ob.status} </td>
                        <td><a href=" "><img src="images/viewIcon.jpg" height="15px" width="15px" alt="" title="" border="0" /></a></td>
                        <td> ${ob.address} </td>
                        <td><a href="memberDetail.com?id=${ob.id}"><img src="images/user_edit.png" alt="" title="" border="0" /></a></td>-->
                        <%--<c:choose>--%>
                        <%--<c:when test="${ob.status=='Active'}">--%>
                            <!--<td><a href="delMember.com?id=${ob.id}" class="ask"><img src="images/Active.jpg" height="15px" width="15px"alt="" title="" border="0" /></a></td>-->
                        <%--</c:when>--%>  
                        <%--<c:when test="${ob.status=='inactive'}">--%>
                             <!--<td><a href="delMember.com?id=${ob.id}" class="ask"><img src="images/inactiveXRED.png" height="15px" width="15px"alt="" title="" border="0" /></a></td>-->
                        <%--</c:when>--%>               
                        <%--<c:when test="${ob.status=='Main Admin'}">--%>
                                <!--<td><a href="delMember.com?id=${ob.id}" class="ask"><img src="images/AdminPNG.jpg" height="15px" width="15px"alt="" title="" border="0" /></a></td>-->
                        <%--</c:when>--%>
                                       
                        <%--</c:choose>--%>
                        <!--</tr>-->
 
       <%--</c:forEach>--%>   
       
       <tr>
           <td> Name : </td>
           <td> ${mem.name} </td>
       </tr>
       <tr>
           <td> Username : </td>
           <td> ${mem.username} </td>
        </tr> 
       
        <tr>
           <td> Date of Birth : </td>
           <td> ${mem.dob} </td>
        </tr> 
       <tr>
           <td> Date of Registration : </td>
           <td> ${mem.dom} </td>
        </tr> 
        <tr>
           <td> Status : </td>
           <td> ${mem.status} </td>
        </tr> 
       
        <tr>
           <td> Email Id : </td>
           <td> ${mem.email} </td>
        </tr> 
       <tr>
           <td> Date of Birth : </td>
           <td> ${mem.dob} </td>
        </tr> 
       <tr>
           <td> Phone No. : </td>
           <td> ${mem.phoneno} </td>
        </tr> 
       
       
       
    </tbody>
       
       
       
       
</table>

    
     <a href="changePassword.com" class="bt_blue"><span class="bt_blue_lft"></span><strong>Change Password</strong><span class="bt_blue_r"></span></a>
    
</form>
     
<!--        <div class="pagination">
        <span class="disabled"><< prev</span><span class="current">1</span><a href="">2</a><a href="">3</a><a href="">4</a><a href="">5</a>…<a href="">10</a><a href="">11</a><a href="">12</a>...<a href="">100</a><a href="">101</a><a href="">next >></a>
        </div> 
     -->
     </div><!-- end of right content-->
                   
  </div>   <!--end of center content -->               
    
    <div class="clear"></div>
    </div> <!--end of main content-->
	
    
    <div class="footer">
    
    	<div class="left_footer"> GROCERY ONLINE <a href="http://indeziner.com">INDEZINER</a></div>
    	<div class="right_footer"><a href="http://indeziner.com"><img src="images/indeziner_logo.gif" alt="" title="" border="0" /></a></div>
    
    </div>

</div>		
</body>
