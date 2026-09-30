package br.upe.reservapatterns.kit.creation;

import br.upe.reservapatterns.equipment.entity.Equipment;
import br.upe.reservapatterns.kit.entity.Kit;
import java.util.ArrayList;
import java.util.List;

/** Exercício 1: monte um novo kit e itens independentes em cada build(). */
public class KitBuilder {
  private final String name;
  private String description = "";
  private final List<Entry> entries = new ArrayList<>();

  public KitBuilder(String name) {
    this.name = name;
  }

  public KitBuilder description(String description) {
    if (description != null) {
      this.description = description;
    }
    return this;
  }

  public KitBuilder add(Equipment equipment, int quantity) {
    if (equipment == null) {
      throw new IllegalArgumentException("O equipamento não pode ser nulo.");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("A quantidade deve ser positiva.");
    }
    if (quantity > equipment.getTotalUnits()) {
      throw new IllegalArgumentException("A quantidade excede o total de unidades do equipamento.");
    }

    for (Entry entry : entries) {
      if (entry.equipment().equals(equipment)) {
        throw new IllegalArgumentException("Equipamento duplicado no kit.");
      }
    }

    entries.add(new Entry(equipment, quantity));
    return this;
  }

  public Kit build() {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("O nome do kit não pode ser vazio.");
    }
    if (entries.isEmpty()) {
      throw new IllegalArgumentException("O kit deve conter pelo menos um item.");
    }

    Kit kit = new Kit(this.name, this.description);

    for (Entry entry : entries) {
      kit.add(entry.equipment(), entry.quantity());
    }

    return kit;
  }

  private record Entry(Equipment equipment, int quantity) {}
}
