package br.upe.reservapatterns.handover.creation;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;
import org.springframework.stereotype.Component;

@Component("COURIER")
public class CourierHandoverFactory implements HandoverFactory {
  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    String address = address(booking, destination);
    return new PickupTerms("COURIER", address, 1500,
            "Entrega mediante identificação do solicitante");
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    String address = address(booking, destination);
    return new ReturnTerms("COURIER", address, booking.getEndsAt(),
            "Prepare todos os itens para recolhimento");
  }

  private String address(Booking booking, String destination) {
    if (booking == null || destination == null || destination.isBlank()
            || destination.trim().length() > 200) {
      throw new IllegalArgumentException("Informe um endereço de até 200 caracteres");
    }
    return destination.trim();
  }
}
