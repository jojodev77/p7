package com.nnk.springboot.integration;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@ExtendWith(MockitoExtension.class)
public class TestIntegrationIt {

	@Spy
	@InjectMocks
	private BidListService bidListService = new BidListService();

	@Mock
	BidListRepository bidListRepository;
	
	@Test
	public void testInteBidList() {
		// GIVEN
		BidList bidList = new BidList();
		bidList.setAccount("fg");
		bidList.setAsk(2);
		bidList.setBidQuantity(4);
		bidList.setBook("ftgh");
		bidList.setCommentary("fvvg");
		List<BidList> bd = new ArrayList<>();
		bd.add(bidList);
		// WHEN
		lenient().when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));
		// THEN
		bidListService.addBidList(bidList);
		verify(bidListService).addBidList(bidList);
	}
}
