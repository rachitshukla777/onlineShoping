package com.itrac.onlineshopping.dao;


import java.util.List;

import com.itrac.onlineshopping.dto.Category;

public interface CategoryDAO
{
  
	List<Category> list();
	Category get(int id);
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}