package com.securin.Receipe.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Nutrients {

    private String calories;
    private String carbohydrateContent;
    private String proteinContent;
    private String fatContent;
}

