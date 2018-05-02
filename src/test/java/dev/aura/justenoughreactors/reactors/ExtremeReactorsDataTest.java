package dev.aura.justenoughreactors.reactors;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableMap;
import erogenousbeef.bigreactors.api.data.ReactorReaction;
import erogenousbeef.bigreactors.api.data.SourceProductMapping;
import erogenousbeef.bigreactors.api.registry.Reactants;
import erogenousbeef.bigreactors.api.registry.ReactorConversions;
import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExtremeReactorsDataTest {
  private static final String MAT1 = "material1";
  private static final String MAT2 = "material2";
  private static final String MAT3 = "material3";

  private static final String ORE1 = "ore1";
  private static final String ORE2 = "ore2";
  private static final String ORE3 = "ore3";

  @BeforeClass
  public static void setUpBeforeClass() {
    Reactants.registerReactant(MAT1, 0, 0x123456);
    Reactants.registerReactant(MAT2, 1, 0xfedcba);

    Reactants.registerSolid(ORE1, MAT1);
    Reactants.registerSolid(ORE2, MAT2, 123);
    Reactants.registerSolid(ORE3, MAT2, 4321);

    ReactorConversions.register(MAT1, MAT2);
    ReactorConversions.register(MAT3, MAT2, 1.23f, 3.21f);
  }

  @Test
  public void Reactants_reactantToSolidTest() {
    final ImmutableMap<String, List<SourceProductMapping>> expected =
        ImmutableMap.<String, List<SourceProductMapping>>builder()
            .put(MAT1, Arrays.asList(new FixedSourceProductMapping(MAT1, 1000, ORE1, 1)))
            .put(
                MAT2,
                Arrays.asList(
                    new FixedSourceProductMapping(MAT2, 123, ORE2, 1),
                    new FixedSourceProductMapping(MAT2, 4321, ORE3, 1),
                    new FixedSourceProductMapping(MAT2, 4321, ORE3, 1))) // 10/10 coding!
            .build();

    assertEquals(expected, ExtremeReactorsData.Reactants_reactantToSolid);
  }

  @Test
  public void ReactorConversions_reactionsTest() {
    final ImmutableMap<String, ReactorReaction> expected =
        ImmutableMap.<String, ReactorReaction>builder()
            .put(MAT1, new FixedReactorReaction(MAT1, MAT2))
            .put(MAT3, new FixedReactorReaction(MAT3, MAT2, 1.23f, 3.21f))
            .build();

    assertEquals(expected, ExtremeReactorsData.ReactorConversions_reactions);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getMappingErrorTest() {
    ExtremeReactorsData.getMapping(Object.class, "Does not exist!");
  }

  private static class FixedSourceProductMapping extends SourceProductMapping {
    public FixedSourceProductMapping(
        String sourceKey, int sourceAmount, String productKey, int productAmount) {
      super(sourceKey, sourceAmount, productKey, productAmount);
    }

    @Override
    public boolean equals(Object obj) {
      if ((obj == null) || !(obj instanceof SourceProductMapping)) return false;

      final SourceProductMapping mapping = (SourceProductMapping) obj;

      return (sourceAmount == mapping.getSourceAmount())
          && (productAmount == mapping.getProductAmount())
          && source.equals(mapping.getSource())
          && product.equals(mapping.getProduct());
    }

    @Override
    public int hashCode() {
      assert false : "hashCode not designed";
      return 42;
    }

    @Override
    public String toString() {
      return "source="
          + source
          + ",sourceAmount="
          + sourceAmount
          + ",product="
          + product
          + ",productAmount="
          + productAmount;
    }
  }

  private static class FixedReactorReaction extends ReactorReaction {
    public FixedReactorReaction(String sourceKey, String productKey) {
      super(sourceKey, productKey);
    }

    public FixedReactorReaction(
        String sourceKey, String productKey, float reactivity, float fissionRate) {
      super(sourceKey, productKey, reactivity, fissionRate);
    }

    @Override
    public boolean equals(Object obj) {
      if ((obj == null) || !(obj instanceof ReactorReaction)) return false;

      final ReactorReaction mapping = (ReactorReaction) obj;

      return (reactivity == mapping.getReactivity())
          && (fissionRate == mapping.getFissionRate())
          && (sourceAmount == mapping.getSourceAmount())
          && (productAmount == mapping.getProductAmount())
          && source.equals(mapping.getSource())
          && product.equals(mapping.getProduct());
    }

    @Override
    public int hashCode() {
      assert false : "hashCode not designed";
      return 42;
    }

    @Override
    public String toString() {
      return "source="
          + source
          + ",sourceAmount="
          + sourceAmount
          + ",product="
          + product
          + ",productAmount="
          + productAmount
          + ",reactivity="
          + reactivity
          + ",fissionRate="
          + fissionRate;
    }
  }
}
