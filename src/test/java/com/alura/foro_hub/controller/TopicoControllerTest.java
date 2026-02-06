package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.topico.*;
import com.alura.foro_hub.domain.topico.dto.DatosDetalleTopico;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosRegistroTopico> datosRegistroTopicoJson;

    @Autowired
    private JacksonTester<DatosDetalleTopico> datosDetalleTopicoJson;

    @MockBean
    private TopicoRepository topicoRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private CursoRepository cursoRepository;

    @Test
    @DisplayName("Deberia devolver http 400 cuando la request no tenga datos")
    @WithMockUser
    void registrar_escenario1() throws Exception {
        var response = mvc.perform(post("/topicos"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia devolver http 201 cuando los datos son válidos")
    @WithMockUser
    void registrar_escenario2() throws Exception {
        var datosRegistro = new DatosRegistroTopico(
                "Bug inesperado",
                "la consulta está mal referenciada",
                1L,
                1L
        );




        var status = StatusTopico.ABIERTO;
        var fecha = LocalDateTime.now();
        var datosDetalle = new DatosDetalleTopico(
                null,
                datosRegistro.titulo(),
                datosRegistro.mensaje(),
                fecha,
                status,
                1L,
                1L
        );
        var jsonEsperado = datosDetalleTopicoJson.write(
                datosDetalle
        ).getJson();


        when(usuarioRepository.findById(any()))
                .thenReturn(Optional.of(new Usuario(1L, "usuario", "123456", "Antony Queen")));


        when(cursoRepository.findById(any()))
                .thenReturn(Optional.of(new Curso(1L, "Spring Boot")));


        when(topicoRepository.save(any())).thenReturn(new Topico(datosRegistro));
        var response = mvc.perform(post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistroTopicoJson.write(datosRegistro).getJson()))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);


    }

}