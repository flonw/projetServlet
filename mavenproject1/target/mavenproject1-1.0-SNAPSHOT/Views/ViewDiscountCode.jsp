<%-- 
    Document   : ViewDiscountCode
    Created on : 19 juin 2019, 22:41:06
    Author     : bfrancois
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>Saisie d'un taux de remise</title>
			<!-- Formatage d'une table par des CSS, inspiré de : https://stackoverflow.com/questions/5967564/form-inside-a-table -->
			<style>
				.table
				{
					display:table;
					border-collapse:separate;
					border-spacing:2px;
				}
				.thead
				{
					display:table-header-group;
					color:white;
					font-weight:bold;
					background-color:grey;
				}
				.tbody
				{
					display:table-row-group;
				}
				.tr
				{
					display:table-row;
				}
				.td
				{
					display:table-cell;
					border:1px solid black;
					padding:1px;
				}			
		</style>		
	</head>
    
	<body>
		<!-- On montre le formulaire de saisie -->
		<h1>Edition des taux de remise</h1>
                
		<form method='post'>
			Code : <input name="code" type="text" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES">
                        <br/>
			Taux : <input name="taux" type="number" step="0.01" min="0.0" max="99.99" size="5">
                        <br/>
			<input type="submit" name="action" value="ADD" value="Ajouter">
		</form>
		
		<div><h4></h4></div>
		
		<div class="table">
                    
			<div class="thead">
                            <div class="td">Code</div>
                            <div class="td">Taux</div>
                            <div class="td">Action</div>
                        </div>
                    
			<div class="tbody">
                            <c:forEach var="codeDiscount" items="${listeCodeDiscount}" varStatus="status">
                                <form class="tr" method="post" >
                                    <div class="td"><input type="text" name="code" value="${codeDiscount.discount_code}" readonly/></div>
                                    <div class="td"><input name="taux" type="number" step="0.01" min="0.0" max="99.99" size="5" value="${codeDiscount.discount_rate}"/></div>
                                    <div class="td"><input type="submit" name="action" value="DELETE"/></div>
                                </form>
                            </c:forEach>			  
			</div>
		</div>
	</body>        
</html>

