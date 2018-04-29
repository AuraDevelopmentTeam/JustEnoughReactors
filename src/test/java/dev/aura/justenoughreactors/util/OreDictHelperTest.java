package dev.aura.justenoughreactors.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor({
  "net.minecraft.item.ItemStack",
  "net.minecraftforge.oredict.OreDictionary"
})
@PrepareForTest({ItemStack.class, OreDictionary.class})
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

  @Before
  public void setUp() {
    PowerMockito.mockStatic(OreDictionary.class);

    Mockito.when(OreDictionary.getOres(EMPTY)).thenReturn(listEmpty);
    Mockito.when(OreDictionary.getOres(ONE)).thenReturn(listOneElement);
    Mockito.when(OreDictionary.getOres(THREE)).thenReturn(listThreeElements);
  }

  @Test
  public void oreDictToItemStacksSingleTest() {
    assertEquals(listEmpty, OreDictHelper.oreDictToItemStacks(EMPTY));
    assertEquals(listOneElement, OreDictHelper.oreDictToItemStacks(ONE));
    assertEquals(listThreeElements, OreDictHelper.oreDictToItemStacks(THREE));
  }

  @Test
  public void oreDictToItemStacksMultipleTest() {
    assertEquals(
        expectedFourElements, OreDictHelper.oreDictToItemStacks(Arrays.asList(EMPTY, ONE, THREE)));
  }
}
