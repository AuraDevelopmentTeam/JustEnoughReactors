package dev.aura.justenoughreactors.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor({
  "net.minecraftforge.fluids.FluidRegistry",
  "net.minecraft.item.ItemStack",
  "net.minecraftforge.oredict.OreDictionary"
})
@PrepareForTest({FluidRegistry.class, ItemStack.class, OreDictionary.class})
public class OreDictHelperTest {
  private static final String EMPTY = "empty";
  private static final String ONE = "one";
  private static final String THREE = "three";

  private static final NonNullList<ItemStack> listEmpty = NonNullList.create();
  private static final NonNullList<ItemStack> listOneElement =
      NonNullList.from(ItemStack.EMPTY, Mockito.mock(ItemStack.class));
  private static final NonNullList<ItemStack> listThreeElements =
      NonNullList.from(
          ItemStack.EMPTY,
          Mockito.mock(ItemStack.class),
          Mockito.mock(ItemStack.class),
          Mockito.mock(ItemStack.class));

  private static final NonNullList<ItemStack> expectedFourElements =
      NonNullList.from(
          ItemStack.EMPTY,
          listOneElement.get(0),
          listThreeElements.get(0),
          listThreeElements.get(1),
          listThreeElements.get(2));

  private static void setUpLists() {
    PowerMockito.mockStatic(OreDictionary.class);

    Mockito.when(OreDictionary.getOres(EMPTY)).thenReturn(listEmpty);
    Mockito.when(OreDictionary.getOres(ONE)).thenReturn(listOneElement);
    Mockito.when(OreDictionary.getOres(THREE)).thenReturn(listThreeElements);
  }

  @Test
  public void oreDictToItemStacksSingleTest() {
    setUpLists();

    assertEquals(listEmpty, OreDictHelper.oreDictToItemStacks(EMPTY));
    assertEquals(listOneElement, OreDictHelper.oreDictToItemStacks(ONE));
    assertEquals(listThreeElements, OreDictHelper.oreDictToItemStacks(THREE));
  }

  @Test
  public void oreDictToItemStacksMultipleTest() {
    setUpLists();

    assertEquals(
        expectedFourElements, OreDictHelper.oreDictToItemStacks(Arrays.asList(EMPTY, ONE, THREE)));
  }

  @Test
  public void doesOreExistTest() {
    PowerMockito.mockStatic(OreDictionary.class);

    Mockito.when(OreDictionary.doesOreNameExist(EMPTY)).thenReturn(false);
    Mockito.when(OreDictionary.doesOreNameExist(ONE)).thenReturn(true);
    Mockito.when(OreDictionary.doesOreNameExist(THREE)).thenReturn(true);
    Mockito.when(OreDictionary.getOres(ONE)).thenReturn(listEmpty);
    Mockito.when(OreDictionary.getOres(THREE)).thenReturn(listThreeElements);

    assertFalse(OreDictHelper.doesOreExist(EMPTY));
    assertFalse(OreDictHelper.doesOreExist(ONE));
    assertTrue(OreDictHelper.doesOreExist(THREE));
  }

  @Test
  public void doesFluidExistTest() {
    PowerMockito.mockStatic(FluidRegistry.class);

    Mockito.when(FluidRegistry.isFluidRegistered(EMPTY)).thenReturn(false);
    Mockito.when(FluidRegistry.isFluidRegistered(ONE)).thenReturn(true);

    assertFalse(OreDictHelper.doesFluidExist(EMPTY));
    assertTrue(OreDictHelper.doesFluidExist(ONE));
  }
}
