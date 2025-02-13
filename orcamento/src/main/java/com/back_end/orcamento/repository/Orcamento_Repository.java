package com.back_end.orcamento.repository;

import com.back_end.orcamento.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Orcamento_Repository extends JpaRepository<Orcamento, Integer> {
}
