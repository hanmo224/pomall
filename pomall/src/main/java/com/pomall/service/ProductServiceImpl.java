package com.pomall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomall.domain.CategoryVO;
import com.pomall.domain.Criteria;
import com.pomall.domain.ProductVO;
import com.pomall.mapper.ProductMapper;

import lombok.Setter;

@Service
public class ProductServiceImpl implements ProductService{

	@Setter(onMethod_ = {@Autowired})
	private ProductMapper productMapper;

	@Override
	public List<CategoryVO> getCategoryList() {
		// TODO Auto-generated method stub
		return productMapper.getCategoryList();
	}

	@Override
	public List<CategoryVO> subCategoryList(Integer cg_code) {
		// TODO Auto-generated method stub
		return productMapper.subCategoryList(cg_code);
	}

	@Override
	public List<ProductVO> pro_list(Integer cg_code, Criteria cri) {
		// TODO Auto-generated method stub
		return productMapper.pro_list(cg_code, cri);
	}

	@Override
	public int pro_count(Integer cg_code, Criteria cri) {
		// TODO Auto-generated method stub
		return productMapper.pro_count(cg_code, cri);
	}

	@Override
	public ProductVO pro_detail(Integer pro_code) {
		// TODO Auto-generated method stub
		return productMapper.pro_detail(pro_code);
	}
}



























