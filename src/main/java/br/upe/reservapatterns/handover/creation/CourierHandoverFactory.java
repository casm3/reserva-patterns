package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COURIER")
public class CourierHandoverFactory implements HandoverFactory {

  private String validateAndCleanDestination(String destination) {
    if (destination == null || destination.isBlank()) {
      throw new IllegalArgumentException("Destino é obrigatório para o modo COURIER");
    }
    String trimmed = destination.trim();
    if (trimmed.length() > 200) {
      throw new IllegalArgumentException("O destino deve ter no máximo 200 caracteres");
    }
    return trimmed;
  }

  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    String cleanDestination = validateAndCleanDestination(destination);
    return new PickupTerms(
            "COURIER",
            cleanDestination,
            1500,
            "Entrega mediante identificação do solicitante"
    );
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    if (booking == null) {
      throw new IllegalArgumentException("Reserva não pode ser nula");
    }
    String cleanDestination = validateAndCleanDestination(destination);
    return new ReturnTerms(
            "COURIER",
            cleanDestination,
            booking.getEndsAt(),
            "Prepare todos os itens para recolhimento"
    );
  }
}