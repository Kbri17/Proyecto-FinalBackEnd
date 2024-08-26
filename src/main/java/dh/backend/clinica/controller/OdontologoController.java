package dh.backend.clinica.controller;

import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.service.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService =odontologoService;
    }

    /*
    @GetMapping("/buscarOdontologo")
    public String buscarPaciente(Model model, @RequestParam Integer idOdontologo){
        Odontologo odontologo = odontologoService.buscarPorId(idOdontologo);

        model.addAttribute("nombreOdontologo", odontologo.getNombre());
        model.addAttribute("matriculaOdontologo", odontologo.getNroMatricula());
        return "vista/odontologo";
    }
     */

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }


    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if(odontologoEncontrado!=null){
            odontologoService.modificarOdontologo(odontologo);
            return ResponseEntity.ok("el odontologo fue modificado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(id);
        if(odontologoEncontrado!=null){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("el odontologo fue eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id){
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(id);
        if(odontologoEncontrado!=null){
            return ResponseEntity.ok(odontologoEncontrado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    //GET
    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>>  buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

}
