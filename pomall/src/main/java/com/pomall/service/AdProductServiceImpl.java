package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.CategoryVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.ProductVO;
import com.pomall.mapper.AdProductMapper;

import lombok.Setter;

@Service
public class AdProductServiceImpl implements AdProductService {

	@Setter(onMethod_ = {@Autowired})
	private AdProductMapper adProductMapper;

	@Override
	public List<CategoryVO> getCategoryList() {
		// TODO Auto-generated method stub
		return adProductMapper.getCategoryList();
	}

	@Override
	public List<CategoryVO> subCategoryList(Integer cg_code) {
		// TODO Auto-generated method stub
		return adProductMapper.subCategoryList(cg_code);
	}

	@Override
	public void pro_insert(ProductVO vo) {
		// TODO Auto-generated method stub
		adProductMapper.pro_insert(vo);
	}

	@Override
	public List<ProductVO> getListWithPaging(Criteria cri, Integer cg_code) {
		// TODO Auto-generated method stub
		return adProductMapper.getListWithPaging(cri, cg_code);
	}

	@Override
	public int getTotalCount(Criteria cri, Integer cg_code) {
		// TODO Auto-generated method stub
		return adProductMapper.getTotalCount(cri, cg_code);
	}

	@Override
	public ProductVO modify(Integer pro_code) {
		// TODO Auto-generated method stub
		return adProductMapper.modify(pro_code);
	}

	@Override
	public CategoryVO get(int cg_code) {
		// TODO Auto-generated method stub
		return adProductMapper.get(cg_code);
	}

	@Override
	public void update(ProductVO vo) {
		// TODO Auto-generated method stub
		adProductMapper.update(vo);
	}

	@Override
	public void delete(Integer pro_code) {
		// TODO Auto-generated method stub
		adProductMapper.delete(pro_code);
	}
	
	@Override
	public void pro_checked_modify(List<ProductVO> pro_list) {
		// TODO Auto-generated method stub
		adProductMapper.pro_checked_modify(pro_list);
	}
	
//	@Override
//	public void pro_checked_modify2(Integer pro_code, int pro_price, String pro_buy) {
//		// TODO Auto-generated method stub
//		adProductMapper.pro_checked_modify2(pro_code, pro_price, pro_buy);
//	}
	
	@Override
	public void pro_checked_delete(List<Integer> pro_code_arr) {
		// TODO Auto-generated method stub
		adProductMapper.pro_checked_delete(pro_code_arr);
	}

}


















