package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.HandoverMode;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COURIER")
public class CourierHandoverFactory implements HandoverFactory {
  private static final int FEE_CENTS = 1500;
  private static final int MAX_DESTINATION = 200;

  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    return new PickupTerms(
            HandoverMode.COURIER.name(), validDestination(destination), FEE_CENTS,
            "Entrega mediante identificação do solicitante");
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    return new ReturnTerms(
            HandoverMode.COURIER.name(), validDestination(destination), booking.getEndsAt(),
            "Prepare todos os itens para recolhimento");
  }

  private static String validDestination(String destination) {
    if (destination == null || destination.isBlank()) {
      throw new IllegalArgumentException("O destino é obrigatório para entrega por mensageiro");
    }
    String trimmed = destination.trim();
    if (trimmed.length() > MAX_DESTINATION) {
      throw new IllegalArgumentException("O destino deve ter até 200 caracteres");
    }
    return trimmed;
  }
}
