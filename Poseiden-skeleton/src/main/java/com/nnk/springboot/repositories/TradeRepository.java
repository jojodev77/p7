package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

	@Query("Select t from Trade t WHERE t.TradeId = :tradeId")
	Trade findByTradeId(@Param("tradeId")long id);
	
	Optional<Trade> findById(long id);
}
