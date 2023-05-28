package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.eum.IngredientTypeEnum;
import tacos.Taco;
import tacos.TacoOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    // 使用 @ModelAttribute注解，没有指定具体的请求路径
    // 这意味着它会在处理该控制器中的任何请求之前被自动调用。
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", IngredientTypeEnum.WRAP),
                new Ingredient("COTO", "Corn Tortilla", IngredientTypeEnum.WRAP),
                new Ingredient("GRBF", "Ground Beef", IngredientTypeEnum.PROTEIN),
                new Ingredient("CARN", "Carnitas", IngredientTypeEnum.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", IngredientTypeEnum.VEGGIES),
                new Ingredient("LETC", "Lettuce", IngredientTypeEnum.VEGGIES),
                new Ingredient("CHED", "Cheddar", IngredientTypeEnum.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", IngredientTypeEnum.CHEESE),
                new Ingredient("SLSA", "Salsa", IngredientTypeEnum.SAUCE),
                new Ingredient("SRCR", "Sour Cream", IngredientTypeEnum.SAUCE)
        );

        IngredientTypeEnum[] types = IngredientTypeEnum.values();
        for (IngredientTypeEnum type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "taco";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, IngredientTypeEnum type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(Taco taco, @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processing taco: " + taco);
        log.info("Processing tacoOrder: " + tacoOrder);
        return "redirect:/orders/current";
    }
}

