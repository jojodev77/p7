package com.nnk.springboot.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.RatingService;

@ExtendWith(MockitoExtension.class)
public class BidListControllerTest {
private MockMvc mockMvc;
	
	@InjectMocks
	private static BidListController bidListController;
	
	@Mock
	BidListRepository bidListRepository;
	
	@Mock
	BidListService bidListService;
	
	@Mock
	BindingResult result;
	
	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(bidListController).build();
	}
	
	/**
	 * @Description test get list bidList
	 */
	@Test
	public void home() {
		BidList bidList = new BidList();
		bidList.setAccount("fg");
		bidList.setAsk(2);
		bidList.setBidQuantity(4);
		bidList.setBook("ftgh");
		bidList.setCommentary("fvvg");
		List<BidList> bd = new ArrayList<>();
		bd.add(bidList);
		lenient().when(bidListRepository.findAll()).thenReturn(bd);
		
		try {
			mockMvc
			  .perform(get("/bidList/list"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("bidList/list"))
			  .andExpect(model().attribute("bidList", bd));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get add rating
	 */
	@Test
	public void getAddBidList() {
		try {
			mockMvc
			  .perform(get("/bidList/add"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("bidList/add"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post add rating
	 */
	@Test
	public void postAddBidList() {
		BidList bidList = new BidList();
		bidList.setBid(4);
		bidList.setAccount("fg");
		bidList.setAsk(2);
		bidList.setBidQuantity(4);
		bidList.setBook("ftgh");
		bidList.setCommentary("fvvg");
		bidList.setTrader("ghfg");
		bidList.setBenchmark("srdtfyuijk");
		List<BidList> bd = new ArrayList<>();
		bd.add(bidList);
		lenient().when(bidListService.addBidList(any())).thenReturn("fghj");
		lenient().when(bidListRepository.findAll()).thenReturn(bd);
		
		try {
			mockMvc
			  .perform(post("/bidList/validate"))
			  .andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get update bidList
	 */
	@Test
	public void getUpdateRating() {
		BidList bidList = new BidList();
		bidList.setAccount("fg");
		bidList.setAsk(2);
		bidList.setBidQuantity(4);
		bidList.setBook("ftgh");
		bidList.setCommentary("fvvg");
		List<BidList> bd = new ArrayList<>();
		bd.add(bidList);
		lenient().when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));
		
		try {
			mockMvc
			  .perform(get("/bidList/update/1"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("bidList/update"))
			  .andExpect(model().attribute("bidList", bidList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post update bidList
	 */
	@Test
	public void postUpdateBidList() {
		BidList bidList = new BidList();
		bidList.setAccount("fg");
		bidList.setAsk(2);
		bidList.setBidQuantity(4);
		bidList.setBook("ftgh");
		bidList.setCommentary("fvvg");
		List<BidList> bd = new ArrayList<>();
		bd.add(bidList);
		lenient().when(bidListService.updateBidList(1, bidList)).thenReturn(bidList);
		lenient().when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));
		
		try {
			mockMvc
			  .perform(post("/bidList/update/1"))
			  .andExpect(view().name("redirect:/rating/list"));
			 // .andExpect(model().attribute("bidList", bidList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post update bidList
	 */
	@Test
	public void getDeleteBidListTest() {
		BidList bidList = new BidList();
		bidList.setAccount("fg");
		bidList.setAsk(2);
		bidList.setBidQuantity(4);
		bidList.setBook("ftgh");
		bidList.setCommentary("fvvg");
		List<BidList> bd = new ArrayList<>();
		bd.add(bidList);
		lenient().when(bidListService.deleteBidList(anyLong())).thenReturn("");
		lenient().when(bidListRepository.findById(anyLong())).thenReturn(Optional.of(bidList));
		lenient().when(bidListRepository.findAll()).thenReturn(bd);
		
		try {
			mockMvc
			  .perform(get("/bidList/delete/1"))
			  .andExpect(view().name("redirect:/bidList/list"))
			  .andExpect(model().attribute("bidList", bd));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
