package dev.aura.justenoughreactors.reactors.recipe;

import com.google.common.collect.ImmutableList;
import dev.aura.justenoughreactors.jei.reactor.ReactorCategory;
import dev.aura.justenoughreactors.jei.reactor.ReactorEntry;
import dev.aura.justenoughreactors.jei.reactor.ReactorWrapper;
import dev.aura.justenoughreactors.reactors.ExtremeReactorsData;
import dev.aura.justenoughreactors.util.OreDictHelper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import erogenousbeef.bigreactors.init.BrBlocks;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@UtilityClass
public class ReactorRecipes {
  public static void registerReactorCategory(IRecipeCategoryRegistration registry) {
    registry.addRecipeCategories(new ReactorCategory(registry));
  }

  @SuppressFBWarnings(
    value = "NP_NONNULL_PARAM_VIOLATION",
    justification = "BrBlocks.* won't be null at runtime!"
  )
  public static void registerReactorRecipes(IModRegistry registry) {
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.reactorCasing), ReactorCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.reactorGlass), ReactorCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.reactorController), ReactorCategory.ID);

    final ImmutableList<String> blocks = getBlocks();
    final ImmutableList<String> fluids = getFluids();

    registry.addRecipes(
        Stream.concat(
                blocks.stream().filter(OreDictHelper::doesOreExist).map(ReactorEntry::newBlock),
                fluids.stream().filter(OreDictHelper::doesFluidExist).map(ReactorEntry::newFluid))
            .map(ReactorWrapper::new)
            .collect(Collectors.toList()),
        ReactorCategory.ID);
  }

  private static ImmutableList<String> getBlocks() {
    return ImmutableList.copyOf(
        ExtremeReactorsData.ReactorInterior_reactorModeratorBlocks.keySet());
  }

  private static ImmutableList<String> getFluids() {
    return ImmutableList.copyOf(
        ExtremeReactorsData.ReactorInterior_reactorModeratorFluids.keySet());
  }
}
