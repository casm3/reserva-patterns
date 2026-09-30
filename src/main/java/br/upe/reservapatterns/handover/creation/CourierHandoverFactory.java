package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COURIER")
public class CourierHandoverFactory implements HandoverFactory {

  private String sanitizeDestination(String destination) {
    if (destination == null || destination.isBlank()) {
      throw new IllegalArgumentException("O destino é obrigatório para envio por mensageiro");
    }
    String cleaned = destination.trim();
    if (cleaned.length() > 200) {
      throw new IllegalArgumentException("O destino não pode ter mais de 200 caracteres");
    }
    return cleaned;
  }
  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    String location = sanitizeDestination(destination);
    return new PickupTerms(
    "COURIER",
    location,
    1500,
    "Entrega mediante identifição do solicitante"
    );
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    String location = sanitizeDestination(destination);
    return new ReturnTerms(
        "COURIER",
        location,
        booking.getEndsAt(),
        "Recolhimento agendado no mesmo endereço de entrega"
    );
  }
}
