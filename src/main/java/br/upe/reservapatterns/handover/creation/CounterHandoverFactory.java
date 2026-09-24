package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COUNTER")
public class CounterHandoverFactory implements HandoverFactory {
  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    if (booking == null) {
      throw new IllegalArgumentException("Reserva obrigatória");
    }
    return new PickupTerms("COUNTER", "Laboratório central", 0,
            "Apresente identificação na retirada");
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    if (booking == null) {
      throw new IllegalArgumentException("Reserva obrigatória");
    }
    return new ReturnTerms("COUNTER", "Laboratório central", booking.getEndsAt(),
            "Devolva todos os itens no balcão");
  }
}
