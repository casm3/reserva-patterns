package br.upe.reservapatterns.handover.creation;

import org.springframework.stereotype.Component;

import br.upe.reservapatterns.booking.entity.Booking;
import br.upe.reservapatterns.handover.entity.PickupTerms;
import br.upe.reservapatterns.handover.entity.ReturnTerms;

@Component("COURIER")
public class CourierHandoverFactory implements HandoverFactory {

  @Override
  public PickupTerms pickup(Booking booking, String destination) {
    String cleanDestination = validateDestination(destination);

    return new PickupTerms("COURIER", cleanDestination,1500, "Entrega" +
            " via courier mediante identificação do solicitante");
  }

  @Override
  public ReturnTerms returns(Booking booking, String destination) {
    String cleanDestination = validateDestination(destination);
    return new ReturnTerms("COURIER", cleanDestination,booking.getEndsAt(),
            "Devolução via courier: recolhimento dos equipamentos no local informado");
  }

  private String validateDestination(String destination) {
    if (destination == null || destination.trim().isEmpty()) {
      throw new IllegalArgumentException("O destino é obrigatório para entrega por courier.");
    }

    String cleanDestination = destination.trim();

    if (cleanDestination.length() > 200) {
      throw new IllegalArgumentException("O destino não pode exceder 200 caracteres.");
    }

    return cleanDestination;
  }
}
