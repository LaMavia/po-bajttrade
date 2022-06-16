package pl.edu.mimuw.bajtTrade.symulacja.giełda;

import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;

public class Zrównoważona extends Giełda {

  public Zrównoważona(int numerDnia) {
    super(numerDnia);
  }

  @Override
  protected Iterable<ZeznanieOfertRobotnika> ustawRobotników(List<ZeznanieOfertRobotnika> ofertyRobotników) {
    if (numerDnia % 2 == 1) {
      return new GiełdaSocjalistyczna(numerDnia).ustawRobotników(ofertyRobotników);
    }

    return new GiełdaKapitalistyczna(numerDnia).ustawRobotników(ofertyRobotników);
  }

}
