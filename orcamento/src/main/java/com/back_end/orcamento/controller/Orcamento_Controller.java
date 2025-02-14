package com.back_end.orcamento.controller;

import com.back_end.orcamento.dto.Orcamento_ResquestDTO;
import com.back_end.orcamento.model.Orcamento;
import com.back_end.orcamento.repository.Orcamento_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orcamento")
public class Orcamento_Controller {

    @Autowired
    private Orcamento_Repository repository;

    private Integer maiorId;


    private void atualizarMaiorId() {
        maiorId = repository.findAll().stream()
                .mapToInt(Orcamento::getId)
                .max()
                .orElse(0);
    }


    @GetMapping
    public ResponseEntity<List<Orcamento>> findAll(){
        List<Orcamento> orcamentos = this.repository.findAll();
        return ResponseEntity.ok(orcamentos);
    }


    @GetMapping("/{id}")
    public Orcamento findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Orçamento não foi encontrado"));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Orcamento_ResquestDTO dto) {

        if (dto.getNomeCliente() == null) {
            return ResponseEntity.badRequest().body("O campo não pode ser vazio!!!");
        }

        Orcamento orcamento = new Orcamento();
        orcamento.setNomeCliente(dto.getNomeCliente());
        orcamento.setData(dto.getData());

        Orcamento orcamentoSave = repository.save(orcamento);

        atualizarMaiorId();

        return ResponseEntity.ok(orcamentoSave);
    }

    @GetMapping("/maiorId")
    public ResponseEntity<?> getMaiorId() {
        return ResponseEntity.ok(maiorId);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Orcamento_ResquestDTO dto) {

        Optional<Orcamento> orcamentoOpt = repository.findById(id);

        Orcamento orcamento = orcamentoOpt.get();
        orcamento.setNomeCliente(dto.getNomeCliente());
        orcamento.setData(dto.getData());

        Orcamento orcamentoSave = repository.save(orcamento);
        return ResponseEntity.ok(orcamentoSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Optional<Orcamento> orcamentoOpt = repository.findById(id);

        if (orcamentoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("O ID fornecido não existe!!!");
        }
        repository.deleteById(id);
        return ResponseEntity.ok().body("O ID foi deletado do banco de dados!!!");
    }

}
