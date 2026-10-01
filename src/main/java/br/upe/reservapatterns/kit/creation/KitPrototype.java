package br.upe.reservapatterns.kit.creation;

import br.upe.reservapatterns.kit.entity.Kit;

/** Exercício 2: cópia profunda dos itens e referência aos equipamentos do catálogo. */
public class KitPrototype {
  private final Kit original;

  public KitPrototype(Kit original) {
    if (original == null) {
      throw new IllegalArgumentException();
    }
    this.original = original;
  }

  public Kit copy() {
    Kit copy = new Kit(original.getName(), original.getDescription());

    for (var item : original.getItems()) {
      copy.add(item.getEquipment(), item.getQuantity());
    }

    return copy;
  }
}