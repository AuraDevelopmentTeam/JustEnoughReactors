package dev.aura.justenoughreactors.reactors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.google.common.collect.ImmutableMap;
import erogenousbeef.bigreactors.api.data.CoilPartData;
import erogenousbeef.bigreactors.api.data.ReactorInteriorData;
import erogenousbeef.bigreactors.api.data.ReactorReaction;
import erogenousbeef.bigreactors.api.data.SourceProductMapping;
import erogenousbeef.bigreactors.api.registry.Reactants;
import erogenousbeef.bigreactors.api.registry.ReactorConversions;
import erogenousbeef.bigreactors.api.registry.ReactorInterior;
import erogenousbeef.bigreactors.api.registry.TurbineCoil;
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

    ReactorInterior.registerBlock(ORE1, 1.0f, 2.0f, 3.0f, 4.0f);
    ReactorInterior.registerBlock(ORE2, 4.0f, 3.0f, 2.0f, 1.0f);
    ReactorInterior.registerBlock(ORE3, 0.0f, 0.0f, 0.0f, 0.0f);

    ReactorInterior.registerFluid(ORE1, 1.0f, 2.0f, 3.0f, 4.0f);
    ReactorInterior.registerFluid(ORE2, 4.0f, 3.0f, 2.0f, 1.0f);
    ReactorInterior.registerFluid(ORE3, 0.0f, 0.0f, 0.0f, 0.0f);

    TurbineCoil.registerBlock(ORE1, 1.0f, 2.0f, 3.0f);
    TurbineCoil.registerBlock(ORE2, 4.0f, 3.0f, 2.0f);
    TurbineCoil.registerBlock(ORE3, 0.0f, 0.0f, 0.0f);
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

  @Test
  public void ReactorInterior_reactorModeratorBlocksTest() {
    final ImmutableMap<String, ReactorInteriorData> expected =
        ImmutableMap.<String, ReactorInteriorData>builder()
            .put(ORE1, new FixedReactorInteriorData(1.0f, 2.0f, 3.0f, 4.0f))
            .put(ORE2, new FixedReactorInteriorData(4.0f, 3.0f, 2.0f, 1.0f))
            .put(ORE3, new FixedReactorInteriorData(0.0f, 0.0f, 0.0f, 0.0f))
            .build();

    assertEquals(expected, ExtremeReactorsData.ReactorInterior_reactorModeratorBlocks);
  }

  @Test
  public void ReactorInterior_reactorModeratorFluidsTest() {
    final ImmutableMap<String, ReactorInteriorData> expected =
        ImmutableMap.<String, ReactorInteriorData>builder()
            .put(ORE1, new FixedReactorInteriorData(1.0f, 2.0f, 3.0f, 4.0f))
            .put(ORE2, new FixedReactorInteriorData(4.0f, 3.0f, 2.0f, 1.0f))
            .put(ORE3, new FixedReactorInteriorData(0.0f, 0.0f, 0.0f, 0.0f))
            .build();

    assertEquals(expected, ExtremeReactorsData.ReactorInterior_reactorModeratorFluids);
  }

  @Test
  public void TurbineCoil_blocksTest() {
    final ImmutableMap<String, CoilPartData> expected =
        ImmutableMap.<String, CoilPartData>builder()
            .put(ORE1, new FixedCoilPartData(1.0f, 2.0f, 3.0f))
            .put(ORE2, new FixedCoilPartData(4.0f, 3.0f, 2.0f))
            .put(ORE3, new FixedCoilPartData(0.0f, 0.0f, 0.0f))
            .build();

    assertEquals(expected, ExtremeReactorsData.TurbineCoil_blocks);
  }

  @Test(expected = NoSuchFieldException.class)
  public void getMappingErrorTest() throws Throwable {
    try {
      ExtremeReactorsData.getMapping(Object.class, "Does not exist!");
    } catch (IllegalArgumentException e) {
      throw e.getCause();
    }

    fail("Expected IllegalArgumentException");
  }

  @Test(expected = ClassCastException.class)
  public void getClassCastErrorTest() throws Throwable {
    try {
      ExtremeReactorsData.getMapping(String.class, "serialPersistentFields");
    } catch (IllegalArgumentException e) {
      throw e.getCause();
    }

    fail("Expected IllegalArgumentException");
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

  private static class FixedReactorInteriorData extends ReactorInteriorData {
    public FixedReactorInteriorData(
        float absorption, float heatEfficiency, float moderation, float heatConductivity) {
      super(absorption, heatEfficiency, moderation, heatConductivity);
    }

    @Override
    public boolean equals(Object obj) {
      if ((obj == null) || !(obj instanceof ReactorInteriorData)) return false;

      final ReactorInteriorData mapping = (ReactorInteriorData) obj;

      return (absorption == mapping.absorption)
          && (heatEfficiency == mapping.heatEfficiency)
          && (moderation == mapping.moderation)
          && (heatConductivity == mapping.heatConductivity);
    }

    @Override
    public int hashCode() {
      assert false : "hashCode not designed";
      return 42;
    }

    @Override
    public String toString() {
      return "absorption="
          + absorption
          + ",heatEfficiency="
          + heatEfficiency
          + ",moderation="
          + moderation
          + ",heatConductivity="
          + heatConductivity;
    }
  }

  private static class FixedCoilPartData extends CoilPartData {
    public FixedCoilPartData(float efficiency, float bonus, float extractionRate) {
      super(efficiency, bonus, extractionRate);
    }

    @Override
    public boolean equals(Object obj) {
      if ((obj == null) || !(obj instanceof CoilPartData)) return false;

      final CoilPartData mapping = (CoilPartData) obj;

      return (efficiency == mapping.efficiency)
          && (bonus == mapping.bonus)
          && (energyExtractionRate == mapping.energyExtractionRate);
    }

    @Override
    public int hashCode() {
      assert false : "hashCode not designed";
      return 42;
    }

    @Override
    public String toString() {
      return "efficiency="
          + efficiency
          + ",bonus="
          + bonus
          + ",energyExtractionRate="
          + energyExtractionRate;
    }
  }
}
