
# User-Related Endpoints

#### Login:
Path: `/users/login`    
Method: POST  
Body: A JSON containing `{username, password}`  
Returns: A token containing...
- `userId`
- `username`
- `roles` (a list of the user roles)

#### Register New Account:
Path: `/users/register`     
Method: POST  
body: A JSON containing `{username, password}`  
Returns: A token containing
- `userId`
- `username`
- `roles` (a list of the user roles)


# Ingredient-Related Endpoints

## Ingredient Searching

#### Get All Categories:
Path: `/categories`   
Method: GET  
Params: None  
Returns: List of `{id, name}` JSONs

#### Get Ingredients:
Path: `/ingredients/basket`   
Method: GET  
Params: `categoryId` (optional)  
Returns: List of ingredient JSONs containing...
- `id` (for ingredient)
- `name` (for ingredient)
- `categoryId`
- `categoryName`

#### Get Ingredient Descriptions:
Path: `/descriptions`    
Method: GET  
Params: `IngredientId` (required)  
Returns: List of description JSONs containing...
- `id` (for description)
- `description`

# Unit-Related Endpoints

#### Get All Units:
Path: `/units`    
Method: GET  
Params: None  
Returns: List of `{id, shortName, longName}` JSONs

# Recipe-Related Endpoints

## Recipe Searching

#### Get All Recipes:
Path: `/recipes`      
Method: GET  
Params: None  
Returns: List of complex recipes JSONs containing...
- `id`
- `owner`  
- `recipe`
- `prepTime`  
- `cookTime`  
- `steps` collection of step objects
    - `id`
    - `position`  
    - `instruction`  
- `recipe_ingredients` collection of ingredient ids
    - `id`  
    - `ingredientDescription`
    - `qty`  

#### Recipes Containing Ingredients:
Description: This should find all recipes that include at least the ingredients 
listed (may have extra ingredients)  
Path: `/ingredients/search`  
Method: POST  
Body: JSON containing the following:
- `ingredients`: a list of ingrient IDs  

Returns: List of basic recipe JSONs containing...
- `id`
- `recipe`

#### Recipes Limited to Ingredients:
Description: This should find all recipes that include nothing except the 
ingredients listed (may exclude some)  
Path: `/ingredients/strict-search`    
Method: POST  
Body: JSON containing the following:
- `ingredients`: a list of ingredient IDs  

Returns: List of basic recipe JSONs containing...
- `id`
- `recipe`

#### Get Recipe By ID:
Path: `/recipes/{id}`   
Method: GET  
Params: `recipeId` (required)

Returns: A detailed recipe JSON containing...
- `id`
- `name`
- `cookTime`
- `prepTime`
- `ownerId`   
- `steps` collection of step objects
    - `step_id`
    - `position`
    - `instruction`  
- `ingredients`: a collection of ingredient objects
    - `ingredient_id`
    - `name`  
    - `qty`
    - `category` object
        - `id`  
        - `category`  
    - `unit` object
        - `id`  
        - `shortType`   
        - `longType`  
    - `ingredientDescription` object
        - `ingredient_description_id`
        - `description`

#### Get Recipe Book:
(User info deduced from token)  
Path: `/users/recipebook`   
Method: GET  
Params: None  
Returns: A list of basic recipe JSONs containing...
- `id`
- `name`

#### Get Submitted Recipes:
(User info deduced from token)  
Path: `/users/recipes`   
Method: GET  
Params: None 
Returns: A list of basic recipe JSONs containing...
- `id`
- `name`


## Recipe Storing and Editing

#### Add New Recipe:
Path: `/recipes`   
Method: POST  
Body: A recipe JSON containing the following:
- `name`
- `cookTime`
- `prepTime`
- `ownerId`   
- `steps` collection of step objects
    - `position`
    - `instruction`  
- `ingredients`: a collection of ingredient objects
    - `ingredient_id` 
    - `qty`
    - `category` object
        - `id`  
        - `category`  
    - `unit` object
        - `id`  
        - `shortType`   
        - `longType`  
    - `ingredientDescription` object
        - `ingredient_description_id`
        - `description`

Returns: Recipe with recipe_id and step_id(s) populated

#### Save To Recipe Book:
(User info deduced from token)  
Path: `/users/recipebook/{id}`    
Method: GET  
Param: `recipe_id` required
Returns: None
