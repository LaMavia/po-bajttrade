package pl.edu.mimuw.bajtTrade.symulacja.giełda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;

public class GiełdaSocjalistyczna extends GiełdaKapitalistyczna {
  public GiełdaSocjalistyczna(int numerDnia) {
    super(numerDnia);
  }

  @Override
  protected Iterable<ZeznanieOfertRobotnika> ustawRobotników(List<ZeznanieOfertRobotnika> ofertyRobotników) {
    List<ZeznanieOfertRobotnika> zeznania = new ArrayList<>();

    for (ZeznanieOfertRobotnika zeznanie : super.ustawRobotników(ofertyRobotników)) {
      zeznania.add(zeznanie);
    }

    Collections.reverse(zeznania);

    return zeznania;
  }
}
