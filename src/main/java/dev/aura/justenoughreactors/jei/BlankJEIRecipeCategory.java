package dev.aura.justenoughreactors.jei;

import dev.aura.justenoughreactors.JustEnoughReactors;
import javax.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BlankJEIRecipeCategory<T extends IRecipeWrapper>
    implements IRecipeCategory<T> {
  private final IDrawable icon;

  @Override
  public String getModName() {
    return JustEnoughReactors.NAME;
  }

  @Nullable
  @Override
  public IDrawable getIcon() {
    return icon;
  }
}
