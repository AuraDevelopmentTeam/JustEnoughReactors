package dev.aura.justenoughreactors.jei.fuel;

import lombok.Value;
import net.minecraft.item.ItemStack;

@Value
public class FuelEntry {
  private final ItemStack fuel;
  private final ItemStack waste;
}
