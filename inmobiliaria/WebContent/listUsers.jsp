c<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html SYSTEM "about:legacy-compat"> 
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <title>CM Inmobiliaria| Home</title>
    
    <!-- Favicon -->
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">

    <!-- Font awesome -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">   
    <!-- slick slider -->
    <link rel="stylesheet" type="text/css" href="css/slick.css">
    <!-- price picker slider -->
    <link rel="stylesheet" type="text/css" href="css/nouislider.css"> 
    <!-- Theme color -->
    <link id="switcher" href="css/theme-color/default-theme.css" rel="stylesheet">     

    <!-- Main style sheet -->
    <link href="css/style.css" rel="stylesheet">    

   
    <!-- Google Font -->
    <link href='https://fonts.googleapis.com/css?family=Vollkorn' rel='stylesheet' type='text/css'>    
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="aa-price-range">
	<table class="table table-hover" >
        <thead>
        	<tr>
        		<th colspan="10">Detalle de Usuarios</th>
        	</tr>
            <tr>
                <th>Nombre Usuario</th>
                <th>Contraseña</th>
                <th>Mail</th>
                <th>Nombre/s</th>
                <th>DNI</th>
                <th>Rol Usuario</th>
                <th>Telefono</th>
                <th colspan="2">Action</th>
            </tr>
        </thead>
        <tbody>
        	<c:if test="${usuarios == null }">
        		<tr><td colspan="10">No se Encontraron Usuarios</td>
        		</c:if>
            <c:forEach items="${usuarios}" var="usu">
                <tr>
                    <td><c:out value="${usu.usuario}" /></td>
                    <td><c:out value="${usu.password}" /></td>
                    <td><c:out value="${usu.mail}" /></td>
                    <td><c:out value="${usu.nombre}" /></td>
                    <td><c:out value="${usu.dni}" /></td>
                    <td><c:out value="${usu.rol_id}" /></td>
                    <td><c:out value="${usu.telefono}" /></td>
                    <td><a
                        href="UsuarioController.do?action=edit&nombre_usuario=<c:out value="${usu.usuario }"/>">Update</a></td>
                    <td><a
                        href="UsuarioController.do?action=delete&nombre_usuario=<c:out value="${usu.usuario }"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p>
        <a href="UsuarioController.do?action=insert">Add Usuario</a>
    </p>	
</body>
</html>