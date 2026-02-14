package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.topico.*;
import com.alura.foro_hub.domain.topico.dto.DatosDetalleTopico;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroCurso;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroRespuesta;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro_hub.domain.usuario.PerfilUsuario;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import com.alura.foro_hub.domain.usuario.dto.DatosRegistroUsuario;
import com.alura.foro_hub.infra.security.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@SpringBootTest
@ActiveProfiles("test")
@WebMvcTest(TopicoController.class)
//@AutoConfigureMockMvc
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureJsonTesters
class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosRegistroTopico> datosRegistroTopicoJson;

    @Autowired
    private JacksonTester<DatosRegistroRespuesta> datosRegistroRespuestaJson;

    @Autowired
    private JacksonTester<DatosRegistroUsuario> datosRegistroUsuarioJson;

    @Autowired
    private JacksonTester<DatosRegistroCurso> datosRegistroCursoJson;

    //@Autowired
    //private JacksonTester<DatosDetalleTopico> datosDetalleTopicoJson;

    @MockBean
    private TopicoRepository topicoRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private CursoRepository cursoRepository;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private RespuestaRepository respuestaRepository;

    @Test
    @DisplayName("Deberia devolver http 400 cuando la request no tenga datos")
    @WithMockUser
    void registrar_escenario1() throws Exception {

        var response = mvc.perform(post("/topicos"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        var response = mvc.perform(post("/respuestas"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        var response = mvc.perform(post("/usuarios"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        var response = mvc.perform(post("/cursos"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deberia devolver http 201 cuando los datos son válidos")
    @WithMockUser
    void registrar_escenario2() throws Exception {

        var datosRegistroTopico = new DatosRegistroTopico(
                "Bug inesperado",
                "la consulta está mal referenciada",
                1L,
                1L
        );

        var datosRegistroRespuesta = new DatosRegistroRespuesta(
                "Revisa la información",
                1L,
                1L
        );

        var datosRegistroUsuario = new DatosRegistroUsuario(
                "usuario",
                "123456",
                "Antony Queen",
                PerfilUsuario.ALUMNO
        );

        var datosRegistroCurso = new DatosRegistroCurso(
                "Spring Boot"
        ):

        var usuarioEjemplo = new Usuario(
                1L,
                "usuario",
                "123456",
                "Antony Queen",
                PerfilUsuario.ALUMNO
        );

        var cursoEjemplo = new Curso(
                1L,
                "Spring Boot"
        );

        var entidadTopico = new Topico(datosRegistroTopico, usuarioEjemplo, cursoEjemplo);

        var entidadRespuesta = new Respuesta(datosRegistroRespuesta, entidadTopico, usuarioEjemplo);

        var entidadUsuario = new Usuario(datosRegistroUsuario);

        var entidadCurso = new Curso(datosRegistroCurso);

        //var status = StatusTopico.ACEPTADO;
        //var fecha = LocalDateTime.now();
        //var fechaString = "2026-02-13T01:37:23.701584";
        //var fecha = LocalDateTime.parse(fechaString);
        //var datosDetalle = new DatosDetalleTopico(
        //        null,
        //        datosRegistro.titulo(),
        //        datosRegistro.mensaje(),
        //        fecha,
        //        status,
        //        "Antony Queen",
        //        "Spring Boot"
        //);
        //var jsonEsperado = datosDetalleTopicoJson.write(
        //        datosDetalle
        //).getJson();


        when(usuarioRepository.findById(any()))
                .thenReturn(Optional.of(usuarioEjemplo));


        when(cursoRepository.findById(any()))
                .thenReturn(Optional.of(cursoEjemplo));


        when(topicoRepository.save(any())).thenReturn(entidadTopico);
        var response = mvc.perform(post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistroTopicoJson.write(datosRegistroTopico).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        //assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

        when(respuestaRepository.save(any())).thenReturn(entidadRespuesta);
        var response = mvc.perform(post("/respuestas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistroRespuestaJson.write(datosRegistroRespuesta).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        when(usuarioRepository.save(any())).thenReturn(entidadUsuario);
        var response = mvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistroUsuarioJson.write(datosRegistroUsuario).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        when(cursoRepository.save(any())).thenReturn(entidadCurso);
        var response = mvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistroCursoJson.write(datosRegistroCurso).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

}