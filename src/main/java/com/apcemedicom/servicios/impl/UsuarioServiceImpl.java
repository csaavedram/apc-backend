package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Usuario;
import com.apcemedicom.modelo.UsuarioRol;
import com.apcemedicom.repositorios.RolRepository;
import com.apcemedicom.repositorios.UsuarioRepository;
import com.apcemedicom.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario obtenerUsuarioPorRuc(String ruc) {
        return usuarioRepository.findByRuc(ruc);
    }

    @Override
    public Usuario obtenerUsuarioPorRazonSocial(String razonSocial) {
        return usuarioRepository.findByRazonSocial(razonSocial);
    }

    @Override
    public Set<Usuario> obtenerUsuarios() {
        return new LinkedHashSet<>(usuarioRepository.findAll());
    }


    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

}