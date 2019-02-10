package dev.aura.justenoughreactors.jei.fuel;

import dev.aura.justenoughreactors.util.OreDictHelper;
import javax.annotation.Nonnull;
import lombok.NonNull;
import lombok.Value;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;

@Value
public class FuelWrapper implements IRecipeWrapper {
  @NonNull private final FuelEntry fuelEntry;

  @Override
  public void getIngredients(@Nonnull IIngredients ingredients) {
    ingredients.setInputs(
        VanillaTypes.ITEM, OreDictHelper.oreDictToItemStacks(fuelEntry.getFuel()));
    ingredients.setOutputs(
        VanillaTypes.ITEM, OreDictHelper.oreDictToItemStacks(fuelEntry.getWaste()));
  }
}
