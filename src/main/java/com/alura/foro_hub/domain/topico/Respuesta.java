package com.alura.foro_hub.domain.topico;

import com.alura.foro_hub.domain.topico.dto.DatosActualizacionRespuesta;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroRespuesta;
import com.alura.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas",uniqueConstraints =
@UniqueConstraint(columnNames = {"mensaje","topico_id"}))
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @Column(nullable = false)
    private LocalDateTime fechaDeCreacion;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusRespuesta status = StatusRespuesta.PROCESADA;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Respuesta(DatosRegistroRespuesta datos, Topico topico, Usuario usuario) {
        this.id = null;
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.fechaDeCreacion = LocalDateTime.now();
        this.status = StatusRespuesta.PROCESADA;
        this.usuario = usuario;
    }

    public void actualizarInformaciones(@Valid DatosActualizacionRespuesta datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

    public void procesar() {
        this.status = StatusRespuesta.PROCESADA;
    }
}