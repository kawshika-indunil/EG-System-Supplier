<%@ page import="com.Supplier"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Supplier</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/suppliervali.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Supplier Service</h1>
				<form id="formSupplier" name="formSupplier" method="post" action="SupplierUI.jsp">  
					Account No:  
 	 				<input id="SupAccNo" name="SupAccNo" type="text"  class="form-control form-control-sm">
					<br>Units:   
  					<input id="SupUnits" name="SupUnits" type="text" class="form-control form-control-sm">   
  					<br>Unit Price:   
  					<input id="SupUnitPrice" name="SupUnitPrice" type="text"  class="form-control form-control-sm">
  					<br>Start Date:   
  					<input id="SupSdate" name="SupSdate" type="date"  class="form-control form-control-sm">
  					<br>End Date:   
  					<input id="SupEdate" name="SupEdate" type="date"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidSupplierIDSave" name="hidSupplierIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divSupplierGrid">
					<%
					    Supplier SupplierObj = new Supplier();
						out.print(SupplierObj.readSupplier());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>