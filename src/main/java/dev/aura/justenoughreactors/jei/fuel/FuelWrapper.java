package dev.aura.justenoughreactors.jei.fuel;

import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

@RequiredArgsConstructor
public class FuelWrapper implements IRecipeWrapper {
  private final FuelEntry fuelEntry;

  @Override
  public void getIngredients(@Nonnull IIngredients ingredients) {
    ingredients.setInput(ItemStack.class, fuelEntry.getFuel());
    ingredients.setOutput(ItemStack.class, fuelEntry.getWaste());
  }
}
