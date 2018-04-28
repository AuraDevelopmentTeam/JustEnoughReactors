package dev.aura.justenoughreactors.util;

import dev.aura.justenoughreactors.JustEnoughReactors;
import lombok.experimental.UtilityClass;
import net.minecraft.util.ResourceLocation;

@UtilityClass
public class Resources {
  private static final String GUI_PATH = "textures/gui/";

  public static ResourceLocation getBackgroundTexture() {
    return new ResourceLocation(JustEnoughReactors.ID, GUI_PATH + "background.png");
  }
}
