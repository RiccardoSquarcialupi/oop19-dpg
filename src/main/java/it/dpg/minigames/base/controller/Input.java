package it.dpg.minigames.base.controller;

public interface Input<R, M> {
    R execute(M model);
}
