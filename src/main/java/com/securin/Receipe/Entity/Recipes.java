package com.securin.Receipe.Entity;

import com.fasterxml.jackson.annotation.*;
import com.securin.Receipe.Model.Nutrients;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Recipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String cuisine;
    private Float rating;

    @JsonProperty("prep_time")
    private Integer prepTime;

    @JsonProperty("cook_time")
    private Integer cookTime;

    @JsonProperty("total_time")
    private Integer totalTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Nutrients nutrients;
    private String serves;

}
