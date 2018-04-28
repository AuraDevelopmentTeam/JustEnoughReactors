package dev.aura.justenoughreactors.reactors;

import com.google.common.collect.ImmutableMap;
import erogenousbeef.bigreactors.api.data.ReactorReaction;
import erogenousbeef.bigreactors.api.data.SourceProductMapping;
import erogenousbeef.bigreactors.api.registry.Reactants;
import erogenousbeef.bigreactors.api.registry.ReactorConversions;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExtremeReactorsData {
  public static final ImmutableMap<String, List<SourceProductMapping>> Reactants_reactantToSolid =
      getImmutableMapping(Reactants.class, "_reactantToSolid");
  public static final ImmutableMap<String, ReactorReaction> ReactorConversions_reactions =
      getImmutableMapping(ReactorConversions.class, "_reactions");

  @SuppressWarnings("unchecked")
  private static <K, V> Map<K, V> getMapping(Class<?> mappingClass, String mappingName) {
    try {
      final Field mappingField = mappingClass.getDeclaredField(mappingName);
      mappingField.setAccessible(true);

      return (Map<K, V>) mappingField.get(null);
    } catch (NoSuchFieldException
        | SecurityException
        | IllegalArgumentException
        | IllegalAccessException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private static <K, V> ImmutableMap<K, V> getImmutableMapping(
      Class<?> mappingClass, String mappingName) {
    return ImmutableMap.copyOf(getMapping(mappingClass, mappingName));
  }
}
