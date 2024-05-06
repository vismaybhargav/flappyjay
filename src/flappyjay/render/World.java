package flappyjay.render;

import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

public class World extends Pane {
    AnimationTimer gameClock;
    public World() {
        setPrefSize(800, 600);
        gameClock = new AnimationTimer() {
            long prevTime = 0;
            long timeBetweenFrames = 16666667L;

            @Override
            public void handle(long now) {
                prevTime = now;
                act(now);

                for(Actor a : getObjects(Actor.class)) {
                    a.act(now);
                }
            }
        };
    }


    public void act(long now) {

    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        List<A> list = new ArrayList<>();
        for(Object o : getChildren()) {
            if(cls.isInstance(o)) {
                list.add(cls.cast(o));
            }
        }
        return list;
    }

    public <A extends Actor> List<A> getObjectsAt(double x, double y, Class<A> cls) {
        List<A> list = new ArrayList<>();
        for(A a : getObjects(cls)) {
            if(a.getBoundsInParent().contains(x, y)) {
                list.add(a);
            }
        }
        return list;
    }
}