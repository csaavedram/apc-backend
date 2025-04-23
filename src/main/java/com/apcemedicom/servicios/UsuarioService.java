package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Usuario;
import com.apcemedicom.modelo.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);
    Set<Usuario> obtenerUsuarios();

    public void eliminarUsuario(Long usuarioId);

    public Usuario obtenerUsuarioPorNumeroDocumento(String numeroDocumento);
}
