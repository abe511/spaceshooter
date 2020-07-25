package io.github.abe511;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Laser {

    //    position and dimensions
    float width, height;
    float xPosition, yPosition; // center bottom

    //    laser physics
    float shotSpeed; // world units per second

    //    texture
    TextureRegion textureRegion;

    public Laser(float xCenter, float yCenter,
                 float width, float height,
                 float shotSpeed,
                 TextureRegion textureRegion) {
        this.xPosition = xCenter - width * 0.5f;
        this.yPosition = yCenter - height * 0.5f;
        this.width = width;
        this.height = height;
        this.shotSpeed = shotSpeed;
        this.textureRegion = textureRegion;
    }

    public void draw(Batch batch) {
        batch.draw(textureRegion, xPosition, yPosition, width, height);
    }
}
