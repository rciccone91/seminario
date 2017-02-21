<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Property | Register</title>

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
						<div class="aa-signin-form-title">
							<!-- <a class="aa-property-home" href="index.html">Regresar a Inicio</a> -->
							<h4>Registrarse como usuario</h4>
						</div>
						<form class="contactform" method="post" action="RegistroServlet">
							<div class="aa-single-field">
								<label for="usuario">Usuario <span class="required">*</span></label>
								<input type="text" required="required" aria-required="true"
									value="" name="usuario" id="usuario">
								<div id="usuarioExiste"
									class="alert alert-warning alert-dismissable">Usuario ya
									existente, por favor ingrese otro</div>
							</div>
							<c:if test="${sessionScope.usuario != null}">
								<c:if test="${sessionScope.usuario.admin}">
									<div class="aa-single-field">
										<label for="rol">Rol<span class="required">*</span></label>
										<div class="aa-single-advance-search">
											<select id="rol" name="rol">
												<option value="1">Cliente</option>
												<option value="2">Administrador</option>
											</select>
										</div>
									</div>
								</c:if>
							</c:if>
							<div class="aa-single-field">
								<label for="nombre">Nombre<span class="required">*</span></label>
								<input type="text" required="required" aria-required="true"
									value="" name="nombre">
							</div>
							<div class="aa-single-field">
								<label for="dni">DNI<span class="required">*</span></label> <input
									type="text" required="required" aria-required="true" value=""
									name="dni" size="300px">

							</div>
							<div class="aa-single-field">
								<label for="telefono">Teléfono<span class="required">*</span></label>
								<input type="text" required="required" aria-required="true"
									value="" name="telefono" size="300px">
							</div>
							<div class="aa-single-field">
								<label for="email">Email <span class="required">*</span></label>
								<input type="email" required="required" aria-required="true"
									id="email" value="" name="email">
							</div>
							<div class="aa-single-field">
								<label for="password">Password <span class="required">*</span></label>
								<input id="password" type="password" name="password">
							</div>
							<div class="aa-single-field">
								<label for="confirmPassword">Confirmar Password <span
									class="required">*</span></label> <input type="password"
									id="confirmPassword" name="confirmPassword">
							</div>
							<div class="aa-single-submit">
								<input type="submit" value="Registrarse" id="submitReg"
									name="submitReg" onclick="return validarPassword()">
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

	<script>
		$("#usuarioExiste").hide();

		//window.onload =  $("usuarioExiste").hide();

		function validarPassword() {
			if (document.getElementById("password").value != document
					.getElementById("confirmPassword").value) {
				alert("Las contraseñas no coinciden");
				return false;
			}
		}

		$(document).ready(function() {
			$('#usuario').blur(function() {
				$.ajax({
					url : 'ValidacionesServlet',
					data : {
						usuario : $("#usuario").val()
					},
					success : function(responseText) {
						if (responseText == "OK") {
							$("#usuarioExiste").hide();
							$("#submitReg").prop("disabled", false);
						} else {
							$("#usuarioExiste").show();
							$("#submitReg").prop("disabled", true);
						}
					}
				});
			});
		});
	</script>



</body>
</html>