package com.demo.service;

import com.demo.domain.Category;
import com.demo.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ekaterina Pyataeva on 02.05.2017.
 */

@Service
@Transactional
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        categories.addAll(categoryRepository.findAll());
        return categories;
    }


    public Category getCategoryById(Long id){
        return categoryRepository.findOne(id);
    }

    public Category create(Category form){
        return categoryRepository.save(form);
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void delete(Category category){
        categoryRepository.delete(category);
    }

}
