package dev.aura.justenoughreactors.util;

import dev.aura.justenoughreactors.JustEnoughReactors;
import erogenousbeef.bigreactors.common.BigReactors;
import lombok.experimental.UtilityClass;
import net.minecraft.util.ResourceLocation;

@UtilityClass
public class Resources {
  private static final String GUI_PATH = "textures/gui/";
  private static final String BLOCK_PATH = "textures/blocks/";

  public static ResourceLocation getBackgroundTexture() {
    return new ResourceLocation(JustEnoughReactors.ID, GUI_PATH + "background.png");
  }

  public static ResourceLocation getReactorCornerTexture() {
    return getReactorTexture("corner");
  }

  public static ResourceLocation getReactorSideTexture() {
    return getReactorTexture("eastwest");
  }

  public static ResourceLocation getReactorCenterTexture() {
    return getReactorTexture("face");
  }

  public static ResourceLocation getTurbineCornerTexture() {
    return getTurbineTexture("corner");
  }

  public static ResourceLocation getTurbineSideTexture() {
    return getTurbineTexture("eastwest");
  }

  public static ResourceLocation getTurbineCenterTexture() {
    return getTurbineTexture("face");
  }

  private static ResourceLocation getReactorTexture(String part) {
    return new ResourceLocation(
        BigReactors.MODID, BLOCK_PATH + "reactor/legacy/casing." + part + ".png");
  }

  private static ResourceLocation getTurbineTexture(String part) {
    return new ResourceLocation(
        BigReactors.MODID, BLOCK_PATH + "turbine/legacy/housing." + part + ".png");
  }
}
