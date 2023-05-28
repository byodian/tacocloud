package tacos;

import org.junit.jupiter.api.Test;
import tacos.eum.IngredientTypeEnum;

public class IngredientTest {

    private final IngredientTypeEnum[] types = IngredientTypeEnum.values();

    @Test
    public void testIngredientType() {
        for (IngredientTypeEnum type : types) {
            System.out.println(type);
        }
    }
}
