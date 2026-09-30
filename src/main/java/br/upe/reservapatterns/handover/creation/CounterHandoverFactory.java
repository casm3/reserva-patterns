package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COUNTER")
public class CounterHandoverFactory implements HandoverFactory {
  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    return new PickupTerms(
            "COUNTER",
            "Laboratório central",
            0,
            "Retirada mediante identificação do solicitante"
    );
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    return new ReturnTerms(
            "COUNTER",
            "Laboratório central",
            booking.getEndsAt(),
            "Devolução no balcão do laboratório"
    );
  }
}
