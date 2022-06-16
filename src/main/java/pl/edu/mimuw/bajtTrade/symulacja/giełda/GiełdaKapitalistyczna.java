package pl.edu.mimuw.bajtTrade.symulacja.giełda;

import java.util.List;
import java.util.stream.Collectors;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;

public class GiełdaKapitalistyczna extends Giełda {

  public GiełdaKapitalistyczna(int numerDnia) {
    super(numerDnia);
  }

  @Override
  protected Iterable<ZeznanieOfertRobotnika> ustawRobotników(List<ZeznanieOfertRobotnika> ofertyRobotników) {
    return ofertyRobotników.stream().sorted((a, b) -> {
      double wa = a.wystawiający().wynik();
      double wb = b.wystawiający().wynik();

      if (wa == wb)
        return a.wystawiający().id() - b.wystawiający().id();

      return (int) Math.signum(wa - wb);
    })
        .collect(Collectors.toList());
  }

}
