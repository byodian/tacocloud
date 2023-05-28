package tacos;

import lombok.Data;
import tacos.eum.IngredientTypeEnum;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final IngredientTypeEnum type;
}
