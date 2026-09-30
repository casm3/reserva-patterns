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
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Nome do kit não pode ser vazio");
    }
    this.name = name.trim();
  }

  public KitBuilder description(String description) {
    this.description = description == null ? "" : description;
    return this;
  }

  public KitBuilder add(Equipment equipment, int quantity) {
    if (equipment == null) {
      throw new IllegalArgumentException("Equipamento não pode ser nulo");
    }
    if(quantity <= 0 || quantity > equipment.getTotalUnits()) {
      throw new IllegalArgumentException("Quantidade inválida ou fora do estoque");
    }
    boolean exists = entries.stream()
        .anyMatch(entry -> entry.equipment().equals(equipment)
            || (entry.equipment().getId() != null && entry.equipment().getId().equals(equipment.getId()))
            || entry.equipment().getName().equalsIgnoreCase(equipment.getName()));
    if (exists) {
      throw new IllegalArgumentException("Equipamento repetido no kit");
    }
    entries.add(new Entry(equipment, quantity));
    return this;
  }

  public Kit build() {
    if (entries.isEmpty()) {
      throw new IllegalArgumentException("O kit deve conter pelo menos um item");
    }
    Kit kit = new Kit(this.name, this.description);
    for (Entry entry : entries) {
      kit.add(entry.equipment(), entry.quantity());
    }
    return kit;
  }

  private record Entry(Equipment equipment, int quantity) {}
}
