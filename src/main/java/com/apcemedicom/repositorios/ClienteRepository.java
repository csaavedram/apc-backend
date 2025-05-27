package com.apcemedicom.repositorios;
import com.apcemedicom.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
