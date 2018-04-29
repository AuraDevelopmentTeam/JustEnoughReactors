package dev.aura.justenoughreactors.reactors.recipe;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import dev.aura.justenoughreactors.jei.fuel.FuelCategory;
import dev.aura.justenoughreactors.jei.fuel.FuelEntry;
import dev.aura.justenoughreactors.jei.fuel.FuelWrapper;
import dev.aura.justenoughreactors.reactors.ExtremeReactorsData;
import dev.aura.justenoughreactors.util.OreDictHelper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import erogenousbeef.bigreactors.api.data.ReactorReaction;
import erogenousbeef.bigreactors.api.data.SourceProductMapping;
import erogenousbeef.bigreactors.init.BrBlocks;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@UtilityClass
public class FuelRecipes {
  public static void registerFuelCategory(IRecipeCategoryRegistration registry) {
    registry.addRecipeCategories(new FuelCategory(registry));
  }

  @SuppressFBWarnings(
    value = "NP_NONNULL_PARAM_VIOLATION",
    justification = "BrBlocks.* won't be null at runtime!"
  )
  public static void registerFuelRecipes(IModRegistry registry) {
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.reactorController), FuelCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.reactorFuelRod), FuelCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.reactorControlRod), FuelCategory.ID);
    registry.addRecipeCatalyst(new ItemStack(BrBlocks.reactorAccessPort), FuelCategory.ID);

    final ImmutableSortedMap<String, String> conversionMapping = getConversionMapping();
    final ImmutableMap<String, List<String>> fuelOreDictMapping = getFuelOreDictMapping();

    registry.addRecipes(
        conversionMapping
            .entrySet()
            .stream()
            .map(
                entry ->
                    new FuelEntry(
                        fuelOreDictMapping.get(entry.getKey()),
                        fuelOreDictMapping.get(entry.getValue())))
            .map(FuelWrapper::new)
            .collect(Collectors.toList()),
        FuelCategory.ID);
  }

  private static ImmutableSortedMap<String, String> getConversionMapping() {
    return ExtremeReactorsData.ReactorConversions_reactions.values()
        .stream()
        .collect(
            ImmutableSortedMap.toImmutableSortedMap(
                Comparator.naturalOrder(),
                ReactorReaction::getSource,
                ReactorReaction::getProduct));
  }

  private static ImmutableMap<String, List<String>> getFuelOreDictMapping() {
    return ExtremeReactorsData.Reactants_reactantToSolid.entrySet()
        .stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry ->
                    entry
                        .getValue()
                        .stream()
                        .map(SourceProductMapping::getProduct)
                        .distinct()
                        .filter(OreDictHelper::doesOreExist)
                        .collect(Collectors.toList())))
        .entrySet()
        .stream()
        .filter(entry -> !entry.getValue().isEmpty())
        .collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
