package com.apcemedicom;

import com.apcemedicom.exepciones.UsuarioFounException;
import com.apcemedicom.modelo.Rol;
import com.apcemedicom.modelo.Usuario;
import com.apcemedicom.modelo.UsuarioRol;
import com.apcemedicom.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ApcBackendApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApcBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

			try {
				Usuario usuario = new Usuario();

				usuario.setNombre("Christian");
				usuario.setApellido("Saavedra");
				usuario.setUsername("admin");
				usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
				usuario.setEmail("chris@gmail.com");
				usuario.setTelefono("999333444");
				usuario.setPerfil("foto.png");

				Rol rol = new Rol();
				rol.setRolId(1L);
				rol.setRolNombre("ADMIN");

				Set<UsuarioRol> usuariosRoles = new HashSet<>();
				UsuarioRol usuarioRol = new UsuarioRol();
				usuarioRol.setRol(rol);
				usuarioRol.setUsuario(usuario);
				usuariosRoles.add(usuarioRol);

				Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuariosRoles);
				System.out.println(usuarioGuardado.getUsername());

			}catch (UsuarioFounException exception){
				exception.printStackTrace();
			}
	}
}
