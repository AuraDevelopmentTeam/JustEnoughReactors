package dev.aura.justenoughreactors.jei.fuel;

import dev.aura.justenoughreactors.jei.BlankJEIRecipeCategory;
import javax.annotation.Nonnull;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.resources.I18n;

public class FuelCategory extends BlankJEIRecipeCategory<FuelWrapper> {
  public static final String ID = "justenoughreactors:fuel";

  public FuelCategory() {
    super(null);
  }

  @Nonnull
  @Override
  public String getUid() {
    return ID;
  }

  @Nonnull
  @Override
  public String getTitle() {
    return I18n.format("justenoughreactors.gui.fuel.title");
  }

  @Nonnull
  @Override
  public IDrawable getBackground() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setRecipe(
      IRecipeLayout recipeLayout, FuelWrapper recipeWrapper, IIngredients ingredients) {
    // TODO Auto-generated method stub
  }
}
