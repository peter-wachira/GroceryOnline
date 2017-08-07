<%-- 
    Document   : category
    Created on : Nov 10, 2016, 11:13:10 AM
    Author     : Chonzom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <style>
        
        button.accordion {
    background-color: #eee;
    color: #444;
    cursor: pointer;
    padding: 18px;
    width: 100%;
    border: none;
    text-align: left;
    outline: none;
    font-size: 15px;
    transition: 0.4s;
}

button.accordion.active, button.accordion:hover {
    background-color: #ddd; 
}

div.panel {
    padding: 0 18px;
    display: none;
    background-color: white;
}

div.panel.show {
    display: block;
}

       

.hovering-item:hover {
	background-color: #80bfff;
}

    </style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADMIN HOME</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="css/clockp.js"></script>
<script type="text/javascript" src="css/clockh.js"></script> 
<script type="text/javascript" src="css/jquery.min.js"></script>
<script type="text/javascript" src="css/ddaccordion.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].onclick = function(){
        this.classList.toggle("active");
        this.nextElementSibling.classList.toggle("show");
  }
}
</script>
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
    "> Welcome Shaun!!
    </li>
            <li><div class="hovering-item"> <a href="profile.com" style="display: block;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    border-bottom-style: groove;
       border-bottom-color: white;
       border-radius: 5px;">
                    
		
	Profile</a></div>
                </li>
            <li>
               <div class="hovering-item"><a href="changePassword.com" style="display: block;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    border-bottom-style: groove;
       border-bottom-color: white;
       border-radius: 5px;">
                    
		
	Change Password</a> </div>
            </li>
            <li>
               <div class="hovering-item"> <a href="viewAllProducts.com" style="display: block;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    border-bottom-style: groove;
       border-bottom-color: white;
       border-radius: 5px;"> 
		
	Stock</a></div>
            </li>
            <li>
               <div class="hovering-item"> <a href="logout.com" style="display: block;
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
    
    <div class="right_content">            
        
    <h2>Admin Home</h2> 
       
    <!--<form action="catStatusCheck.com">-->
                   
<!--<table id="rounded-corner" summary="2007 Major IT Companies' Profit">-->
<div>
    
    <h3 style="position:absolute;">New Orders
       <br/> Product ID: 3
       <br/> <a href="new_order.com">More Orders </a>
       
      
    </h3>
     <img src="images/bubble.png" width="330px" height="100px"/>
     </div>
    <div>
        <h3 style="position:absolute;">New Customers<br/>
        Customer name: anam 
        <br/>
        <a href="new_customer.com">More customers</a></h3>
        <img src="images/bubble.png" width="330px" height="100px"/>
    
    </div>
    <div>
        <h3 style="position:absolute;">Out of stock!!<br/>
         Item name:Figaro Olive Oil
        <br/>
        <a href="stock_over.com">More items</a></h3>
       <img src="images/bubble.png" width="330px" height="100px"/>
    
    </div>
<div>
<!--<button class="accordion">Section 1</button>
<div class="panel">
    <ul><li>Hey</li>
        <li>Hello</li>
        <li>Bye</li></ul>
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
</div>
</div>
<button class="accordion">Section 2</button>
<div class="panel">
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
</div>
</div>-->

<!--</table>-->  
       
<!--	 <a href="add_cat.com" class="bt_green"><span class="bt_green_lft"></span><strong>Add new category</strong><span class="bt_green_r"></span></a>
     <a href="view_cat.com?cid=${category.id}" class="bt_blue"><span class="bt_blue_lft"></span><strong>View items from category</strong><span class="bt_blue_r"></span></a>
     <a href="#" class="bt_red"><span class="bt_red_lft"></span><strong>Inactivate items</strong><span class="bt_red_r"></span></a> -->
     
     
    <!--</form>-->
    
      
     
     </div><!-- end of right content-->
            
                    
  </div>   <!--end of center content -->               
                    
                    
    
    
    <div class="clear"></div>
    </div> <!--end of main content-->
	
    
    <div class="footer">
    
    	<div class="left_footer">IN ADMIN PANEL | Powered by <a href="http://indeziner.com">INDEZINER</a></div>
    	<div class="right_footer"><a href="http://indeziner.com"><img src="images/indeziner_logo.gif" alt="" title="" border="0" /></a></div>
    
    </div>

</div>		
</body>
</html>