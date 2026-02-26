package com.securin.Receipe.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.securin.Receipe.Model.Nutrients;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RecipeRequest {

    private String title;
    private String cuisine;

    @JsonProperty("prep_time")
    private Integer prepTime;

    @JsonProperty("cook_time")
    private Integer cookTime;


    private String description;
    private Nutrients nutrients;
    private String serves;
}