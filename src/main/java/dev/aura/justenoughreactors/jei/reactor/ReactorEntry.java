package dev.aura.justenoughreactors.jei.reactor;

import lombok.NonNull;
import lombok.Value;

@Value
public class ReactorEntry {
  @NonNull private final String material;
  private final boolean block;

  public static ReactorEntry newBlock(String blockName) {
    return new ReactorEntry(blockName, true);
  }

  public static ReactorEntry newFluid(String fluidName) {
    return new ReactorEntry(fluidName, false);
  }
}
