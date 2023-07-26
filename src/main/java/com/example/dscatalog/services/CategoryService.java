package com.example.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dscatalog.dto.CategoryDTO;
import com.example.dscatalog.entities.Category;
import com.example.dscatalog.repositories.CategoryRepository;
import com.example.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();

        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

        // outra forma de fazer
        // List<CategoryDTO> listDTO = new ArrayList<>();
        // for (Category category : list) {
        // listDTO.add(new CategoryDTO(category));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) throws EntityNotFoundException {

        Optional<Category> obj = repository.findById(id);

        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));

        return new CategoryDTO(entity);
    }

}
