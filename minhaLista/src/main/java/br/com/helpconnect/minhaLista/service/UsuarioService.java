package br.com.helpconnect.minhaLista.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpconnect.minhaLista.model.Usuario;
import br.com.helpconnect.minhaLista.model.UsuarioLogin;
import br.com.helpconnect.minhaLista.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/* CADASTRAR USUARIO NO SISTEMA */
	public Optional<Usuario> CadastrarCliente(Usuario usuario) {	

		if(usuarioRepository.findByUsername(usuario.getUsername()).isPresent() && usuario.getId() == 0) {
			return null;
			
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		/* REGISTRA O USUARIO NA BASE DE DADOS */
		usuarioRepository.save(usuario);

		return Optional.of(usuarioRepository.save(usuario));

	}
	
	/* LOGA USUARIO NO SISTEMA */
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> usuarioLogin) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByUsername(usuarioLogin.get().getUsername());
		
		/* CASO TENHA ALGUM VALOR DIGITADO, IREMOS COMPARAR OS DADOS QUE ESTAO CADASTRADOS NA BASE DE DADOS COM O QUE O USUARIO ACABOU DE DIGITAR */
		if (usuario.isPresent()) {
			/* COMPARA O QUE FOI DIGITADO NO BODY COM O QUE ESTA NO BANCO DE DADOS REFERENTE AQUELE DETERMINADO USUARIO */
			if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				
				/* CRIA UMA STRING COM O 'NOME_USUARIO:SENHA' */
				String auth = usuarioLogin.get().getUsername() + ":" + usuarioLogin.get().getSenha();
				
				/* CRIA UM ARRAY DE BYTE, QUE RECEBE A STRING GERADA ACIMA E FORMATA NO PADRAO 'US-ASCII' */
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				
				/* GERA O TOKEN PARA ACESSO DE USUARIO POR MEIO DO ARRAY DE BY GERADO */
				String authHeader = "Basic " + new String(encodedAuth);
				
				/* INSERE O TOKEN GERADO DENTRO DE NOSSO ATRIBUTO TOKEN */
				usuarioLogin.get().setToken(authHeader);				
				usuarioLogin.get().setUsername(usuario.get().getUsername());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setCategoria(usuario.get().getCategoria());
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setAvatar(usuario.get().getAvatar());
				
				return usuarioLogin;

			}
		}
		
		return null;
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(usuarioRepository.save(usuario));
	}

}
