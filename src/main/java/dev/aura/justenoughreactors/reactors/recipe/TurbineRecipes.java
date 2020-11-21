package dev.aura.justenoughreactors.reactors.recipe;

import com.google.common.collect.ImmutableList;
import dev.aura.justenoughreactors.jei.turbine.TurbineCategory;
import dev.aura.justenoughreactors.jei.turbine.TurbineEntry;
import dev.aura.justenoughreactors.jei.turbine.TurbineWrapper;
import dev.aura.justenoughreactors.reactors.ExtremeReactorsData;
import dev.aura.justenoughreactors.util.OreDictHelper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import erogenousbeef.bigreactors.init.BrBlocks;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@UtilityClass
public class TurbineRecipes {
  public static void registerTurbineCategory(IRecipeCategoryRegistration registry) {
    registry.addRecipeCategories(new TurbineCategory(registry));
  }

  @SuppressFBWarnings(
      value = "NP_NONNULL_PARAM_VIOLATION",
      justification = "BrBlocks.* won't be null at runtime!")
  public static void registerTurbineRecipes(IModRegistry registry) {
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.turbineRotorBlade), TurbineCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.turbineRotorShaft), TurbineCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.turbineBearing), TurbineCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.turbineHousing), TurbineCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.turbineGlass), TurbineCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.turbineController), TurbineCategory.ID);

    final ImmutableList<String> blocks = getBlocks();

    registry.addRecipes(
        blocks.stream()
            .filter(OreDictHelper::doesOreExist)
            .map(TurbineEntry::new)
            .map(TurbineWrapper::new)
            .collect(Collectors.toList()),
        TurbineCategory.ID);
  }

  private static ImmutableList<String> getBlocks() {
    return ImmutableList.copyOf(ExtremeReactorsData.TurbineCoil_blocks.keySet());
  }
}
