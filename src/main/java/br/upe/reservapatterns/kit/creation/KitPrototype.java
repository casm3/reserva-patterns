package br.upe.reservapatterns.kit.creation;

import br.upe.reservapatterns.kit.entity.Kit;
import br.upe.reservapatterns.kit.entity.KitItem;

/** Exercício 2: cópia profunda dos itens e referência aos equipamentos do catálogo. */
public class KitPrototype {
  private final Kit original;

  public KitPrototype(Kit original) {
    if  (original == null) {
      throw new IllegalArgumentException("Kit must not be null");
    }
    this.original = original;
  }

  public Kit copy() {
    Kit kitCopy = new Kit(original.getName(), original.getDescription());

    for (KitItem kitItem : original.getItems()) {
      kitCopy.add(kitItem.getEquipment(), kitItem.getQuantity());
    }

    return kitCopy;
  }
}
