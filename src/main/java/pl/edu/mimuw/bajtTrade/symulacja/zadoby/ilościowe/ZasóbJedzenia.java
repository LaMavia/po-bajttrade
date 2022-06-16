package pl.edu.mimuw.bajtTrade.symulacja.zadoby.ilościowe;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import pl.edu.mimuw.bajtTrade.symulacja.Symulacja.KonfiguracjaSymulacji;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.Agent;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbIlościowy;

public class ZasóbJedzenia extends ZasóbIlościowy {

  public ZasóbJedzenia(Agent właściciel_) {
    super(właściciel_, TypyZasobów.Jedzenie);
  }

  @Override
  public void zużyj(double ilość, Robotnik robotnik, Map<TypyZasobów, Integer> premie,
      KonfiguracjaSymulacji konfiguracja) {
    Function<Integer, ?> dodajDoWszystkich = (premia) -> {
      for (Entry<TypyZasobów, Integer> wejście : premie.entrySet()) {
        premie.put(wejście.getKey(), wejście.getValue() + premia);
      }

      return null;
    };

    switch (typ) {
      case Jedzenie: {
        if (ilość() < ilość) {
          robotnik.dodajDzieńBezJedzenia();

          switch (robotnik.dniBezJedzenia()) {
            case 1:
              dodajDoWszystkich.apply(-100);
              break;
            case 2:
              dodajDoWszystkich.apply(-300);
              break;
          }
        } else {
          robotnik.zresetujDniBezJedzenia();
        }

        break;
      }

      default:
        break;
    }

    super.zużyj(ilość, robotnik, premie, konfiguracja);
  }

}
