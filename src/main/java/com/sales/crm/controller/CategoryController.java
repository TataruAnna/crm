package com.sales.crm.controller;

import com.sales.crm.dtos.CategoryDTO;
import com.sales.crm.model.Category;
import com.sales.crm.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Endpoints for category ")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //TODO de acceptat ca paremetru un CategoryRequestDTO in loc de Category
    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory (@RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategoryDTO = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);

    }
    @PostMapping("/update")
    public ResponseEntity<Category> updateCategory (@RequestParam String name, @RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(name, category));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> productCategories = categoryService.findAll();
        return ResponseEntity.ok(productCategories);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<List<Category>> deleteCategoryById(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.deleteCategoryById(categoryId));
    }






}
