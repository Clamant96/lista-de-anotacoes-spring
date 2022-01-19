package br.com.helpconnect.minhaLista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.helpconnect.minhaLista.model.Lista;

@Repository
public interface ListaRespository extends JpaRepository<Lista, Long> {

}
