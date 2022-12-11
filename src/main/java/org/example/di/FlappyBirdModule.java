package org.example.di;

import dagger.Module;
import dagger.Provides;
import org.example.logic.ChangeWorld;
import org.example.logic.ColumnManager;
import org.example.model.Bird;
import org.example.model.Game;
import org.example.model.Screen;

import javax.inject.Singleton;

@Module
public interface FlappyBirdModule {
    @Singleton
    @Provides
    static Bird providesBird() {
        return new Bird();
    }

    @Provides
    @Singleton
    static Screen provideScreen() {
        return new Screen();
    }

    @Provides
    @Singleton
    static Game provideGame() {
        return new Game();
    }

    @Provides
    @Singleton
    static ChangeWorld provideChangeWorld() {
        return new ChangeWorld();
    }

    @Singleton
    @Provides
    static ColumnManager provideColumnManager() {
        return new ColumnManager();
    }
}
