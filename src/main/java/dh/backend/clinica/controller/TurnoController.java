package dh.backend.clinica.controller;

import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.model.Turno;
import dh.backend.clinica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }



    @PutMapping("/modificar")
    public ResponseEntity<String>  modificarTurno(@RequestBody Turno turno){
        Turno turnoEncontrado = turnoService.buscarPorId(turno.getId());
        if(turnoEncontrado!= null){
            turnoService.modificarTurno(turno);
            return ResponseEntity.ok("el paciente fue modificado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        Turno turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado!= null) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("el turno fue eliminado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno>  buscarPorId(@PathVariable Integer id){
        Turno turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado!= null) {
            return ResponseEntity.ok(turnoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
}
