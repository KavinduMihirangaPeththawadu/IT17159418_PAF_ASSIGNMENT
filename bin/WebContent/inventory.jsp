<%@page import="com.inventory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventory Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="Components/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="Components/inventory.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-7">
				<h1 class="m-3">Inventory Management</h1>

				<form id="formInventory" name="formInventory" method="post"
					action="inventory.jsp">

					Inventory Name: 
					<input id="inventoryName" name="inventoryName"
						type="text" class="form-control form-control-sm"> <br>
					Inventory Type: 
					<input id="inventoryType"
						name="inventoryType" type="text"
						class="form-control form-control-sm"> <br> 
					    <label for="inventoryStore">Inventory Store</label>
    <select class="form-control" id="inventoryStore" name="inventoryStore">
      <option value="">-- select --</option>
      <option value="1">Store 1</option>
      <option value="2">Store 2</option>
      <option value="3">Store 3</option>
      <option value="4">Store 4</option>
    </select><br>
					Inventory Quantity: 
					<input id="inventoryQuantity" name="inventoryQuantity" type="text"
						class="form-control form-control-sm"> <br> 
					<input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidInventoryIDSave" name="hidInventoryIDSave" value="">

				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

			</div>
		</div>




		<br>
		<div id="divInventoryGrid">

			<%
			inventory inventoryObj = new inventory();
			out.print(inventoryObj.readInventory());
			%>
		</div>
</body>
</html>