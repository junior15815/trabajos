package pe.edu.upeu.asistencia.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "asis_asistenciapa")
public class Asistenciapa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "actividadId", nullable = false)
    private String actividadId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    @Basic(optional = false)
    @Column(name = "horai", nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime horaReg;
    @Size(max = 60)
    private String latituda;
    @Size(max = 60)
    private String longituda;

    @Size(max = 8)
    private String tipo;
    @Size(max = 2)
    @Column(name = "calificacion", nullable = false, length = 2)
    private String calificacion;
    @Column(name = "cui", nullable = true, length = 200)
    private String cui;
    @Column(name = "tipoCui", nullable = false, length = 200)
    private String tipoCui;
    @Column(name = "entsal", nullable = false, length = 2)
    private String entsal;
    @Size(max = 2)
    @Column(name = "offlinex", length = 2, nullable = false)
    private String offlinex;

    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"asistenciaxs", "inscritos", "subactasisxs", "materialesxs"})
    private Actividad actividadId;    
}
