package dh.backend.clinica;


import dh.backend.clinica.dao.impl.DaoOdontologo;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.service.OdontologoService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OdontologoServiceTest {

    static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService = new OdontologoService(new DaoOdontologo());

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./Database/odontologos;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
    @Test
    @DisplayName("Testear que un odontologo fue cargado correctamente")
    void caso1(){

        //Dado
        Odontologo odontologo = new Odontologo("HJ90","Paola","Vargas");
        //cuando
        Odontologo odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
        // entonces
        assertNotNull(odontologoDesdeDb);
    }

    @Test
    @DisplayName("Testear que se listen todos los odontologos")
    void caso2(){
        //DADO
        List<Odontologo> odontologos;
        //CUANDO
        odontologos = odontologoService.listarTodos();
        // entonces
        assertNotNull(odontologos);
    }


}