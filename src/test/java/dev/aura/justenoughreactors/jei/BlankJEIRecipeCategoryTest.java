package dev.aura.justenoughreactors.jei;

import static org.junit.Assert.*;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(I18n.class)
public class BlankJEIRecipeCategoryTest {
  @Test
  public void getTitleTest() {
    PowerMockito.mockStatic(I18n.class);
    Mockito.when(I18n.format(Mockito.any())).thenAnswer(invocation -> invocation.getArguments()[0]);

    final TestBase test1 = new Test1();
    final TestBase test2 = new Test2();

    assertEquals("justenoughreactors.gui.test1.title", test1.getTitle());
    assertEquals("justenoughreactors.gui.test1.title", test1.getTitle());
    assertEquals("justenoughreactors.gui.test2.title", test2.getTitle());
    assertEquals("justenoughreactors.gui.test2.title", test2.getTitle());
  }

  private static class Test1 extends TestBase {
    @Override
    public String getUid() {
      return "justenoughreactors:test1";
    }
  }

  private static class Test2 extends TestBase {
    @Override
    public String getUid() {
      return "justenoughreactors:test2";
    }
  }

  private abstract static class TestBase extends BlankJEIRecipeCategory<IRecipeWrapper> {
    protected TestBase() {
      super(null);
    }

    @SuppressFBWarnings(
        value = "NP_NONNULL_RETURN_VIOLATION",
        justification = "Doesn't matter in testing. Method won't get called!")
    @Override
    public IDrawable getBackground() {
      return null;
    }

    @Override
    public void setRecipe(
        IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
      // Nothing
    }
  }
}
