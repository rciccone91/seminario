<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CM Inmobiliaria | Home</title>

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
<body class="aa-price-range">
	<!-- Pre Loader -->
	<div id="aa-preloader-area">
		<div class="pulse"></div>
	</div>
	<!-- SCROLL TOP BUTTON -->
	<a class="scrollToTop" href="#"><i class="fa fa-angle-double-up"></i></a>
	<!-- END SCROLL TOP BUTTON -->

	<!-- Start header section -->
	<header id="aa-header">
	<div class="container">
		<input id="propSel" name="propSel" type="hidden" value="0">
		<div class="row">
			<div class="col-md-12">
				<div class="aa-header-area">
					<div class="row">
						<div class="col-md-6 col-sm-6 col-xs-6">
							<div class="aa-header-left">
								<div class="aa-telephone-no">
									<span class="fa fa-phone"></span> 034505-920278
								</div>
								<div class="aa-email hidden-xs">
									<span class="fa fa-envelope-o"></span> cm.inmobiliaria@gmail.com
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6">
							<div class="aa-header-right">
								<c:if test="${sessionScope.usuario == null}">
									<a href="registro.jsp" class="aa-register">Registrarse</a>
									<a href="login.jsp" class="aa-login">Login</a>
								</c:if>
								<form action="${pageContext.request.contextPath}/LogoutServlet"
									method="post">
									<c:if test="${sessionScope.usuario != null}">
										<input id="logout" name="logout" type="submit" value="Logout"
											class="btn-link">
									</c:if>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</header>
	<!-- End header section -->

	<!-- Start menu section -->
<section id="aa-menu-area"> <nav
		class="navbar navbar-default main-navbar" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<!-- FOR MOBILE VIEW COLLAPSED BUTTON -->
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- LOGO -->
			<!-- Text based logo -->
			<a class="navbar-brand aa-logo" href="${pageContext.request.contextPath}/IndexServlet"> CM <span>Inmobiliaria</span></a>
			<!-- Image based logo -->
			<!-- <a class="navbar-brand aa-logo-img" href="index.html"><img src="img/logo.png" alt="logo"></a> -->
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul id="top-menu" class="nav navbar-nav navbar-right aa-main-nav">
				<li class="active"><a href="${pageContext.request.contextPath}/IndexServlet">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown">Propiedades <span
						class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${pageContext.request.contextPath}/ResultadosPropiedadServlet?op=2">En Alquiler</a></li>
						<li><a href="${pageContext.request.contextPath}/ResultadosPropiedadServlet?op=1">En Venta</a></li>
					</ul></li>
				<c:if test="${sessionScope.usuario != null}">
					<c:if test="${sessionScope.usuario.admin}">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="blog-archive.html">Usuarios<span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="registro.jsp">Alta</a></li>
								<li><a href="paginaEnConstruccion.jsp">Baja</a></li>
								<li><a href="paginaEnConstruccion.jsp">Modificación</a></li>
							</ul></li>
					</c:if>
				</c:if>
				<c:if test="${sessionScope.usuario != null}">
				<li><a href="${pageContext.request.contextPath}/FavoritasServlet">Favoritas</a></li>
				<li><a href="${pageContext.request.contextPath}/ReservadasServlet">Reservadas</a></li>
				</c:if>
				<li><a href="contact.html">Contacto</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav> </section>
	<!-- Start Proerty header  -->

	<section id="aa-property-header">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="aa-property-header-inner">
					<h2>${prop.tipoDePropiedad.descripcion}-
						${prop.tipoDeOperacion.descripcion}</h2>
					<ol class="breadcrumb">
						<li class="active"></li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- End Proerty header  -->

	<!-- Start Properties  -->
	<section id="aa-properties">
	<div class="container">
		<div class="row">
			<div class="col-md-11">
				<div class="aa-properties-content">
					<!-- Start properties content body -->
					<div class="aa-properties-details">
						<div class="aa-properties-details-img">
							<c:forEach var="i" items="${prop.imagenes}">
								<img src="${i.archivo}" alt="img">
							</c:forEach>
						</div>
						<div class="aa-properties-info">
							<input id="propId" name="propId" type="hidden"
								value="${prop.id}">
							<h2>${prop.direccion}-${prop.ciudad.ciudad}</h2>
							<span class="aa-price">$${prop.precio}</span>
							<p>${prop.desc}</p>
							<h4>Informacion de la propiedad</h4>
							<ul>
								<li>Fecha de Construccion: ${prop.fechaDeConstruccion}</li>
								<li>Superficie Total: ${prop.superficieTotal}</li>
								<li>Superficie Cubierta: ${prop.superficieCubierta}</li>
							</ul>
							<h4>Caracteristicas</h4>
							<ul>
								<li>${prop.ambientes}<c:choose>
										<c:when test="${prop.ambientes > 1 }"> Ambientes </c:when>
										<c:otherwise> Ambiente</c:otherwise>
									</c:choose>
								<li>${prop.banios}<c:choose>
										<c:when test="${prop.banios > 1 }"> Baños </c:when>
										<c:otherwise> Baño</c:otherwise>
									</c:choose>
								<li>${prop.habitaciones}<c:choose>
										<c:when test="${prop.habitaciones == 1 }"> Habitacion </c:when>
										<c:otherwise> Habitaciones </c:otherwise>
									</c:choose>
								</li>
								<c:forEach var="c" items="${prop.caracteristicas}">
									<li>${c.descripcion}</li>
								</c:forEach>
								<!--  <li>Air Condition</li>
								<li>Belcony</li>
								<li>Gym</li>
								<li>Garden</li>
								<li>CCTV</li>
								<li>Children Play Ground</li>
								<li>Comunity Center</li>
								<li>Security System</li>-->
							</ul>
							<c:if test="${sessionScope.usuario != null}">
								<div class="row">
									<div class="aa-properties-social">
										<div class="col-md-1"></div>
										<c:if test="${!esFavorita}">
											<div class="col-md-4">
												<input type="button" id="favorita" name="favorita"
													value="Favorita" class="btn btn-primary">
											</div>
										</c:if>
										<c:if test="${esFavorita}">
											<div class="col-md-4">
												<input type="button" id="favorita" name="favorita"
													disabled="disabled" value="Ya se encuentra entre favoritas"
													class="btn btn-primary">
											</div>
										</c:if>
										<c:if test="${!estaReservada}">
											<div class="col-md-3">
												<input type="button" id="reservar" name="reservar"
													value="Reservar" class="btn btn-primary">
											</div>
										</c:if>
										<c:if test="${estaReservada}">
											<div class="col-md-3">
												<input type="button" id="reservar" name="reservar"
													disabled="disabled" value="Actualmente Reservada"
													class="btn btn-primary">
											</div>
										</c:if>
										<div class="col-md-4">
											<input type="button" id="masInfo" name="masInfo"
												value="Mas Info!" class="btn btn-primary">
										</div>
									</div>
								</div>
							</c:if>
							<!-- <h4>Property Map</h4>
							<iframe
								src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6851.201919469417!2d-86.11773906635584!3d33.47324776828677!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x888bdb60cc49c571%3A0x40451ca6baf275c7!2s36008+AL-77%2C+Talladega%2C+AL+35160%2C+USA!5e0!3m2!1sbn!2sbd!4v1460452919256"
								width="100%" height="450" frameborder="0" style="border: 0"
								allowfullscreen></iframe>
						</div> -->
							<!-- Properties social share -->
							<div class="aa-properties-social">
								<ul>
									<li>Share</li>
									<li><a href="#"><i class="fa fa-facebook"></i></a></li>
									<li><a href="#"><i class="fa fa-twitter"></i></a></li>
									<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
									<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
								</ul>
							</div>
							<!-- Nearby properties -->
							<div class="aa-nearby-properties">
								<div class="aa-title">
									<h2>Propiedades cercanas</h2>
									<span></span>
								</div>
								<div class="aa-nearby-properties-area">
									<div class="row">
										<form id="formCercanas"
											action="${pageContext.request.contextPath}/PropiedadesCercanasServlet"
											class="aa-sort-form" method="post">
											<input id="propSelCercana" name="propSelCercana" type="hidden" value="0">
											<c:forEach var="p" items="${propCercanas}">
												<div class="col-md-6">
													<article class="aa-properties-item"> <a href="#"
														class="aa-properties-item-img"> <img
														src="img/item/1.jpg" alt="img">
													</a>
													<div class="aa-tag for-sale">
														${p.tipoDeOperacion.descripcion}</div>
													<div class="aa-properties-item-content">
														<div class="aa-properties-info">
															<span>${p.ambientes} Amb.</span> <span>${p.habitaciones}
																Hab.</span> <span>${p.banios} Baño<c:if
																	test="${p.banios >1}">s</c:if></span> <span>Sup.
																Total:${p.superficieTotal} </span>
														</div>
														<div class="aa-properties-about">
															<h4>
																<a>${p.direccion} - ${p.ciudad.ciudad}</a>
															</h4>
															<!-- <p style="margin-right:16 px" >${p.desc}</p> -->
														</div>
														<div class="aa-properties-detial">
															<span class="aa-price"> $${p.precio} </span>
														</div>
														<div class="aa-properties-detial">
															<input type="submit" class="form-control btn btn-info"
																name="action" value="Detalles"
																onclick="return setCercanaId(${p.id})" />
														</div>
													</div>
													</article>
												</div>

												<!-- <article class="aa-properties-item"> <a
												class="aa-properties-item-img" href="#"> <img alt="img"
												src="img/item/1.jpg">
											</a>
											<div class="aa-tag for-sale">For Sale</div>
											<div class="aa-properties-item-content">
												<div class="aa-properties-info">
													<span>5 Rooms</span> <span>2 Beds</span> <span>3
														Baths</span> <span>1100 SQ FT</span>
												</div>
												<div class="aa-properties-about">
													<h3>
														<a href="#">Appartment Title</a>
													</h3>
													<p>Lorem ipsum dolor sit amet, consectetur adipisicing
														elit. Enim molestiae vero ducimus quibusdam odit vitae.</p>
												</div>
												<div class="aa-properties-detial">
													<span class="aa-price"> $35000 </span> <a
														class="aa-secondary-btn" href="#">View Details</a>
												</div>
											</div>
											</article> -->
											</c:forEach>
										</form>
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- / Properties  -->

	<!-- Footer -->
	<footer id="aa-footer">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="aa-footer-area">
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="aa-footer-left">
								<p>
									Designed by <a rel="nofollow" href="http://www.markups.io/">MarkUps.io</a>
								</p>
							</div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="aa-footer-middle">
								<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
									class="fa fa-twitter"></i></a> <a href="#"><i
									class="fa fa-google-plus"></i></a> <a href="#"><i
									class="fa fa-youtube"></i></a>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-xs-12">
							<div class="aa-footer-right">
								<a href="#">Home</a> <a href="#">Support</a> <a href="#">License</a>
								<a href="#">FAQ</a> <a href="#">Privacy & Term</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</footer>
	<!-- / Footer -->

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
		function setId(id) {
			console.log(id);
			$('#propSel').val(id);
		}

		function setCercanaId(id) {
			console.log(id);
			$('#propSelCercana').val(id);
		}
		
		
		$(document)
				.ready(
						function() {
							$('#favorita')
									.click(
											function() {
												console
												console.log($("#propId").val())
												$
														.ajax({
															url : 'AccionesEnPropiedadesServlet',
															data : {
																propId : $(
																		"#propId")
																		.val(),
																accion : "Favorita"
															},
															success : function(
																	responseText) {
																if (responseText == "OK") {
																	$(
																			'#favorita')
																			.prop(
																					"disabled",
																					true);
																	$(
																			'#favorita')
																			.val(
																					"Ya se encuentra entre favoritas");
																	alert("La propiedad ha sido agregada a favoritas!");
																} else {
																	alert(responseText)
																}
															}
														});
											});
						});

		$(document)
				.ready(
						function() {
							$('#reservar')
									.click(
											function() {
												console
												console.log($("#propId").val())
												$
														.ajax({
															url : 'AccionesEnPropiedadesServlet',
															data : {
																propId : $(
																		"#propId")
																		.val(),
																accion : "Reservar"
															},
															success : function(
																	responseText) {
																if (responseText == "OK") {
																	$(
																			'#reservar')
																			.prop(
																					"disabled",
																					true);
																	$(
																			'#reservar')
																			.val(
																					"Actualmente Reservada");
																	alert("La propiedad se ha reservado. Esta reserva es temporal, y de no efectuarse el pago del adelanto perdera validez.");
																} else {
																	alert(responseText)
																}
															}
														});
											});
						});

		$(document)
				.ready(
						function() {
							$('#masInfo')
									.click(
											function() {
												console
												console.log($("#propId").val())
												$
														.ajax({
															url : 'AccionesEnPropiedadesServlet',
															data : {
																propId : $(
																		"#propId")
																		.val(),
																accion : "MasInfo"
															},
															success : function(
																	responseText) {
																if (responseText == "OK") {
																	alert("La inmobiliaria se comunicara con usted, para acercarle mas informacion.");
																}
															}
														});
											});
						});
	</script>

</body>
</html>