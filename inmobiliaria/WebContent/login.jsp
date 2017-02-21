<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Property | Signin</title>

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
<link id="switcher" href="css/theme-color/default-theme.css"
	rel="stylesheet">

<!-- Main style sheet -->
<link href="css/style.css" rel="stylesheet">


<!-- Google Font -->
<link href='https://fonts.googleapis.com/css?family=Vollkorn'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
	<section id="aa-signin">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="aa-signin-area">

					<div class="aa-signin-form">
						<c:if test="${error != null}">
							<div class="alert alert-info alert-dismissable">${error}</div>
						</c:if>
						<c:if test="${error == null}">
							<div class="aa-signin-form-title">
								<h4>Ingresa con tu cuenta</h4>
							</div>
						</c:if>
						<form class="contactform" action="LoginServlet" method="post">
							<div class="aa-single-field">
								<label for="usuario">Usuario<span class="required">*</span></label>
								<input id="usuario" name="usuario" type="text"
									required="required" aria-required="true" value=""
									name="usuario">
							</div>
							<div class="aa-single-field">
								<label for="password">Password <span class="required">*</span></label>
								<input id="password" name="password" type="password" required="required"
									name="password">
							</div>
							<div class="aa-single-submit">
								<input type="submit" value="Ingresar" class="aa-browse-btn"
									name="submit">
								<p>
									¿Todavía no tenes una cuenta? <a href="register.html">REGISTRATE
										AHORA!</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<!-- jQuery library -->
	<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.js"></script>
	<!-- slick slider -->
	<script type="text/javascript" src="js/slick.js"></script>
	<!-- Price picker slider -->
	<script type="text/javascript" src="js/nouislider.js"></script>

	<!-- Custom js -->
	<script src="js/custom.js"></script>

	<script type="text/javascript">
	
	function validarBuscar() {
		var u = document.getElementById("usuario").value
		var p = document.getElementById("password").value
		if( u == "" || p == "" ) {
			alert("Por favor, ingrese el password y la contraseña!");
			return false;
		}
		return true;
	}
	
	</script>


</body>
</html>