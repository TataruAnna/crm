package com.sales.crm.MapperService;

import com.sales.crm.dtos.CategoryDTO;
import com.sales.crm.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryDTO mapFromCategoryToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }
    public Category mapFromDTOToCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
