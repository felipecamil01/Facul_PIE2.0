package com.Advocacia.Controller;

import com.Advocacia.Entity.Contato;
import com.Advocacia.Service.ContatoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contato")
@CrossOrigin(origins = "*")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping("/save")
    public ResponseEntity<Contato> save(@Valid @RequestBody Contato contato) {
        Contato novoContato = contatoService.save(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContato);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contato> update(@PathVariable Long id, @Valid @RequestBody Contato contatoAtualizado) {
        try {
            Contato contato = contatoService.update(id, contatoAtualizado);
            return ResponseEntity.ok(contato);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            contatoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Contato>> findAll() {
        List<Contato> contatos = contatoService.findAll();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        return contatoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
}
