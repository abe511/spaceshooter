package io.github.abe511;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

abstract class Ship {
    //    ship parameters
    float movementSpeed; // world units per second
    int shield;

    //    position and dimensions
    float  xPosition, yPosition; // lower-left corner
    float height, width;

    //    laser parameters
    float laserWidth, laserHeight;
    float laserMovementSpeed;
    float timeBetweenShots;
    float timeSinceLastShot = 0;

    //    graphics
    TextureRegion shipTextureRegion, shieldTextureRegion, laserTextureRegion;

    public Ship(float xCenter, float yCenter,
                float height, float width,
                float movementSpeed, int shield,
                float laserWidth, float laserHeight,
                float laserMovementSpeed, float timeBetweenShots,
                TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion,
                TextureRegion laserTextureRegion) {
        this.movementSpeed = movementSpeed;
        this.shield = shield;
        this.xPosition = xCenter - width * 0.5f;
        this.yPosition = yCenter - height * 0.5f;
        this.height = height;
        this.width = width;
        this.laserWidth = laserWidth;
        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
        this.timeBetweenShots = timeBetweenShots;
        this.shipTextureRegion = shipTextureRegion;
        this.shieldTextureRegion = shieldTextureRegion;
        this.laserTextureRegion = laserTextureRegion;
    }

    public void update(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    public boolean canFire() {
        return (timeSinceLastShot - timeBetweenShots) >= 0;
    }

    public abstract Laser[] fireLasers();

    public void draw (Batch batch) {
        batch.draw(shipTextureRegion, xPosition, yPosition, width, height);
        if(shield > 0) {
            batch.draw(shieldTextureRegion, xPosition, yPosition, width, height);
        }
    }
}
