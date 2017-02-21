<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CM Inmobiliaria</title>

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
		<div class="row">
			<div class="col-md-12">
				<div class="aa-header-area">
					<div class="row"></div>
					<div class="row"></div>
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
				<li><a href="${pageContext.request.contextPath}/IndexServlet">Home</a></li>
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
				<li class="active"><a href="${pageContext.request.contextPath}/FavoritasServlet">Favoritas</a></li>
				<li><a href="${pageContext.request.contextPath}/ReservadasServlet">Reservadas</a></li>
				</c:if>
				<li><a href="contact.html">Contacto</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav> </section>
	<!-- End menu section -->

	<!-- Start Proerty header  -->

	<section id="aa-property-header">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="aa-property-header-inner">
					<h2>Propiedades Reservadas</h2>
					<!-- <ol class="breadcrumb">
						<li><a href="#">HOME</a></li>
						<li class="active">PROPERTIES</li>
					</ol> -->
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- End Proerty header  -->

	<!-- <form class="aa-sort-form" method="post"> -->
	<!-- Start Properties  -->
	<section id="aa-properties">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="aa-properties-content">
					<!-- start properties content head -->
					<div class="aa-properties-content-head">
						<!-- <div class="aa-properties-content-head-left">
							<form action="" class="aa-sort-form">
								<label for="">Sort by</label> <select name="">
									<option value="1" selected="Default">Default</option>
									<option value="2">Name</option>
									<option value="3">Price</option>
									<option value="4">Date</option>
								</select>
							</form>
							<form action="" class="aa-show-form">
								<label for="">Show</label> <select name="">
									<option value="1" selected="12">6</option>
									<option value="2">12</option>
									<option value="3">24</option>
								</select>
							</form>
						</div> 
						<div class="aa-properties-content-head-right">
							<a id="aa-grid-properties" href="#"><span class="fa fa-th"></span></a>
							<a id="aa-list-properties" href="#"><span class="fa fa-list"></span></a>
						</div>-->
						<div class="aa-title">
							<h2>Favoritas</h2>
							<span></span>
							<p>Estas son las propiedades marcadas como favoritas</p>
						</div>
					</div>
					<!-- Start properties content body -->

					<div class="aa-properties-content-body">
						<div class="row">
							<form id="formPropiedades"
								action="${pageContext.request.contextPath}/FavoritasServlet"
								class="aa-sort-form" method="post">
								<input id="propSel" name="propSel" type="hidden" value="0">
								<c:forEach var="p" items="${resultados}">
									<div class="col-md-4">
										<article class="aa-properties-item"> <a href="#"
											class="aa-properties-item-img"> <img src="img/item/1.jpg"
											alt="img">
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
										</div>
										<div class="aa-properties-detial">
											<input type="submit" class="form-control btn btn-info"
												name="action" value="Detalles"
												onclick="return setId(${p.id})" />
										</div>
									
									</article>
									</div>
								</c:forEach>
							</form>
						</div>

					</div>
					<!-- Start properties content bottom 
					<div class="aa-properties-content-bottom">
						<form id="formPaginado" name="" class="aa-sort-form" method="post"
							action="ResultadosPropiedadServlet">
							<nav> <input type="hidden" id="numPagina" name="numPagina"
								value="1">
							<ul class="pagination">
								<c:forEach var="p" items="${props}">
									<li id="pagina" value="numPagina">
										<input id="pagSeleccionada" type="submit" 
										onclick="return setPagina()" class="btn-link">
									</li>
								</c:forEach>
							</ul>

							</nav>
						</form>
					</div>-->
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
								<a href="#">FAQ</a> <a href="#">Privacy &amp; Term</a>
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
	<script>
		function setId(id) {
			$('#propSel').val(id);
			console.log(document.getElementById("propSel").value);
		}
	</script>

	<div id="portfolio-popup">
		<div class="portfolio-popup-area">
			<div class="portfolio-popup-inner"></div>
		</div>
	</div>
</body>
</html>