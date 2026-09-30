package br.upe.reservapatterns.booking.creation;

import java.time.LocalDateTime;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.kit.entity.Kit;
import br.upe.reservapatterns.kit.entity.KitItem;
import br.upe.reservapatterns.staff.entity.StaffUser;

/** Exercício 3: fluxo comum e método de fábrica sobrescrito. */
public abstract class BookingCreator {
  public final Booking open(Kit kit, StaffUser requester, LocalDateTime startsAt) {

    if (kit == null || requester == null || startsAt == null){
      throw new IllegalArgumentException("Ta nulo papai");
    }

    if(kit.getItems() == null || kit.getItems().isEmpty()){
      throw new IllegalArgumentException("Ta nulo ou vazio ai papai");
    }
    Booking booking = create(kit, requester, startsAt);

    for (KitItem item : kit.getItems()) {
      booking.addItem(item.getEquipment(), item.getQuantity());
    }

    return booking;
  }
  protected abstract Booking create(Kit kit, StaffUser requester, LocalDateTime startsAt);
}

