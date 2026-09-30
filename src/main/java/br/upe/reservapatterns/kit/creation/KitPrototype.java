package br.upe.reservapatterns.kit.creation;

import br.upe.reservapatterns.equipment.entity.Equipment;
import br.upe.reservapatterns.kit.entity.Kit;
import br.upe.reservapatterns.kit.entity.KitItem;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Null;

import java.util.ArrayList;
import java.util.List;

/** Exercício 2: cópia profunda dos itens e referência aos equipamentos do catálogo. */
public class KitPrototype {
  private final Kit original;

  public KitPrototype(Kit original) {
    if (original == null){
      throw new IllegalArgumentException("Inválido");
    }
    this.original = original;
  }

  public Kit copy() {
    Kit copiado = new Kit(original.getName(), original.getDescription());
    List<KitItem>novosItens= new ArrayList<>();
    for (KitItem item: original.getItems()){
      copiado.add(item.getEquipment(), item.getQuantity());
      }
    return copiado;
  }
}
