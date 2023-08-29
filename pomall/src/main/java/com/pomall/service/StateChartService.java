package com.pomall.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.pomall.dto.StateChartDTO;

public interface StateChartService {

	// 1차
	public List<StateChartDTO> firstCategoryOrderPrice();
	
	public JSONObject firstCategoryChart();
	
	// 2차
	public List<StateChartDTO> secondCategoryOrderPrice();
	
	public JSONObject secondCategoryChart();
}










