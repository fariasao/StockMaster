package br.com.stockmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stockmaster.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{
    
}
