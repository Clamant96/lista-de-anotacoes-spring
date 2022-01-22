package br.com.helpconnect.minhaLista.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.helpconnect.minhaLista.model.Usuario;
import br.com.helpconnect.minhaLista.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Usuario> cliente = usuarioRepository.findByUsername(username);
		
		cliente.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
		
		return cliente.map(UserDetailsImplementation::new).get();
	}
	
}
