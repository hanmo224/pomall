package com.pomall.mapper;

import java.util.List;
import java.util.Map;

import com.pomall.dto.StateChartDTO;

public interface StateChartMapper {

	// 1차
	public List<StateChartDTO> firstCategoryOrderPrice();
	
	public List<Map<String, Object>> firstCategoryChart();
	
	// 2차
	public List<StateChartDTO> secondCategoryOrderPrice();
	
	public List<Map<String, Object>> secondCategoryChart();
}











