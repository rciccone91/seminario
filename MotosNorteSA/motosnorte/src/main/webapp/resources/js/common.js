//GeolocalizarPropiedad.xhtml
function geocode() {
	console.log("direccion:" + document.getElementById('mainForm:direccion').value);
	console.log("nombreCiudad:" + document.getElementById('mainForm:nombreCiudad').value);
	var dir = (document.getElementById('mainForm:direccion').value).concat(", ");
	var ciu = document.getElementById('mainForm:nombreCiudad').value;
	var buscada = dir.concat(ciu);
	PF('geoGmap').geocode(buscada);
}


function mostrarPropiedad(){
	reverseGeocode();
	PF('propOP').show();
}

function reverseGeocode() {
	console.log("latitud:" + document.getElementById('mainForm:latitud').value);
	console.log("longitud:" + document.getElementById('mainForm:longitud').value);
    var lat = document.getElementById('mainForm:latitud').value,
        lng = document.getElementById('mainForm:longitud').value;

    PF('geoGmap').reverseGeocode(lat, lng);
}

// Geolocalización en ALTA

function geocodeAlta() {
	console.log("buscada");
	console.log(document.getElementById('mainForm:dir').value);
//	var geocoder = new google.maps.Geocoder();
	PF('geoGmap').geocode(document.getElementById('mainForm:dir').value);
//	geocoder.geocode(document.getElementById('mainForm:dir').value);
}

function validarGeoLoc(){
	var dir = document.getElementById('mainForm:direccion').value;
	var ciu = document.getElementById('mainForm:nombreCiudad').value;
//	var trimmed = dir.trim();
//	console.log(trimmed);
	
	if(dir && ciu){
		console.log(dir);
		console.log(ciu);
		geocodeAlta();
	}
}

function inhabilitarCiudad(){
	console.log("entre");
	document.getElementById("mainForm:ciudad").style.visibility = "hidden";
	document.getElementById("mainForm:ciudadLabl").style.visibility = "hidden";
}

function habilitarCiudad(){
	console.log("habilitaaaaaaaaar");
	var dir = document.getElementById("mainForm:direccion").value;
	if(dir.trim()){
		document.getElementById("mainForm:ciudad").style.visibility = "visible";
		document.getElementById("mainForm:ciudadLabl").style.visibility = "visible";
	}
	else{
		document.getElementById("mainForm:ciudad").style.visibility = "hidden";
		document.getElementById("mainForm:ciudadLabl").style.visibility = "hidden";
	}
		
}

function hideGmap(){
	document.getElementById("geoGmap").style.display = "none";
}
