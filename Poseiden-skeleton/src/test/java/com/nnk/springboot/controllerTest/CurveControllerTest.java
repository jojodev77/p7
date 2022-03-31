package com.nnk.springboot.controllerTest;

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

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.RatingService;

@ExtendWith(MockitoExtension.class)
public class CurveControllerTest {
private MockMvc mockMvc;
	
	@InjectMocks
	private static CurveController curveController;
	
	@Mock
	CurvePointRepository curvePointRepository;
	
	@Mock
	CurvePointService curvePointService;
	
	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(curveController).build();
	}
	
	/**
	 * @Description test get list rating
	 */
	@Test
	public void home() {
		CurvePoint curve = new CurvePoint();
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		lenient().when(curvePointRepository.findAll()).thenReturn(lc);
		
		try {
			mockMvc
			  .perform(get("/curvePoint/list"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("curvePoint/list"))
			  .andExpect(model().attribute("curvePoint", lc));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get add rating
	 */
	@Test
	public void getAddCurve() {
		try {
			mockMvc
			  .perform(get("/curvePoint/add"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("curvePoint/add"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post add rating
	 */
	@Test
	public void postAddCurve() {
		CurvePoint curve = new CurvePoint();
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		lenient().when(curvePointRepository.findAll()).thenReturn(lc);
		
		try {
			mockMvc
			  .perform(post("/curvePoint/validate"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("curvePoint/list"))
			  .andExpect(model().attribute("curvePoint", lc));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get update rating
	 */
	@Test
	public void getUpdateCurve() {
		CurvePoint curve = new CurvePoint();
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		lenient().when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curve));
		
		try {
			mockMvc
			  .perform(get("/curvePoint/update/1"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("curvePoint/update"))
			  .andExpect(model().attribute("curvePoint", curve));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post update rating
	 */
	@Test
	public void postUpdateCurveTest() {
		CurvePoint curve = new CurvePoint();
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		lenient().when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curve));
		curvePointRepository.save(curve);
		try {
			mockMvc
			  .perform(post("/curvePoint/update/1"))
			  .andExpect(view().name("redirect:/curvePoint/list"));
//			  .andExpect(model().attribute("curvePoint", curve));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post update rating
	 */
	@Test
	public void getDeleteCurveTest() {
		CurvePoint curve = new CurvePoint();
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		lenient().when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curve));
		lenient().when(curvePointRepository.findAll()).thenReturn(lc);
		
		try {
			mockMvc
			  .perform(get("/curvePoint/delete/1"))
//			  .andExpect(status().isOk())
			  .andExpect(view().name("redirect:/curvePoint/list"))
			  .andExpect(model().attribute("curvePoint", lc));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
