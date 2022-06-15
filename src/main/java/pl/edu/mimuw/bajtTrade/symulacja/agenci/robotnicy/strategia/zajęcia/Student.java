package pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.zajęcia;

import java.util.Hashtable;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.strategia.StrategiaZajęcia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.TypyZasobów;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.Zasób;
import pl.edu.mimuw.bajtTrade.symulacja.zadoby.ZasóbIlościowy;

public class Student implements StrategiaZajęcia {
  private double zapas;
  private int okres;

  public Student(double zapas, int okres) {
    this.zapas = zapas;
    this.okres = okres;
  }

  private double średniaCenaJedzenia(int numerDnia, Historia historia) {
    int n = Math.min(okres, numerDnia);
    double suma = 0.;

    for (int di = 1; di <= n; di++) {
      suma += historia.dajDzień(numerDnia - di).dajCenęŚrednią(TypyZasobów.Jedzenie);
    }

    return suma / n;
  }

  @Override
  public boolean czySięUczy(Robotnik robotnik, Hashtable<TypyZasobów, Zasób> zasoby, int numerDnia, Historia historia) {
    double kursJedzenia = średniaCenaJedzenia(numerDnia, historia);
    double posiadaneDiamenty = ((ZasóbIlościowy) zasoby.get(TypyZasobów.Diamenty)).ilość();

    return posiadaneDiamenty >= 100 * zapas * kursJedzenia;
  }

}
