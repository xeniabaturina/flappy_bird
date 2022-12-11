package org.example.di;

import dagger.Module;
import dagger.Provides;
import org.example.logic.ChangeWorld;
import org.example.logic.Painter;
import org.example.logic.column_manager.ColumnManager;
import org.example.logic.column_manager.MovableColumnManager;
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
    static ChangeWorld provideChangeWorld(
            Game game,
            ColumnManager columnManager,
            Bird bird,
            Screen screen
    ) {
        return ChangeWorld
                .Builder
                .newInstance(game, columnManager, bird, screen)
                .setSpeed(3)
                .build();
    }

    @Singleton
    @Provides
    static Painter providePainter(
            Game game,
            ColumnManager columnManager,
            Bird bird,
            Screen screen
    ) {
        return new Painter(game, columnManager, bird, screen);
    }

    @Singleton
    @Provides
    static ColumnManager provideColumnManager(
            Screen screen
    ) {
        return new MovableColumnManager(screen);
    }
}
