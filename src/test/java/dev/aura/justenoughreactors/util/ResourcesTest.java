package dev.aura.justenoughreactors.util;

import static org.junit.Assert.assertEquals;

import net.minecraft.util.ResourceLocation;
import org.junit.Test;

public class ResourcesTest {
  @Test
  public void getBackgroundTextureTest() {
    assertEquals(
        new ResourceLocation("justenoughreactors", "textures/gui/background.png"),
        Resources.getBackgroundTexture());
  }

  @Test
  public void getReactorCornerTextureTest() {
    assertEquals(
        new ResourceLocation("bigreactors", "textures/blocks/reactor/legacy/casing.corner.png"),
        Resources.getReactorCornerTexture());
  }

  @Test
  public void getReactorSideTextureTest() {
    assertEquals(
        new ResourceLocation("bigreactors", "textures/blocks/reactor/legacy/casing.eastwest.png"),
        Resources.getReactorSideTexture());
  }

  @Test
  public void getReactorCenterTextureTest() {
    assertEquals(
        new ResourceLocation("bigreactors", "textures/blocks/reactor/legacy/casing.face.png"),
        Resources.getReactorCenterTexture());
  }

  @Test
  public void getTurbineCornerTextureTest() {
    assertEquals(
        new ResourceLocation("bigreactors", "textures/blocks/turbine/legacy/housing.corner.png"),
        Resources.getTurbineCornerTexture());
  }

  @Test
  public void getTurbineSideTextureTest() {
    assertEquals(
        new ResourceLocation("bigreactors", "textures/blocks/turbine/legacy/housing.eastwest.png"),
        Resources.getTurbineSideTexture());
  }

  @Test
  public void getTurbineCenterTextureTest() {
    assertEquals(
        new ResourceLocation("bigreactors", "textures/blocks/turbine/legacy/housing.face.png"),
        Resources.getTurbineCenterTexture());
  }
}
