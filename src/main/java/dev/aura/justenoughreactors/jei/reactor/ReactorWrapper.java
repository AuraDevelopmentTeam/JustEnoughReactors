package dev.aura.justenoughreactors.jei.reactor;

import dev.aura.justenoughreactors.util.OreDictHelper;
import javax.annotation.Nonnull;
import lombok.NonNull;
import lombok.Value;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

@Value
public class ReactorWrapper implements IRecipeWrapper {
  @NonNull private final ReactorEntry reactorEntry;

  @Override
  public void getIngredients(@Nonnull IIngredients ingredients) {
    if (reactorEntry.isBlock()) {
      ingredients.setInputs(
          VanillaTypes.ITEM, OreDictHelper.oreDictToItemStacks(reactorEntry.getMaterial()));
    } else {
      ingredients.setInput(
          VanillaTypes.FLUID,
          FluidRegistry.getFluidStack(reactorEntry.getMaterial(), Fluid.BUCKET_VOLUME));
    }
  }
}
