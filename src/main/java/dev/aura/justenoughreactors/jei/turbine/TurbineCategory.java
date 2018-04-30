package dev.aura.justenoughreactors.jei.turbine;

import dev.aura.justenoughreactors.jei.BlankJEIRecipeCategory;
import dev.aura.justenoughreactors.util.DrawableFrame;
import dev.aura.justenoughreactors.util.Resources;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import erogenousbeef.bigreactors.init.BrBlocks;
import javax.annotation.Nonnull;
import lombok.Getter;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class TurbineCategory extends BlankJEIRecipeCategory<TurbineWrapper> {
  public static final String ID = "justenoughreactors:turbine";

  @Getter private final IDrawable background;

  public TurbineCategory(IRecipeCategoryRegistration registry) {
    this(registry.getJeiHelpers());
  }

  public TurbineCategory(IJeiHelpers helpers) {
    this(helpers.getGuiHelper());
  }

  @SuppressFBWarnings(
    value = "NP_NONNULL_PARAM_VIOLATION",
    justification = "BrBlocks.turbineBearing won't be null at runtime!"
  )
  public TurbineCategory(IGuiHelper guiHelper) {
    super(guiHelper.createDrawableIngredient(new ItemStack(BrBlocks.turbineBearing, 1, 0)));

    background =
        new DrawableFrame(
            Resources.getTurbineCornerTexture(),
            Resources.getTurbineSideTexture(),
            Resources.getTurbineCenterTexture());
  }

  @Nonnull
  @Override
  public String getUid() {
    return ID;
  }

  @Nonnull
  @Override
  public String getTitle() {
    return I18n.format("justenoughreactors.gui.turbine.title");
  }

  @Override
  public void setRecipe(
      @Nonnull IRecipeLayout recipeLayout,
      @Nonnull TurbineWrapper recipeWrapper,
      @Nonnull IIngredients ingredients) {
    final IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

    itemStacks.init(0, true, 15, 15);

    itemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
  }
}
