package io.github.abe511;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

class GameScreen implements Screen {

    //    screen
    private Camera camera;
    private Viewport viewport;

    //    graphics
    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private TextureRegion[] backgrounds;
    private float backgroundHeight; // in world units
    private TextureRegion playerShipTextureRegion, playerShieldTextureRegion,
            enemyShipTextureRegion, enemyShieldTextureRegion,
            playerLaserTextureRegion, enemyLaserTextureRegion;

    //    timing
    private float[] backgroundOffsets = {0,0,0,0};
    private float backgroundMaxScrollingSpeed;

    //    world parameters
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    //    game objects
    private Ship playerShip;
    private Ship enemyShip;
    private LinkedList<Laser> playerLaserList;
    private LinkedList<Laser> enemyLaserList;

    GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //  texture atlas setup
        textureAtlas = new TextureAtlas("images.atlas");

        //  background setup
        backgrounds = new TextureRegion[4];
        backgrounds[0] = textureAtlas.findRegion("Starscape00");
        backgrounds[1] = textureAtlas.findRegion("Starscape01");
        backgrounds[2] = textureAtlas.findRegion("Starscape02");
        backgrounds[3] = textureAtlas.findRegion("Starscape03");

        backgroundHeight = WORLD_HEIGHT * 2;
        backgroundMaxScrollingSpeed = (float) (WORLD_HEIGHT * 0.25);

        //  initialize texture regions
        playerShipTextureRegion = textureAtlas.findRegion("playerShip1_red");
        enemyShipTextureRegion = textureAtlas.findRegion("enemyGreen3");
        playerShieldTextureRegion = textureAtlas.findRegion("shield3");
        enemyShieldTextureRegion= textureAtlas.findRegion("shield2");
        enemyShieldTextureRegion.flip(false, true);
        playerLaserTextureRegion= textureAtlas.findRegion("laserRed04");
        enemyLaserTextureRegion = textureAtlas.findRegion("laserGreen08");

        //  game objects setup
        playerShip = new PlayerShip(WORLD_WIDTH * 0.5f, WORLD_HEIGHT * 0.1f,
                10, 10, 2, 3,
                0.4f, 4, 45, 0.05f,
                playerShipTextureRegion, playerShieldTextureRegion, playerLaserTextureRegion);
        enemyShip = new EnemyShip(WORLD_WIDTH * 0.5f, WORLD_HEIGHT * 0.9f,
                10, 10, 1, 1,
                0.4f, 4, 50, 0.07f,
                enemyShipTextureRegion, enemyShieldTextureRegion, enemyLaserTextureRegion);
        playerLaserList = new LinkedList<>();
        enemyLaserList = new LinkedList<>();


        batch = new SpriteBatch();
    }


    @Override
    public void render(float deltaTime) {
        batch.begin();

        //  scrolling background

        renderBackground(deltaTime);

        //  enemy ships
        enemyShip.draw(batch);
        //  player ship
        playerShip.draw(batch);
        //  lasers

        //  explosions


        batch.end();
    }

    private void renderBackground(float deltaTime) {
        backgroundOffsets[0] += deltaTime * backgroundMaxScrollingSpeed * 0.1;
        backgroundOffsets[1] += deltaTime * backgroundMaxScrollingSpeed * 0.4;
        backgroundOffsets[2] += deltaTime * backgroundMaxScrollingSpeed * 0.8;
        backgroundOffsets[3] += deltaTime * backgroundMaxScrollingSpeed;

        for(int layer = 0; layer < backgroundOffsets.length; layer++) {
            if(backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }
}
