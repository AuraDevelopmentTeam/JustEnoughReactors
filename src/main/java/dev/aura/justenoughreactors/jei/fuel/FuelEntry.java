package dev.aura.justenoughreactors.jei.fuel;

import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class FuelEntry {
  public FuelEntry(String fuel, String waste) {
    this(Collections.singletonList(fuel), Collections.singletonList(waste));
  }

  @NonNull private final List<String> fuel;
  @NonNull private final List<String> waste;
}
