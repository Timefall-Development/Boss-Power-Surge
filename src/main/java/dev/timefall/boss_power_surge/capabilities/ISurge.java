package dev.timefall.boss_power_surge.capabilities;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface ISurge extends Component {
  /* Vanilla Bosses and Mini Bosses:
        Ender Dragon
        Wither
        Warden
        Elder Guardian TODO
     */

     int getInitialDragon();
     void setInitialDragon(int initialDragon);
     int getDragonsKilled();
     void setDragonsKilled(int dragonsKilled);
     int getWithersSpawned();
     void setWithersSpawned(int withersSpawned);
     int getWardensSpawned();
     void setWardensSpawned(int wardensSpawned);
}
