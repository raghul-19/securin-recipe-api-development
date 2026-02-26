package com.securin.Receipe.Controller;

import com.securin.Receipe.Model.RecipeRequest;
import com.securin.Receipe.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/fileInput")
    public ResponseEntity<?> getFileInputDataSets(
            @RequestPart("file") MultipartFile file) {

        try {

            if(file.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("File is empty");
            }

            recipeService.saveRecipesFromFile(file);

            return ResponseEntity.ok("Recipes extracted successfully");

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("Message", e.getMessage() != null ? e.getMessage() : "Unknown error occurred"));

        }
    }

    @PostMapping("/addNewRecipe")
    public ResponseEntity<?> addNewRecipe(@RequestBody RecipeRequest request) {
        try {
            return ResponseEntity.ok(recipeService.addNewRecipe(request));
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("Message", e.getMessage()));
        }
    }

    @GetMapping("/topRated")
    public ResponseEntity<?> getTopRatedRecipes(@RequestParam int size) {
        try {
            return  ResponseEntity.ok(recipeService.getTopRatedRecipes(size));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Message", e.getMessage()));
        }
    }
}