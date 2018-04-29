package dev.aura.justenoughreactors.jei.reactor;

import dev.aura.justenoughreactors.jei.BlankJEIRecipeCategory;
import dev.aura.justenoughreactors.util.Resources;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import erogenousbeef.bigreactors.init.BrBlocks;
import javax.annotation.Nonnull;
import lombok.Getter;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class ReactorCategory extends BlankJEIRecipeCategory<ReactorWrapper> {
  public static final String ID = "justenoughreactors:reactor";

  @Getter private final IDrawable background;

  public ReactorCategory(IRecipeCategoryRegistration registry) {
    this(registry.getJeiHelpers());
  }

  public ReactorCategory(IJeiHelpers helpers) {
    this(helpers.getGuiHelper());
  }

  @SuppressFBWarnings(
    value = "NP_NONNULL_PARAM_VIOLATION",
    justification = "BrBlocks.reactorCasing won't be null at runtime!"
  )
  public ReactorCategory(IGuiHelper guiHelper) {
    super(guiHelper.createDrawableIngredient(new ItemStack(BrBlocks.reactorCasing, 1, 0)));

    background = guiHelper.createDrawable(Resources.getBackgroundTexture(), 0, 0, 76, 18);
  }

  @Nonnull
  @Override
  public String getUid() {
    return ID;
  }

  @Nonnull
  @Override
  public String getTitle() {
    return I18n.format("justenoughreactors.gui.reactor.title");
  }

  @Override
  public void setRecipe(
      @Nonnull IRecipeLayout recipeLayout,
      @Nonnull ReactorWrapper recipeWrapper,
      @Nonnull IIngredients ingredients) {
    if (recipeWrapper.getReactorEntry().isBlock()) {
      final IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

      itemStacks.init(0, true, 0, 0);

      itemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
    } else {
      final IGuiFluidStackGroup fluidStacks = recipeLayout.getFluidStacks();

      fluidStacks.init(0, true, 0, 0);

      fluidStacks.set(0, ingredients.getInputs(FluidStack.class).get(0));
    }
  }
}
