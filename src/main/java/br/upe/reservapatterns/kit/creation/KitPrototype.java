package br.upe.reservapatterns.kit.creation;

import br.upe.reservapatterns.kit.entity.Kit;
import br.upe.reservapatterns.kit.entity.KitItem;

/** Exercício 2: cópia profunda dos itens e referência aos equipamentos do catálogo. */
public class KitPrototype {
  private final Kit original;

  public KitPrototype(Kit original) {
    if (original == null) {
      throw new IllegalArgumentException("Kit de origem é obrigatório");
    }
    this.original = original;
  }

  public Kit copy() {
    Kit copy = new Kit(original.getName(), original.getDescription());
    for (KitItem item : original.getItems()) {
      // Kit.add cria um KitItem novo, reutilizando o mesmo Equipment
      copy.add(item.getEquipment(), item.getQuantity());
    }
    return copy;
  }
}

