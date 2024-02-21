package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Conductor;
import com.ipfs.web.entidades.Siniestro;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConductorRepositorio extends JpaRepository<Conductor, String> {
    @Query("SELECT c FROM Conductor c WHERE"
            + " CONCAT(c.idConductor, c.genero, c.titular, c.apellidoNombre, c.dni, c.profesion, c.tel, c.domicilio, c.cp, c.localidad, c.provincia, c.estadoCivil, c.fechaNacimiento, c.testAlcoholemia, c.nroRegistro, c.categoria, c.Expedido, c.vencimiento)"
            + " LIKE %?1%")
    public List<Conductor> findAll(String palabraClave);
}
