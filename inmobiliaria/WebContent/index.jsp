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
		<div class="row">
			<div class="col-md-12">
				<div class="aa-header-area">

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<span>.................................................................................................................................</span>
						</div>
					</div>
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
								<form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
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
					data-toggle="dropdown" href="properties.html">Propiedades <span
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
								<li><a href="blog-single.html">Baja</a></li>
								<li><a href="blog-single.html">Modificación</a></li>
							</ul></li>
					</c:if>
				</c:if>
				<li><a href="contact.html">Contacto</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav> </section>
	<!-- End menu section -->
	<form class="form-group" method="post">
		<!-- Start slider  -->
		<section id="aa-slider">
		<div class="aa-slider-area">
			<div class="aa-top-slider">
				<div class="aa-top-slider-single">
					<img src="img/slider/3.jpg" alt="img">
					<div class="aa-top-slider-content">
						<span class="aa-top-slider-catg">Duplex</span>
						<h2 class="aa-top-slider-title">1560 Square Feet</h2>
						<p class="aa-top-slider-location">
							<i class="fa fa-map-marker"></i>South Beach, Miami (USA)
						</p>
						<span class="aa-top-slider-off">30% OFF</span>
						<p class="aa-top-slider-price">$460,000</p>
						<a href="#" class="aa-top-slider-btn">Read More <span
							class="fa fa-angle-double-right"></span></a>
					</div>
				</div>
				<div class="aa-top-slider-single">
					<img src="img/slider/2.jpg" alt="img">
					<div class="aa-top-slider-content">
						<span class="aa-top-slider-catg">Duplex</span>
						<h2 class="aa-top-slider-title">1560 Square Feet</h2>
						<p class="aa-top-slider-location">
							<i class="fa fa-map-marker"></i>South Beach, Miami (USA)
						</p>
						<span class="aa-top-slider-off">30% OFF</span>
						<p class="aa-top-slider-price">$460,000</p>
						<a href="#" class="aa-top-slider-btn">Read More <span
							class="fa fa-angle-double-right"></span></a>
					</div>
				</div>
				<div class="aa-top-slider-single">
					<img src="img/slider/1.jpg" alt="img">
					<div class="aa-top-slider-content">
						<span class="aa-top-slider-catg">Duplex</span>
						<h2 class="aa-top-slider-title">1560 Square Feet</h2>
						<p class="aa-top-slider-location">
							<i class="fa fa-map-marker"></i>South Beach, Miami (USA)
						</p>
						<span class="aa-top-slider-off">30% OFF</span>
						<p class="aa-top-slider-price">$460,000</p>
						<a href="#" class="aa-top-slider-btn">Read More <span
							class="fa fa-angle-double-right"></span></a>
					</div>
				</div>
				<div class="aa-top-slider-single">
					<img src="img/slider/5.jpg" alt="img">
					<div class="aa-top-slider-content">
						<span class="aa-top-slider-catg">Duplex</span>
						<h2 class="aa-top-slider-title">1560 Square Feet</h2>
						<p class="aa-top-slider-location">
							<i class="fa fa-map-marker"></i>South Beach, Miami (USA)
						</p>
						<span class="aa-top-slider-off">30% OFF</span>
						<p class="aa-top-slider-price">$460,000</p>
						<a href="#" class="aa-top-slider-btn">Read More <span
							class="fa fa-angle-double-right"></span></a>
					</div>
				</div>
				<div class="aa-top-slider-single">
					<img src="img/slider/4.jpg" alt="img">
					<div class="aa-top-slider-content">
						<span class="aa-top-slider-catg">Duplex</span>
						<h2 class="aa-top-slider-title">1560 Square Feet</h2>
						<p class="aa-top-slider-location">
							<i class="fa fa-map-marker"></i>South Beach, Miami (USA)
						</p>
						<span class="aa-top-slider-off">30% OFF</span>
						<p class="aa-top-slider-price">$460,000</p>
						<a href="#" class="aa-top-slider-btn">Read More <span
							class="fa fa-angle-double-right"></span></a>
					</div>
				</div>
				<div class="aa-top-slider-single">
					<img src="img/slider/6.jpg" alt="img">
					<div class="aa-top-slider-content">
						<span class="aa-top-slider-catg">Duplex</span>
						<h2 class="aa-top-slider-title">1560 Square Feet</h2>
						<p class="aa-top-slider-location">
							<i class="fa fa-map-marker"></i>South Beach, Miami (USA)
						</p>
						<span class="aa-top-slider-off">30% OFF</span>
						<p class="aa-top-slider-price">$460,000</p>
						<a href="#" class="aa-top-slider-btn">Read More <span
							class="fa fa-angle-double-right"></span></a>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- End slider  -->

		<!-- Advance Search -->
		<section id="aa-advance-search">
		<div class="container">
			<div class="aa-advance-search-area">
				<div class="form">
					<div class="aa-advance-search-top">
						<div class="row">
							<div class="col-md-3">
								<div class="aa-single-advance-search">
									<input id="inputCiudad" name="inputCiudad" type="text"
										placeholder="Ciudad">
								</div>
							</div>
							<div class="col-md-3">
								<div class="aa-single-advance-search">
									<select id="inputTipoProp" name="inputTipoProp">
										<option value="0" selected>Tipo de Propiedad</option>
										<c:forEach var="tipo" items="${ tipoDePropiedades }">
											<option value="${ tipo.id }">${ tipo.descripcion }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-2">
								<div class="aa-single-advance-search">
									<select id="inputAmbientes" name="inputAmbientes">
										<option value="0" selected>Ambientes</option>
										<option value="1">Monoambiente</option>
										<option value="2">2 Ambientes</option>
										<option value="3">3 Ambientes</option>
										<option value="4">4 Ambientes</option>
										<option value="5">5 o más ambientes</option>
									</select>
								</div>
							</div>
							<!-- <div class="col-md-2">
                 <div class="aa-single-advance-search">
                  <select>
                    <option value="0" selected>Type</option>
                    <option value="1">Flat</option>
                    <option value="2">Land</option>
                    <option value="3">Plot</option>
                    <option value="4">Commercial</option>
                  </select>
              </div>
              </div> -->
							<div class="col-md-2">
								<div class="aa-single-advance-search">
									<select id="inputOperacion" name="inputOperacion">
										<option value="0" selected>Operación</option>
										<option value="1">Venta</option>
										<option value="2">Alquiler</option>
									</select>
								</div>
							</div>
							<div class="col-md-2">
								<div class="aa-single-advance-search">
									<input class="aa-search-btn" type="submit" name="action"
										value="Buscar" onclick="return validarBuscar();">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- / Advance Search -->

		<!-- Latest property -->
		<section id="aa-latest-property">
		<div class="container">
			<input id="propSel" name="propSel" type="hidden" value="0">
			<div class="aa-latest-property-area">
				<div class="aa-title">
					<h2>Últimas propiedades</h2>
					<span></span>
					<p>Estas son las ultimas propiedades que ingresaron a nuestra
						inmobiliaria</p>
				</div>
				<div class="aa-latest-properties-content">
					<div class="row">
						<c:forEach var="p" items="${propiedades}">
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
											Hab.</span> <span>${p.banios} Baño<c:if test="${p.banios >1}">s</c:if></span>
										<span>Sup. Total:${p.superficieTotal} </span>
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
											onclick="return setId(${p.id})" />
										<!--  <a class="aa-secondary-btn" href="#">View Details</a>-->
										<!-- <a class="aa-secondary-btn" href="#">View Details</a> -->
									</div>
								</div>
								</article>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- / Latest property -->

		<!-- About us -->
		<section id="aa-about-us">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="aa-about-us-area">
						<div class="row">
							<div class="col-md-5">
								<div class="aa-about-us-left">
									<img src="img/about-us.png" alt="image">
								</div>
							</div>
							<div class="col-md-7">
								<div class="aa-about-us-right">
									<div class="aa-title">
										<h2>Conózcanos</h2>
										<span></span>
									</div>
									<p>CM Imobiliaria - Experiencia y profesionalismo</p>
									<ul>
										<li>Más de 30 años de experiencia en negocios
											inmobiliarios.</li>
										<li>Nos encontramos ubicados en Zona Norte, con multiples
											sucursales</li>
										<li>Poseemos propiedades en venta y alquiler a lo largo
											de la Zona Norte del Gran Buenos Aires y Capital Fededal.</li>
										<li>Atención personalizada, enfoncándonos siempre en las
											necesidades del cliente y sus preferencias.</li>
										<li>Atención a empresas, llevando adelante proyectos de
											construcción.</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- / About us -->

		<!-- Service section -->
		<section id="aa-service">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="aa-service-area">
						<div class="aa-title">
							<h2>Nuestros servicios</h2>
							<span></span>
							<p>Conozca las ventajas de trabajar con nosotros</p>
						</div>
						<!-- service content -->
						<div class="aa-service-content">
							<div class="row">
								<div class="col-md-3">
									<div class="aa-single-service">
										<div class="aa-service-icon">
											<span class="fa fa-home"></span>
										</div>
										<div class="aa-single-service-content">
											<h4>
												<a href="#">Venta de Propiedades</a>
											</h4>
											<p>Ofrecemos un amplio catalogo de propiedades en venta.</p>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="aa-single-service">
										<div class="aa-service-icon">
											<span class="fa fa-check"></span>
										</div>
										<div class="aa-single-service-content">
											<h4>
												<a href="#">Alquiler de Propiedades</a>
											</h4>
											<p>Gran cantidad de propiedades en alquiler en zonas centricas muy requeridas, y con propiedades nuevas entrando cada día</p>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="aa-single-service">
										<div class="aa-service-icon">
											<span class="fa fa-crosshairs"></span>
										</div>
										<div class="aa-single-service-content">
											<h4>
												<a href="#">Desarrollo de Propiedades</a>
											</h4>
											<p>Proveemos un servicio de desarrollo de proyectos de propiedades, junto a nuestros profesionales</p>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="aa-single-service">
										<div class="aa-service-icon">
											<span class="fa fa-bar-chart-o"></span>
										</div>
										<div class="aa-single-service-content">
											<h4>
												<a href="#">Análisis de Mercado</a>
											</h4>
											<p>Brindamos un análisis personalizado a cada cliente, para que el mismo 
											pueda llevarse la mejor experiencia al momento de cumplir el sueño de la casa propia</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
		<!-- / Service section -->


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
	</form>


	<!-- jQuery library -->
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
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
		if ($('#inputOperacion').find('option:selected').val() == '0') {
			alert("Seleccione el tipo de operación");
			return false;
		}else if ($('#inputTipoProp').find('option:selected').val() == '0'
				&& $('#inputAmbientes').find('option:selected').val() == '0' 
				&& document.getElementById("inputCiudad").value == ""){
			alert("Seleccione alguno de los criterios de búsqueda");
			return false;
		}

		return true;
	}
	
	function setId(id){
		console.log(id);
		$('#propSel').val(id);
	}
	
		function buildProperty(id, desc, direccion, ambientes, banios,
				superficieCubierta, precio, tipoDeOperacion, tipoDePropiedad,
				ciudad, superficieTotal, habitaciones, fechaDeConstruccion,
				latitud, longitud, imagenes, caracteristicas) {
			console.log('YAAAAAAY');
			console.log(id);
			console.log(desc);
			console.log(direccion);
			console.log(precio)
			console.log(tipoDeOperacion)
			console.log(caracteristicas)
			/*var property = {
					id : p.id,
					direccion : p.direccion,
					desc : p.desc,
				};
			
			console.log(property.direccion)
			console.log(property.desc)*/

			return true;
			// return true or false, depending on whether you want to allow the `href` property to follow through or not
		}
	</script>
</body>
</html>