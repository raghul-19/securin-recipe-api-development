package com.securin.Receipe.Service;

import com.securin.Receipe.Entity.Recipes;
import com.securin.Receipe.Model.RecipeRequest;
import com.securin.Receipe.ReceipeApplication;
import com.securin.Receipe.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service


public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    private Recipes generateRecipe(RecipeRequest recipeRequest) {
        Integer totalTime=(recipeRequest.getPrepTime()!=null && recipeRequest.getCookTime()!=null)?recipeRequest.getPrepTime()+recipeRequest.getCookTime():null;
        return Recipes.builder()
                .title(recipeRequest.getTitle())
                .cuisine(recipeRequest.getCuisine())
                .prepTime(recipeRequest.getPrepTime())
                .cookTime(recipeRequest.getCookTime())
                .totalTime(totalTime)
                .nutrients(recipeRequest.getNutrients())
                .serves(recipeRequest.getServes())
                .description(recipeRequest.getDescription())
                .build();
    }

    public void saveRecipesFromFile(MultipartFile file) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        Map<String, Recipes> recipeDataSets=mapper.readValue(
                file.getInputStream(),
                new TypeReference<Map<String, Recipes>>() {}
        );
        List<Recipes>recipes=new ArrayList<>();
        for(Recipes recipe:recipeDataSets.values()){
            Recipes r=recipeRepository.findByTitle(recipe.getTitle());
            if(r==null){
                recipes.add(recipe);
            }
        }

        recipeRepository.saveAll(recipes);
    }

    public Recipes addNewRecipe(RecipeRequest request) {
        Recipes r=recipeRepository.findByTitle(request.getTitle());
        if(r!=null) return r;
        return recipeRepository.save(generateRecipe(request));

    }

    public Page<Recipes> getTopRatedRecipes(int size){
        Pageable pageable = PageRequest.of(
                0,
                size,
                Sort.by(
                        Sort.Order.desc("rating").nullsLast()
                )
        );

        Page<Recipes> recipes = recipeRepository.findAll(pageable);
        return recipes;
    }
}
