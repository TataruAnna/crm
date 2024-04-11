package com.sales.crm.service;

import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.Category;
import com.sales.crm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Transactional
    public Category updateCategory(String name, Category category){
        Category foundCategory = categoryRepository.findCategoryByName(name).orElseThrow(()-> new ResourceNotFoundException("This category doesn't exist" ));
        if(category.getDescription() != null){
            foundCategory.setDescription(category.getDescription());
        }
        return categoryRepository.save(foundCategory);
    }
    @Transactional
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Transactional
    public List<Category> deleteCategoryById(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("This category doesn't exist"));
        categoryRepository.delete(category);
        return findAll();
    }

}
