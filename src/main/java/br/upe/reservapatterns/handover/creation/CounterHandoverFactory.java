package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.HandoverMode;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COUNTER")
public class CounterHandoverFactory implements HandoverFactory {
  private static final String LOCATION = "Laboratório central";

  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    return new PickupTerms(
            HandoverMode.COUNTER.name(), LOCATION, 0, "Retire os itens no balcão com identificação");
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    return new ReturnTerms(
            HandoverMode.COUNTER.name(), LOCATION, booking.getEndsAt(),
            "Devolva todos os itens no balcão até o prazo");
  }
}


