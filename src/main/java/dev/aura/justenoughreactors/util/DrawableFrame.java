package dev.aura.justenoughreactors.util;

import lombok.RequiredArgsConstructor;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

@RequiredArgsConstructor
public class DrawableFrame implements IDrawable {
  private static final int renderSize = 16;

  private final ResourceLocation corner;
  private final ResourceLocation side;
  private final ResourceLocation center;

  private final int paddingTop;
  private final int paddingLeft;

  private static void drawTexturedRectangle(int x, int y, float rotation) {
    final int halfSize = renderSize / 2;

    GlStateManager.pushMatrix();
    GlStateManager.translate(x + halfSize, y + halfSize, 0);
    GlStateManager.rotate(rotation, 0, 0, 1);

    Gui.drawModalRectWithCustomSizedTexture(
        -halfSize, -halfSize, 0, 0, renderSize, renderSize, renderSize, renderSize);

    GlStateManager.popMatrix();
  }

  public DrawableFrame(ResourceLocation corner, ResourceLocation side, ResourceLocation center) {
    this(corner, side, center, 0, 0);
  }

  @Override
  public int getWidth() {
    return 3 * renderSize;
  }

  @Override
  public int getHeight() {
    return 3 * renderSize;
  }

  @Override
  public void draw(Minecraft minecraft, int xOffset, int yOffset) {
    final int x = xOffset + this.paddingLeft;
    final int y = yOffset + this.paddingTop;

    final int shift1 = renderSize;
    final int shift2 = renderSize * 2;

    minecraft.getTextureManager().bindTexture(corner);
    drawTexturedRectangle(x, y, 0);
    drawTexturedRectangle(x + shift2, y, 90);
    drawTexturedRectangle(x, y + shift2, 180);
    drawTexturedRectangle(x + shift2, y + shift2, 270);

    minecraft.getTextureManager().bindTexture(side);
    drawTexturedRectangle(x + shift1, y, 0);
    drawTexturedRectangle(x, y + shift1, 90);
    drawTexturedRectangle(x + shift1, y + shift2, 180);
    drawTexturedRectangle(x + shift2, y + shift1, 270);

    minecraft.getTextureManager().bindTexture(center);
    drawTexturedRectangle(x + shift1, y + shift1, 0);
  }
}
