package com.nnk.springboot.serviceTest;

import static org.junit.Assert.assertEquals;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {
	@Spy
	@InjectMocks
	private BidListService bidListService = new BidListService();

	@Mock
	BidListRepository bidListRepository;

	/**
	 * @Description method for add curvePoint with succes
	 */
	@Test
	public void addRradeTest() {
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

	/**
	 * @Description method for add curvePoint with error
	 */
	@Test
	public void addRradeTestwithError() {
		// GIVEN
		BidList bidList = new BidList();
		bidList.setAccount("fg");
		bidList.setAsk(2);
		bidList.setBidQuantity(4);
		bidList.setBook("ftgh");
		bidList.setCommentary("fvvg");
		List<BidList> bd = new ArrayList<>();
		bd.add(bidList);
		ResponseEntity resp = new ResponseEntity<>("bidList add with success", HttpStatus.OK);
		// WHEN
		lenient().when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));
		// THEN
		// THEN
		// THEN
		assertEquals(bidListService.addBidList(bidList), resp);

	}
	
	/**
	 * @Description method for update curvePoint with succes
	 */
	@Test
	public void updateRadeTest() {
		// GIVEN
		BidList bidList = new BidList();
		bidList.setAccount("fg");
		bidList.setAsk(2);
		bidList.setBidQuantity(4);
		bidList.setBook("ftgh");
		bidList.setCommentary("fvvg");
		bidList.setId(1);
		List<BidList> bd = new ArrayList<>();
		bd.add(bidList);
		// WHEN
		bidListRepository.save(bidList);
		lenient().when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));
		// THENup
		bidListService.updateBidList(1, bidList);
		verify(bidListService).updateBidList(1, bidList);

	}
	
	/**
	 * @Description method for delete curvePoint with succes
	 */
	@Test
	public void deleteRadeTest() {
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
		bidListRepository.save(bidList);
		lenient().when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));
		// THEN
		bidListService.deleteBidList(1);
		verify(bidListService).deleteBidList(1);

	}
}
