package dev.aura.justenoughreactors.jei.fuel;

import dev.aura.justenoughreactors.JustEnoughReactors;
import dev.aura.justenoughreactors.jei.BlankJEIRecipeCategory;
import dev.aura.justenoughreactors.util.Resources;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import erogenousbeef.bigreactors.init.BrItems;
import javax.annotation.Nonnull;
import lombok.Getter;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

public class FuelCategory extends BlankJEIRecipeCategory<FuelWrapper> {
  public static final String ID = JustEnoughReactors.ID + ":fuel";

  @Getter private final IDrawable background;

  public FuelCategory(IRecipeCategoryRegistration registry) {
    this(registry.getJeiHelpers());
  }

  public FuelCategory(IJeiHelpers helpers) {
    this(helpers.getGuiHelper());
  }

  @SuppressFBWarnings(
    value = "NP_NONNULL_PARAM_VIOLATION",
    justification = "BrItems.ingotMetals won't be null at runtime!"
  )
  public FuelCategory(IGuiHelper guiHelper) {
    super(guiHelper.createDrawableIngredient(new ItemStack(BrItems.ingotYellorium, 1, 0)));

    background = guiHelper.createDrawable(Resources.getBackgroundTexture(), 0, 0, 76, 18);
  }

  @Nonnull
  @Override
  public String getUid() {
    return ID;
  }

  @Override
  public void setRecipe(
      @Nonnull IRecipeLayout recipeLayout,
      @Nonnull FuelWrapper recipeWrapper,
      @Nonnull IIngredients ingredients) {
    final IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

    itemStacks.init(0, true, 0, 0);
    itemStacks.init(1, false, 58, 0);

    itemStacks.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
    itemStacks.set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
  }
}
