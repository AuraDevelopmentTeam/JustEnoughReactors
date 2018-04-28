package dev.aura.justenoughreactors;

import net.minecraftforge.fml.common.Mod;

@Mod(
  modid = JustEnoughReactors.ID,
  name = JustEnoughReactors.NAME,
  version = JustEnoughReactors.VERSION,
  dependencies = JustEnoughReactors.DEPENDENCIES,
  certificateFingerprint = JustEnoughReactors.FINGERPRINT
)
public class JustEnoughReactors {
  public static final String ID = "@id@";
  public static final String NAME = "@name@";
  public static final String VERSION = "@version@";
  public static final String GROUP = "@group@";
  public static final String DESCRIPTION = "@description@";
  public static final String DEPENDENCIES = "after-required:jei;after-required:bigreactors";
  public static final String FINGERPRINT = "2238d4a92d81ab407741a2fdb741cebddfeacba6";
}
