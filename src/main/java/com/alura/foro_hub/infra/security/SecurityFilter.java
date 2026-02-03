package com.alura.foro_hub.infra.security;

//import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//public class SecurityFilter implements Filter {
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("FILTRO LLAMADO!");
        var tokenJWT = recuperarToken(request);

        // --> descomentariar la linea siguiente para ver el token:
        //System.out.println(tokenJWT);

        // --> comentariar lineas siguientes para ver el token:
        if(tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            //System.out.println(subject);
            var usuario = repository.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Usuario logeado");
        }

        //si no se usa filterChain no continua con la cadena de filtros y se bloquea !!
        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader!=null){
            return authorizationHeader.replace("Bearer ", "");
        }
        //throw new RuntimeException(("Token JWT no enviado en el encabezado de Authorization"));
        return null;
    }
}
