package sia.tacocloud.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sia.tacocloud.models.Ingredient;

import java.util.List;

@Service
@Slf4j
public class TacoCLoudClient {

    private RestTemplate restTemplate;

    public TacoCLoudClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
    }

    public List<Ingredient> getAllIngredients() {
        return restTemplate.exchange("http://localhost:8080/ingredients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {})
                .getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}",
                ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return restTemplate.postForObject("http://localhost:8080/ingredients",
                ingredient, Ingredient.class);
    }

//    public Ingredient getIngredientById(String ingredientId) {
//        ResponseEntity<Ingredient> responseEntity =
//                restTemplate.getForEntity("http://localhost:8080/ingredients/{id}",
//                        Ingredient.class, ingredientId);
//        log.info("Fetched time: {}",
//                responseEntity.getHeaders().getDate());
//        return responseEntity.getBody();
//    }

//    public Ingredient createIngredient(Ingredient ingredient) {
//        ResponseEntity<Ingredient> responseEntity =
//                restTemplate.postForEntity("http://localhost:8080/ingredients",
//                        ingredient,
//                        Ingredient.class);
//        log.info("New resource created at {}",
//                responseEntity.getHeaders().getLocation());
//        return responseEntity.getBody();
//    }
}
