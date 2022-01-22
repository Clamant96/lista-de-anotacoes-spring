package br.com.helpconnect.minhaLista.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpconnect.minhaLista.model.Usuario;
import br.com.helpconnect.minhaLista.model.UsuarioLogin;
import br.com.helpconnect.minhaLista.repository.ListaRespository;
import br.com.helpconnect.minhaLista.repository.UsuarioRepository;
import br.com.helpconnect.minhaLista.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ListaRespository listaRespository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAllByUsuarios() {
		
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findByIdUsuario(@PathVariable long id) {
		
		return usuarioRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/* PARA LOGARMOS NO SISITEMA TRABALHAMOS COM A CLASSE 'UserLogin' */
	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				/* CASO SEU USUARIO SEJA INVALIDO VOCE RECEBERA UM ERRO DE NAO AUTORIZADO */
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	/* CHAMA O METODO DE CADASTRAR USUARIOS QUE E RESPONSAVEL POR VERIFICAR SE O USUARIO INSERIDO JA SE ENCONTRA NA BASE DE DADOS, CODIFICAR A SENHA INSERIDA E SALVAR OS DADOS CADASTRADO NA BASE DE DADOS */
	@PostMapping("/cadastro")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		Optional<Usuario> user = usuarioService.CadastrarCliente(usuario);
		
		try {
			return ResponseEntity.ok(user.get());
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putCliente(@RequestBody Usuario usuario) {
		
		Optional<Usuario> user = usuarioService.atualizarUsuario(usuario);
		
		try {
			return ResponseEntity.ok(user.get());
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
			
		}
	}
	
	@DeleteMapping("/{id}")
	public void deletaCliente(@PathVariable long id) {
		
		usuarioRepository.deleteById(id);
		listaRespository.deleteById(id);
	}
	
}
