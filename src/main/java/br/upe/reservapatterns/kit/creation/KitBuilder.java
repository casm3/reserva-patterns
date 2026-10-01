package br.upe.reservapatterns.kit.creation;

import br.upe.reservapatterns.equipment.entity.Equipment;
import br.upe.reservapatterns.kit.entity.Kit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Exercício 1: monte um novo kit e itens independentes em cada build(). */
public class KitBuilder {
  private final String name;
  private String description = "";
  private final List<Entry> entries = new ArrayList<>();

  public KitBuilder(String name) {
    this.name = name;
  }

  public KitBuilder description(String description) {
    this.description = description == null ? "" : description;
    return this;
  }

  public KitBuilder add(Equipment equipment, int quantity) {
    if (equipment == null) {
      throw new IllegalArgumentException("Equipamento é obrigatório");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("A quantidade deve ser positiva");
    }
    if (quantity > equipment.getTotalUnits()) {
      throw new IllegalArgumentException(
              "Quantidade maior que o estoque de " + equipment.getName());
    }
    for (Entry e : entries) {
      if (sameEquipment(e.equipment(), equipment)) {
        throw new IllegalArgumentException("Equipamento repetido no kit: " + equipment.getName());
      }
    }
    entries.add(new Entry(equipment, quantity));
    return this;
  }

  public Kit build() {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("O nome do kit é obrigatório");
    }
    if (entries.isEmpty()) {
      throw new IllegalArgumentException("O kit precisa ter ao menos um item");
    }
    Kit kit = new Kit(name, description);
    for (Entry e : entries) {
      kit.add(e.equipment(), e.quantity());
    }
    return kit;
  }

  private static boolean sameEquipment(Equipment a, Equipment b) {
    if (a == b) {
      return true;
    }
    return a.getId() != null && Objects.equals(a.getId(), b.getId());
  }

  private record Entry(Equipment equipment, int quantity) {}
}
