package pl.edu.mimuw.bajtTrade.symulacja.giełda;

import java.util.List;

import pl.edu.mimuw.bajtTrade.symulacja.agenci.robotnicy.Robotnik;
import pl.edu.mimuw.bajtTrade.symulacja.agenci.spekulanci.Spekulant;
import pl.edu.mimuw.bajtTrade.symulacja.historia.Historia;

public abstract class Giełda {
  public abstract void symulujDzień(int numerDnia, Historia historia, List<Robotnik> robotnicy,
      List<Spekulant> spekulanci);
}
