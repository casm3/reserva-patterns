package br.upe.reservapatterns.kit.creation;

import br.upe.reservapatterns.kit.entity.Kit;
import br.upe.reservapatterns.kit.entity.KitItem;

/** Exercício 2: cópia profunda dos itens e referência aos equipamentos do catálogo. */
public class KitPrototype {
  private final Kit original;

  public KitPrototype(Kit original) {
    if (original == null) {
      throw new IllegalArgumentException("O kit original não pode ser nulo");
    }
    this.original = original;
  }

  public Kit copy() {
    Kit copy = new Kit(original.getName(), original.getDescription());

    for (KitItem item : original.getItems()) {
      copy.add(item.getEquipment(), item.getQuantity());
    }
    return copy;
  }
}
