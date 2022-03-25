package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

	@Query("Select b from BidList b WHERE b.BidListId = :bidListId")
	Optional<BidList> findByBidListId(@Param("bidListId")long bidListId);
	
	Optional<BidList> findById(long id);

}
