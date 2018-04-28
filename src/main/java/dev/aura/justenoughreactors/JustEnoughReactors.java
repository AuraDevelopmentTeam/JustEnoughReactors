package dev.aura.justenoughreactors;

import dev.aura.justenoughreactors.jei.fuel.FuelCategory;
import lombok.Getter;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;

@Mod(
  modid = JustEnoughReactors.ID,
  name = JustEnoughReactors.NAME,
  version = JustEnoughReactors.VERSION,
  dependencies = JustEnoughReactors.DEPENDENCIES,
  certificateFingerprint = JustEnoughReactors.FINGERPRINT
)
@JEIPlugin
public class JustEnoughReactors implements IModPlugin {
  public static final String ID = "@id@";
  public static final String NAME = "@name@";
  public static final String VERSION = "@version@";
  public static final String GROUP = "@group@";
  public static final String DESCRIPTION = "@description@";
  public static final String DEPENDENCIES = "after-required:jei;after-required:bigreactors";
  public static final String FINGERPRINT = "2238d4a92d81ab407741a2fdb741cebddfeacba6";

  @Instance(ID)
  @Getter
  private static JustEnoughReactors instance;

  @Override
  public void registerCategories(IRecipeCategoryRegistration registry) {
    registry.addRecipeCategories(new FuelCategory(registry.getJeiHelpers()));
  }

  @Override
  public void register(IModRegistry registry) {
    // Nothing yet
  }
}
