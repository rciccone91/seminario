package edu.utn.seminario.motosnorte.webservices;

import javax.xml.ws.Endpoint;

public class MotosNorteWsEndpoint {

	   public static void main(String[] args) {
	      Endpoint
	            .publish("http://localhost:8088/MotosNorteWs", new MotosNorteWs());
	   }
}
