package com.back_end.orcamento.controller;

import com.back_end.orcamento.dto.ProdutoOrcamento_ResquestDTO;
import com.back_end.orcamento.model.Orcamento;
import com.back_end.orcamento.model.ProdutoOrcamento;
import com.back_end.orcamento.repository.Orcamento_Repository;
import com.back_end.orcamento.repository.ProdutoOrcamento_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtoOrcamento")
public class ProdutoOrcamento_Controller {

        @Autowired
    private ProdutoOrcamento_Repository repository;

        @Autowired
    private Orcamento_Repository orcamentoRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoOrcamento>> findAll(){
        List<ProdutoOrcamento> produtoOrcamentos = this.repository.findAll();
        return ResponseEntity.ok(produtoOrcamentos);
    }

    @GetMapping("/{id}")
    public ProdutoOrcamento findById(@PathVariable Integer id) {
        return this.repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("O ProdutoOrcamenrto não foi encontrado !!!"));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProdutoOrcamento_ResquestDTO dto) {

        if (dto.getValor() == null) {
            return ResponseEntity.badRequest().body("O campo valor não pode estar vazio!!!");
        }

        if (dto.getNome() == null) {
            return ResponseEntity.badRequest().body("O campo nome não pode estar vazio!!!");
        }

        Integer maiorId = orcamentoRepository.findAll().stream()
                .mapToInt(Orcamento::getId)
                .max()
                .orElse(0);

        Optional<Orcamento> optionalOrcamento = orcamentoRepository.findById(maiorId);

        if (!optionalOrcamento.isPresent()) {
            return ResponseEntity.badRequest().body("Nenhum orçamento encontrado!!!");
        }

        ProdutoOrcamento produto = new ProdutoOrcamento();
        produto.setNome(dto.getNome());
        produto.setValor(dto.getValor());
        produto.setOrcamento(optionalOrcamento.get());

        ProdutoOrcamento produtoSave = repository.save(produto);
        return ResponseEntity.ok(produtoSave);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProdutoOrcamento_ResquestDTO dto) {

        Optional<ProdutoOrcamento> produtoOpt = repository.findById(id);

        if (dto.getId() == null) {
            return ResponseEntity.badRequest().body("O ID fornecido é invalido tente com outro Id !!!");
        }

        if (dto.getOrcamento_id() == null) {
            return ResponseEntity.badRequest().body("O ID fornecido é invalido!!!");
        }

        if (dto.getNome() == null) {
            return ResponseEntity.badRequest().body("O campo não poder ser vazio!!!");
        }

        if (dto.getValor() == null) {
            return ResponseEntity.badRequest().body("O campo não poder ser vazio!!!");        }

        ProdutoOrcamento produto = produtoOpt.get();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setValor(dto.getValor());

        ProdutoOrcamento produtoSave = repository.save(produto);
        return ResponseEntity.ok(produtoSave);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Optional<ProdutoOrcamento> ProdutoOpt = repository.findById(id);

        if (ProdutoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("O ID fornecido não existe!!!");
        }
        repository.deleteById(id);
        return ResponseEntity.ok().body("O ID foi deletado do banco de dados!!!");
    }
}
