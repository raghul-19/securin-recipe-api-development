package com.securin.Receipe.Repository;

import com.securin.Receipe.Entity.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends JpaRepository<Recipes,Integer> {

    @Query("Select r from Recipes r where LOWER(r.title) = LOWER(:title)")
    public Recipes findByTitle(@Param("title") String title);
}
