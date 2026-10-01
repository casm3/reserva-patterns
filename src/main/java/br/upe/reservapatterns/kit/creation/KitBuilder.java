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
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException();
    } else {
      this.name = name;
    }
  }

  public KitBuilder description(String description) {
    if (description == null) {
      throw new IllegalArgumentException();
    } else {
      this.description = description;
      return this;
    }
  }

  public KitBuilder add(Equipment equipment, int quantity) {
    for (Entry entry : entries) {
      if (entry.equipment.equals(equipment)) {
        throw new IllegalArgumentException();
      }
    }

    if (equipment == null || quantity <= 0 || quantity > equipment.getTotalUnits()) {
      throw new IllegalArgumentException();
    }

    entries.add(new Entry(equipment, quantity));
    return this;
  }

  public Kit build() {
    if (!entries.isEmpty()) {
      Kit kit = new Kit(name, description);

      for (Entry entry : entries) {
        if (entry.quantity() > entry.equipment().getTotalUnits() || entry.quantity <= 0) {
          throw new IllegalArgumentException();
        } else {
          kit.add(entry.equipment(), entry.quantity());
        }
      }

      return kit;
    } else {
      throw new IllegalArgumentException();
    }
  }

  private record Entry(Equipment equipment, int quantity) {}
}
