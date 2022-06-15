package pl.edu.mimuw.bajtTrade.symulacja;

import java.util.ArrayList;
import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.Giełda;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertRobotnika;
import pl.edu.mimuw.bajtTrade.symulacja.giełda.oferty.ZeznanieOfert.ZeznanieOfertSpekulanta;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;
import pl.edu.mimuw.bajtTrade.symulacja.historia.OpisDnia;

public class Symulacja {
  private Historia historia;
  private final int długość;
  private final Giełda giełda;
  private List<Robotnik> robotnicy;
  private List<Spekulant> spekulanci;
  private final int karaZaBrakUbrań;
  private int dzień = 0;

  public Symulacja(int długość, Giełda giełda, List<Robotnik> robotnicy, List<Spekulant> spekulanci,
      int karaZaBrakUbrań, OpisDnia dzień0) {
    this.historia = new Historia();
    this.długość = długość;
    this.giełda = giełda;
    this.robotnicy = robotnicy;
    this.spekulanci = spekulanci;
    this.karaZaBrakUbrań = karaZaBrakUbrań;

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
        zeznaniaRobotników.add((ZeznanieOfertRobotnika) robotnik.rozegrajDzień(dzień, historia));
    }

    for (Spekulant spekulant : spekulanci) {
      zeznaniaSpekulantów.add((ZeznanieOfertSpekulanta) spekulant.rozegrajDzień(dzień, historia));
    }
  }
}
