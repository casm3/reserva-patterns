package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COUNTER")
public class CounterHandoverFactory implements HandoverFactory {
  private static final String LOCATION = "Laboratório central";

  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    return new PickupTerms(
        "COUNTER",
        LOCATION,
        0,
        "Retirada presencial no balcão de atendimento"
    );
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    return new ReturnTerms(
        "COUNTER",
        LOCATION,
        booking.getEndsAt(),
        "Devolução presencial no balcão de atendimento"
    );
  }
}

