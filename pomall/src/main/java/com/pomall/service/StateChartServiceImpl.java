package com.pomall.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.dto.StateChartDTO;
import com.pomall.mapper.StateChartMapper;

import lombok.Setter;

@Service
public class StateChartServiceImpl implements StateChartService{

	@Setter(onMethod_ = {@Autowired})
	private StateChartMapper stateChartMapper;

	@Override
	public List<StateChartDTO> firstCategoryOrderPrice() {
		// TODO Auto-generated method stub
		return stateChartMapper.firstCategoryOrderPrice();
	}

	@Override
	public JSONObject firstCategoryChart() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = stateChartMapper.firstCategoryChart();
		
		JSONObject data = new JSONObject();
		
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		
		JSONArray title = new JSONArray();
		col1.put("label", "1차 카테고리 이름");
		col1.put("type", "string");
		col2.put("label", "주문금액");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		
		data.put("cols", title);
		
		JSONArray body = new JSONArray();
		
		for(Map<String, Object> map : list) {
			JSONObject categoryName = new JSONObject();
			categoryName.put("v", map.get("categoryname"));
			JSONObject orderprice = new JSONObject();
			orderprice.put("v", map.get("orderprice"));
			
			JSONArray row = new JSONArray();
			row.add(categoryName);
			row.add(orderprice);
			
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			
			body.add(cell);
		}
		
		data.put("rows", body);
		
		return data;
	}

	@Override
	public List<StateChartDTO> secondCategoryOrderPrice() {
		// TODO Auto-generated method stub
		return stateChartMapper.secondCategoryOrderPrice();
	}

	@Override
	public JSONObject secondCategoryChart() {
		
	    List<Map<String, Object>> list = stateChartMapper.secondCategoryChart();

	    JSONObject data = new JSONObject();

	    JSONObject col1 = new JSONObject();
	    JSONObject col2 = new JSONObject();

	    JSONArray title = new JSONArray();
	    col1.put("label", "2차 카테고리 이름");
	    col1.put("type", "string");
	    col2.put("label", "주문금액");
	    col2.put("type", "number");

	    title.add(col1);
	    title.add(col2);

	    data.put("cols", title);

	    JSONArray body = new JSONArray();

	    for (Map<String, Object> map : list) {
	        JSONObject categoryName = new JSONObject();
	        categoryName.put("v", map.get("categoryname"));
	        JSONObject orderprice = new JSONObject();
	        orderprice.put("v", map.get("orderprice"));

	        JSONArray row = new JSONArray();
	        row.add(categoryName);
	        row.add(orderprice);

	        JSONObject cell = new JSONObject();
	        cell.put("c", row);

	        body.add(cell);
	    }

	    data.put("rows", body);

	    return data;
	}

}











