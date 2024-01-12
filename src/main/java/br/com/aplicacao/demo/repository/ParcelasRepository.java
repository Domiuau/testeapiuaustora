package br.com.aplicacao.demo.repository;

import br.com.aplicacao.demo.entidades.Parcelas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelasRepository extends JpaRepository<Parcelas, String> {
}
