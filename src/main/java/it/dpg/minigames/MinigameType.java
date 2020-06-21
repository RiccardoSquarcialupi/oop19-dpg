package it.dpg.minigames;

import it.dpg.minigames.ballgame.controller.BallMinigame;
import it.dpg.minigames.base.controller.Minigame;
import it.dpg.minigames.molegame.MoleMiniGame;
import it.dpg.minigames.punchygame.PunchyMinigame;

import java.util.function.Supplier;

public enum MinigameType {

    BALLGAME(BallMinigame::new),
    PUNCHYGAME(PunchyMinigame::new),
    MOLEGAME(MoleMiniGame::new);

    private final Supplier<Minigame> implementationSupplier;

    MinigameType(Supplier<Minigame> implementationSupplier) {
        this.implementationSupplier = implementationSupplier;
    }

    Minigame getMinigame() {
        return implementationSupplier.get();
    }
}
