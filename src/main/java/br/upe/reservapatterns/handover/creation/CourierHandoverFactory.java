package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COURIER")
public class CourierHandoverFactory implements HandoverFactory {
  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    return new PickupTerms(
            "COURIER",
            destination.trim(),
            1500,
            "Entrega mediante identificação do solicitante"
    );
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    return new ReturnTerms(
            "COURIER",
            destination.trim(),
            booking.getEndsAt(),
            "Prepare todos os itens para recolhimento"
    );
  }
}
