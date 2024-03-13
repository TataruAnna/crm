package com.sales.crm.controller;

import com.sales.crm.model.Category;
import com.sales.crm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    private ResponseEntity<Category> addCategory (@RequestBody Category category){
        Category newCategory = categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);

    }
    @PostMapping("/update")
    private ResponseEntity<Category> updateCategory (@RequestParam String name, @RequestBody Category category){
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
