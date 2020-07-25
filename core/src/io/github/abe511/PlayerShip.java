package io.github.abe511;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

class PlayerShip extends Ship {

    public PlayerShip(float xCenter, float yCenter,
                      float height, float width,
                      float movementSpeed, int shield,
                      float laserWidth, float laserHeight,
                      float laserMovementSpeed, float timeBetweenShots,
                      TextureRegion shipTextureRegion,
                      TextureRegion shieldTextureRegion,
                      TextureRegion laserTextureRegion) {
        super(xCenter, yCenter, height, width, movementSpeed, shield,
                laserWidth, laserHeight, laserMovementSpeed, timeBetweenShots,
                shipTextureRegion, shieldTextureRegion, laserTextureRegion);
    }

    @Override
    public Laser[] fireLasers() {
        Laser[] lasers = new Laser[2];
        lasers[0] = new Laser(xPosition + width * 0.03f, yPosition + height * 0.6f,
                laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);
        lasers[1] = new Laser(xPosition + width * 0.97f, yPosition + height * 0.6f,
                laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);
        timeSinceLastShot = 0;
        return lasers;
    }
}
