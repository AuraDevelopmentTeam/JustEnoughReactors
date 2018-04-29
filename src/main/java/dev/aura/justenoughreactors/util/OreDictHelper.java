package dev.aura.justenoughreactors.util;

import java.util.Collection;
import java.util.List;
import lombok.experimental.UtilityClass;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

@UtilityClass
public class OreDictHelper {
  public static NonNullList<ItemStack> oreDictToItemStacks(String oreDictEntry) {
    return OreDictionary.getOres(oreDictEntry);
  }

  public static NonNullList<ItemStack> oreDictToItemStacks(List<String> oreDictEntries) {
    return oreDictEntries
        .stream()
        .map(OreDictHelper::oreDictToItemStacks)
        .flatMap(Collection::stream)
        .collect(NonNullList::create, NonNullList::add, NonNullList::addAll);
  }

  public static boolean doesOreExist(String oreDictEntry) {
    return OreDictionary.doesOreNameExist(oreDictEntry);
  }

  public static boolean doesFluidExist(String fluidDictEntry) {
    return FluidRegistry.isFluidRegistered(fluidDictEntry);
  }
}
