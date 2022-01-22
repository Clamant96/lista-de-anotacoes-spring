package br.com.helpconnect.minhaLista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.minhaLista.model.Categoria;

@Repository
public interface CategoriaRespository extends JpaRepository<Categoria, Long> {
	
	//public List<Categoria> findAllByUsuarioContainingIgnoreCase(long id);
	
}
