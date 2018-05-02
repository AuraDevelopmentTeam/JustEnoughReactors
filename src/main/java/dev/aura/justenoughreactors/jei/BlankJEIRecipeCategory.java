package dev.aura.justenoughreactors.jei;

import dev.aura.justenoughreactors.JustEnoughReactors;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BlankJEIRecipeCategory<T extends IRecipeWrapper>
    implements IRecipeCategory<T> {
  private static final Pattern titleReplacer = Pattern.compile("^justenoughreactors:(.+)$");

  @Getter(onMethod_ = {@Nullable})
  private final IDrawable icon;

  private String title;

  @Override
  public String getModName() {
    return JustEnoughReactors.NAME;
  }

  @Nonnull
  @Override
  public String getTitle() {
    if (title == null) {
      title = titleReplacer.matcher(getUid()).replaceFirst("justenoughreactors.gui.$1.title");
    }

    return I18n.format(title);
  }
}
