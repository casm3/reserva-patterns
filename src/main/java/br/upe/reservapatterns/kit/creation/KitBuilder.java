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
    if (description == null) {
      throw new IllegalArgumentException("Descrição obrigatória");
    }
    this.description = description;
    return this;
  }

  public KitBuilder add(Equipment equipment, int quantity) {
    if (equipment == null || quantity <= 0 || quantity > equipment.getTotalUnits()) {
      throw new IllegalArgumentException("Item do kit inválido");
    }
    if (entries.stream().anyMatch(entry -> entry.equipment() == equipment
            || equipment.getId() != null
            && equipment.getId().equals(entry.equipment().getId()))) {
      throw new IllegalArgumentException("Equipamento repetido");
    }
    entries.add(new Entry(equipment, quantity));
    return this;
  }

  public Kit build() {
    if (name == null || name.isBlank() || entries.isEmpty()) {
      throw new IllegalArgumentException("Informe o nome e ao menos um item");
    }
    Kit kit = new Kit(name, description);
    entries.forEach(entry -> kit.add(entry.equipment(), entry.quantity()));
    return kit;
  }

  private record Entry(Equipment equipment, int quantity) {}
}
