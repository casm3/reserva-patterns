package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COURIER")
public class CourierHandoverFactory implements HandoverFactory {
  private static final String METHOD = "COURIER";
  private static final int FEE_CENTS = 1500;
  private static final int MAX_DESTINATION_LENGTH = 200;

  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    return new PickupTerms(METHOD, location(destination), FEE_CENTS,
            "Entrega mediante identificação do solicitante");
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    return new ReturnTerms(METHOD, location(destination), booking.getEndsAt(),
            "Prepare todos os itens para recolhimento");
  }

  private String location(String destination) {
    if (destination == null || destination.isBlank()) {
      throw new IllegalArgumentException("Destino obrigatório");
    }
    String trimmed = destination.trim();
    if (trimmed.length() > MAX_DESTINATION_LENGTH) {
      throw new IllegalArgumentException("Destino deve ter até 200 caracteres");
    }
    return trimmed;
  }
}
