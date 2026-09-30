package br.upe.reservapatterns.kit.creation;

import br.upe.reservapatterns.equipment.entity.Equipment;
import br.upe.reservapatterns.kit.entity.Kit;
import br.upe.reservapatterns.kit.entity.KitItem;

import java.util.ArrayList;
import java.util.List;

/** Exercício 1: monte um novo kit e itens independentes em cada build(). */
public class KitBuilder {
  private final String name;
  private String description = "";
  private final List<Entry> entries = new ArrayList<>();

  public KitBuilder(String name) {
    if (name.isBlank()) {
      throw new IllegalArgumentException("A variável nome não pode ser vazia");
    } else {
      this.name = name;
    }
  }

  public KitBuilder description(String description) {
    if (description.isBlank()) {
      throw new IllegalArgumentException("A descrição não pode ser vázia");
    } else {
      this.description = description;
    }

    return this;
  }

  public KitBuilder add(Equipment equipment, int quantity) {
    if (quantity > 0 && quantity <= equipment.getTotalUnits()) {
      Entry entry = new Entry(equipment, quantity);
      entries.add(entry);
    } else {
      throw new IllegalArgumentException("A quantidade dos equipamentos não pode ser negativa ou" +
              "maior que a quantidade total de itens");
    }
    return this;
  }

  public Kit build() {
    Kit kit = new Kit(this.name, this.description);
    for (Entry entry: this.entries) {
      kit.add(entry.equipment, entry.quantity);
    }
    return kit;
  }

  private record Entry(Equipment equipment, int quantity) {}
}
