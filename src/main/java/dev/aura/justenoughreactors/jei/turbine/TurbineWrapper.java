package dev.aura.justenoughreactors.jei.turbine;

import dev.aura.justenoughreactors.util.OreDictHelper;
import javax.annotation.Nonnull;
import lombok.NonNull;
import lombok.Value;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

@Value
public class TurbineWrapper implements IRecipeWrapper {
  @NonNull private final TurbineEntry turbineEntry;

  @Override
  public void getIngredients(@Nonnull IIngredients ingredients) {
    ingredients.setInput(
        ItemStack.class, OreDictHelper.oreDictToItemStacks(turbineEntry.getMaterial()));
  }
}
