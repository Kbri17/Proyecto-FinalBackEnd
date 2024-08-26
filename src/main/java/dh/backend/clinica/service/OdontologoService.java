package dh.backend.clinica.service;

import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.model.Odontologo;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }
    public Odontologo buscarPorId(Integer id){
        return odontologoIDao.buscarPorId(id);
    }
    public List<Odontologo> buscarTodos() {
        return odontologoIDao.listaTodos();
    }
    public void modificarOdontologo(Odontologo odontologo){
        odontologoIDao.modificar(odontologo);
    }

    public void eliminarOdontologo(Integer id){
        odontologoIDao.eliminar(id);
    }

}
