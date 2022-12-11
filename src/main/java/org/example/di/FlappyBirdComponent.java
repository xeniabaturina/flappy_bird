package org.example.di;


import dagger.Component;
import org.example.controller.FlappyBirdController;

import javax.inject.Singleton;

@Singleton
@Component(modules = FlappyBirdModule.class)
public interface FlappyBirdComponent {

    FlappyBirdController flappyBirdController();
}
