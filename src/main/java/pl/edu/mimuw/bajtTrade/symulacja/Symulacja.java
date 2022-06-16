package pl.edu.mimuw.bajtTrade.symulacja;

import java.util.ArrayList;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.OpisDnia;

public class Symulacja {
  public static class KonfiguracjaSymulacji {
    private final int długość;
    private final int karaZaBrakUbrań;

    public KonfiguracjaSymulacji(int długość, int karaZaBrakUbrań) {
      this.długość = długość;
      this.karaZaBrakUbrań = karaZaBrakUbrań;
    }

    public int długość() {
      return długość;
    }

    public int karaZaBrakUbrań() {
      return karaZaBrakUbrań;
    }
  }

  private Historia historia;
  private final Giełda giełda;
  private List<Robotnik> robotnicy;
  private List<Spekulant> spekulanci;
  private int dzień = 0;
  private final KonfiguracjaSymulacji konfiguracja;

  public Symulacja(Giełda giełda, List<Robotnik> robotnicy, List<Spekulant> spekulanci,
      OpisDnia dzień0, KonfiguracjaSymulacji konfiguracja) {
    this.historia = new Historia();
    this.giełda = giełda;
    this.robotnicy = robotnicy;
    this.spekulanci = spekulanci;
    this.konfiguracja = konfiguracja;

    historia.zapiszDzień(dzień0.ustawDzień(0));
  }

  public void rozpocznij() {
    
  }

  private void zasymulujDzień() {
    dzień++;

    List<ZeznanieOfertRobotnika> zeznaniaRobotników = new ArrayList<>();
    List<ZeznanieOfertSpekulanta> zeznaniaSpekulantów = new ArrayList<>();

    for (Robotnik robotnik : robotnicy) {
      if (robotnik.żywy())
        zeznaniaRobotników.add((ZeznanieOfertRobotnika) robotnik.rozegrajDzień(dzień, historia, konfiguracja));
    }

    for (Spekulant spekulant : spekulanci) {
      zeznaniaSpekulantów.add((ZeznanieOfertSpekulanta) spekulant.rozegrajDzień(dzień, historia, konfiguracja));
    }
  }
}
