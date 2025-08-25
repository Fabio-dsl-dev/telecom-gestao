package com.navida.telecom_gestao.config;

import com.navida.telecom_gestao.model.Perfil;
import com.navida.telecom_gestao.model.Usuario;
import com.navida.telecom_gestao.repository.PerfilRepository;
import com.navida.telecom_gestao.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (perfilRepository.count() == 0) {
                Perfil vendedor = perfilRepository.save(new Perfil(null, "VENDEDOR"));
                Perfil tecnico = perfilRepository.save(new Perfil(null, "TECNICO"));
                Perfil gerente = perfilRepository.save(new Perfil(null, "GERENTE"));

                Usuario u1 = new Usuario();
                u1.setUsername("vendedor");
                u1.setPassword(passwordEncoder.encode("vendedor"));
                u1.setPerfis(Set.of(vendedor));
                usuarioRepository.save(u1);

                Usuario u2 = new Usuario();
                u2.setUsername("tecnico");
                u2.setPassword(passwordEncoder.encode("tecnico"));
                u2.setPerfis(Set.of(tecnico));
                usuarioRepository.save(u2);

                Usuario u3 = new Usuario();
                u3.setUsername("gerente");
                u3.setPassword(passwordEncoder.encode("gerente"));
                u3.setPerfis(Set.of(gerente));
                usuarioRepository.save(u3);
            }
        };
    }
}
