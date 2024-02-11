package com.ipfs.web.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipfs.web.entidades.Vehiculo;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.repositorios.VehiculoRepositorio;
import java.util.ArrayList;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VehiculoServicio {

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearVehiculo(/*MultipartFile archivo*/String aseguradora, String dominio, String marca, String modelo, String tipo, String color, String año, String dañosMateriales, String lesiones, String muerte) throws MiException {

        validar(aseguradora, dominio, marca, modelo, tipo, color, año, dañosMateriales, lesiones, muerte);

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setAseguradora(aseguradora);
        vehiculo.setAño(año);
        vehiculo.setColor(color);
        vehiculo.setDominio(dominio);
        vehiculo.setDañosMateriales(dañosMateriales);
        vehiculo.setLesiones(lesiones);
        vehiculo.setMuerte(muerte);
        vehiculo.setTipo(tipo);
        vehiculo.setModelo(modelo);
        vehiculo.setMarca(marca);

        /*oferta.setAltaOferta(new Date());*/

 /*Imagen imagen = imagenServicio.guardar(archivo);*/

 /*oferta.setImagen(imagen);*/
        vehiculoRepositorio.save(vehiculo);
    }

    public List<Vehiculo> listarVehiculos() {

        List<Vehiculo> vehiculos = new ArrayList();

        vehiculos = vehiculoRepositorio.findAll(); //encuentra todo (findAll)

        return vehiculos;
    }
    //FUNCIONALIDAD PARA FILTROS DE OFERTAS (busqueda)

    public List<Vehiculo> listAll(String palabraClave) {
        if (palabraClave != null) {
            return vehiculoRepositorio.findAll(palabraClave);
        }

        return vehiculoRepositorio.findAll();
    }

    @Transactional //se pasa el idOferta porq se necesita en modificarOferta
    public void modificarVehiculo(/*MultipartFile archivo*/String idVehiculo, String aseguradora, String dominio, String marca, String modelo, String tipo, String color, String año, String dañosMateriales, String lesiones, String muerte) throws MiException {

        validar(aseguradora, dominio, marca, modelo, tipo, color, año, dañosMateriales, lesiones, muerte);

        Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(idVehiculo);
        if (respuesta.isPresent()) {
            Vehiculo vehiculo = respuesta.get();
            vehiculo.setAseguradora(aseguradora);
            vehiculo.setAño(año);
            vehiculo.setColor(color);
            vehiculo.setDominio(dominio);
            vehiculo.setDañosMateriales(dañosMateriales);
            vehiculo.setLesiones(lesiones);
            vehiculo.setMuerte(muerte);
            vehiculo.setTipo(tipo);
            vehiculo.setModelo(modelo);
            vehiculo.setMarca(marca);

            /*String idImagen = null;  //se le asigna null ??

            if (oferta.getImagen() != null) {
                idImagen = oferta.getImagen().getIdImagen();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            oferta.setImagen(imagen);*/
            vehiculoRepositorio.save(vehiculo);
        }
    }

    public Vehiculo getOne(String idOferta) {
        return vehiculoRepositorio.getOne(idOferta); //conseguir uno
    }

    @Transactional
    public void borrarPorId(String idVehiculo) { //eliminar por num de id
        vehiculoRepositorio.deleteById(idVehiculo);
    }

    private void validar(/*String idOferta, MultipartFile archivo, */String aseguradora, String dominio, String marca, String modelo, String tipo, String color, String año, String dañosMateriales, String lesiones, String muerte) throws MiException {
     
        if (aseguradora.isEmpty() || aseguradora == null) {
            throw new MiException("la aseguradora no puede ser nulo o estar vacio");
        }

        if (dominio.isEmpty() || dominio == null) {
            throw new MiException("el dominio no puede ser nulo o estar vacio");
        }
        if (marca == null) {
            throw new MiException("marca no puede ser nulo");
        }
        if (marca.isEmpty() || marca == null) {
            throw new MiException("la marca no puede ser nulo o estar vacio");
        }
        if (modelo.isEmpty() || modelo == null) {
            throw new MiException("el modelo no puede ser nulo o estar vacio");
        }
        if (tipo.isEmpty() || tipo == null) {
            throw new MiException("el tipo no puede ser nulo o estar vacio");
        }
        if (año.isEmpty() || año == null) {
            throw new MiException("el año no puede ser nulo o estar vacio");
        }
        if (muerte.isEmpty() || muerte == null) {
            throw new MiException("muerte no puede ser nulo o estar vacio");
        }
        if (color.isEmpty() || color == null) {
            throw new MiException("el color no puede ser nulo o estar vacio");
        }
        if (lesiones.isEmpty() || lesiones == null) {
            throw new MiException("lesiones no puede ser nulo o estar vacio");
        }
        if (dañosMateriales.isEmpty() || dañosMateriales == null) {
            throw new MiException("dañosMateriales no puede ser nulo o estar vacio");
        }
    }
}
