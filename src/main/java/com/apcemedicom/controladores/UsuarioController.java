package com.apcemedicom.controladores;

import com.apcemedicom.servicios.UsuarioService;
import com.apcemedicom.modelo.Rol;
import com.apcemedicom.modelo.Usuario;
import com.apcemedicom.modelo.UsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");

        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }

    @GetMapping("/")
    public ResponseEntity<?> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @GetMapping("/ruc/{ruc}")
    public Usuario obtenerUsuarioPorRuc(@PathVariable("ruc") String ruc) {
        return usuarioService.obtenerUsuarioPorRuc(ruc);
    }

    @GetMapping("/razonSocial/{razonSocial}")
    public Usuario obtenerUsuarioPorRazonSocial(@PathVariable("razonSocial") String razonSocial) {
        return usuarioService.obtenerUsuarioPorRazonSocial(razonSocial);
    }    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

    @GetMapping("/verificar-disponibilidad/{username}")
    public ResponseEntity<?> verificarDisponibilidadUsername(@PathVariable("username") String username) {
        try {
            Usuario usuario = usuarioService.obtenerUsuario(username);
            
            java.util.Map<String, Object> response = new java.util.HashMap<>();
            if (usuario != null) {
                response.put("disponible", false);
                response.put("mensaje", "El username ya está registrado");
            } else {
                response.put("disponible", true);
                response.put("mensaje", "Username disponible");
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            java.util.Map<String, Object> response = new java.util.HashMap<>();
            response.put("disponible", true);
            response.put("mensaje", "Username disponible");
            return ResponseEntity.ok(response);
        }
    }

}
