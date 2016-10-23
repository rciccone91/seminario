package app;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Cilindrada;
import edu.utn.seminario.motosnorte.domain.Rol;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.service.UsuarioService;

public class App {

	public static void main(String[] args) {
		try {
			DataLayer data = new DataLayer();
			Cilindrada cilindrada = new Cilindrada();
			cilindrada.setCilindrada("51 - 125 cc");
			Usuario usuario = new Usuario();
			usuario.setActivo(true);
			usuario.setContrasenia("123456");
			usuario.setLegajo(34);
			usuario.setUsuario("rciccone");
			usuario.setRol(1);

			data.guardar(usuario);

//			UsuarioService service = new UsuarioService();
//			Usuario user = (Usuario)service.login("rciccone", "123456");
//			System.out.println(user.toString());

			data.guardar(cilindrada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
