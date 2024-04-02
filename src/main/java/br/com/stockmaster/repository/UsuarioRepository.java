package br.com.stockmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.stockmaster.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
