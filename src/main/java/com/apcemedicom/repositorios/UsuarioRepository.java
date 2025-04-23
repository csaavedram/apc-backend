package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsername(String username);

    public Usuario findByNumeroDocumento(String numeroDocumento);

}
