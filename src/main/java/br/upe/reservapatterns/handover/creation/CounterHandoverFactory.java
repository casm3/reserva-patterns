package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COUNTER")
public class CounterHandoverFactory implements HandoverFactory {
  private static final String METHOD = "COUNTER";
  private static final String LOCATION = "Laboratório central";

  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    return new PickupTerms(METHOD, LOCATION, 0,
            "Retire os itens no balcão mediante identificação do solicitante");
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    return new ReturnTerms(METHOD, LOCATION, booking.getEndsAt(),
            "Devolva todos os itens no balcão do laboratório");
  }
}
