package dev.aura.justenoughreactors.jei.fuel;

import lombok.NonNull;
import lombok.Value;
import net.minecraft.item.ItemStack;

@Value
public class FuelEntry {
  @NonNull private final ItemStack fuel;
  @NonNull private final ItemStack waste;
}
