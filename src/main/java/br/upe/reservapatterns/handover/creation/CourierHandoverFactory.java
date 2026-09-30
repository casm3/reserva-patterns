package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COURIER")
public class CourierHandoverFactory implements HandoverFactory {
  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    String location = validateDestination(destination);

    return new PickupTerms(
            "COURIER",
            location,
            1500,
            "Entrega mediante identificação do solicitante"
    );
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    String location = validateDestination(destination);

    return new ReturnTerms(
            "COURIER",
            location,
            booking.getEndsAt(),
            "Prepare todos os itens para recolhimento"
    );
  }

  private String validateDestination(String destination) {
    if (destination == null || destination.isBlank()) {
      throw new IllegalArgumentException("Destino obrigatório");
    }

    String location = destination.trim();

    if (location.length() > 200) {
      throw new IllegalArgumentException("Destino deve ter até 200 caracteres");
    }

    return location;
  }
}