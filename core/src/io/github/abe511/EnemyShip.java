package io.github.abe511;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

class EnemyShip extends Ship {

    public EnemyShip(float xCenter, float yCenter,
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
        lasers[0] = new Laser(xPosition + width * 0.18f, yPosition - height,
                laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);
        lasers[1] = new Laser(xPosition + width * 0.82f, yPosition - height,
                laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);
        timeSinceLastShot = 0;
        return lasers;
    }
}
