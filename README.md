🍽️ Recipe Management System – Spring Boot REST API

The Recipe Management System is a backend REST API application built using Spring Boot that allows users to upload, manage, and retrieve recipe data efficiently.

This application supports uploading recipes through JSON files, adding recipes manually via API, storing nested nutritional data in MySQL as JSON format, automatic total time calculation, and retrieval of top-rated recipes with proper null value handling.

🚀 Features

Upload multiple recipes using a JSON file

Add new recipes manually through REST API

Store nested nutritional data in MySQL as JSON

Automatically calculate total preparation time

Retrieve Top Rated Recipes

Prevent duplicate recipes based on title

Pagination support for sorted recipe retrieval

Handles null ratings using NULLS LAST

🛠️ Tech Stack

Java 21

Spring Boot

Spring Data JPA

Hibernate

MySQL

Jackson

Lombok

Multipart File Upload

🧱 Entity Structure

Recipe contains the following fields:

Title

Cuisine

Prep Time

Cook Time

Total Time (Auto Generated)

Description

Serves

Rating

Nutrients (Stored as JSON)

🧬 Nutrients JSON Structure

Example:

"nutrients": {
"calories": "500 kcal",
"carbohydrateContent": "60 g",
"proteinContent": "6 g",
"fatContent": "25 g"
}
📌 API Endpoints
Upload Recipes via File

POST /recipe/fileInput

Content-Type: multipart/form-data

Key: file
Value: JSON file containing recipe data

Add New Recipe

POST /recipe/addNewRecipe

Content-Type: application/json

Sample Request:

{
"title": "Chocolate Cake",
"cuisine": "Dessert",
"prep_time": 20,
"cook_time": 40,
"description": "A rich chocolate cake",
"nutrients": {
"calories": "500 kcal",
"carbohydrateContent": "60 g",
"proteinContent": "6 g",
"fatContent": "25 g"
},
"serves": "6 servings"
}
Get Top Rated Recipes

GET /recipe/topRated?size=5

Returns top N recipes sorted by rating in descending order.
Recipes with null ratings are displayed at the end.

⚙️ Database Configuration

Update your application.properties file:

spring.datasource.url=jdbc:mysql://localhost:3306/recipe_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
