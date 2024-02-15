package com.devs.demo.repository;

import com.devs.demo.model.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long> {
    List<Vendas> findByCpfUsuario(String cpf);
    List<Vendas> findByDataCompraBetween(Date startDate, Date endDate);

    @Query("SELECT v FROM Vendas v WHERE MONTH(v.dataCompra) = :mes")
    List<Vendas> findByMes(int mes);
}

