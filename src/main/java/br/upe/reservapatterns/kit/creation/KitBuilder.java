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
    this.description = description == null ? "" : description;
    return this;
  }

  public KitBuilder add(Equipment equipment, int quantity) {
    if (equipment == null) {
      throw new IllegalArgumentException("Equipamento obrigatório");
    }
    if (quantity < 1 || quantity > equipment.getTotalUnits()) {
      throw new IllegalArgumentException("Quantidade fora do estoque");
    }
    for (Entry entry : entries) {
      if (equipment == entry.equipment()) {
        throw new IllegalArgumentException("Equipamento repetido");
      }
    }
    entries.add(new Entry(equipment, quantity));
    return this;
  }

  public Kit build() {
    if (entries.isEmpty()) {
      throw new IllegalArgumentException("Kit precisa de ao menos um item");
    }
    Kit kit = new Kit(name, description);
    for (Entry entry : entries) {
      kit.add(entry.equipment(), entry.quantity());
    }
    return kit;
  }

  private record Entry(Equipment equipment, int quantity) {}
}
