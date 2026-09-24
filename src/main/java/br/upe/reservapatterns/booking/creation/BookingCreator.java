package br.upe.reservapatterns.booking.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.kit.entity.Kit;
import br.upe.reservapatterns.kit.entity.KitItem;
import br.upe.reservapatterns.staff.entity.StaffUser;
import java.time.LocalDateTime;

/** Exercício 3: fluxo comum e método de fábrica sobrescrito. */
public abstract class BookingCreator {
  public final Booking open(Kit kit, StaffUser requester, LocalDateTime startsAt) {
    if (kit == null || requester == null || startsAt == null || kit.getItems().isEmpty()) {
      throw new IllegalArgumentException("Reserva precisa de kit, solicitante, início e itens");
    }
    Booking booking = create(kit, requester, startsAt);
    if (booking == null) {
      throw new IllegalStateException("Método de fábrica não criou a reserva");
    }
    for (KitItem item : kit.getItems()) {
      booking.addItem(item.getEquipment(), item.getQuantity());
    }
    return booking;
  }

  protected abstract Booking create(Kit kit, StaffUser requester, LocalDateTime startsAt);
}
