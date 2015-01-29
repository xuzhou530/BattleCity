package org.cachb.battlecity;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class StartActivity extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 320;

	private Camera mCamera;
	private Texture mTexture;
	private TextureRegion mSplashTextureRegion;

	@Override
	public Engine onLoadEngine() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mCamera));
	}

	@Override
	public void onLoadResources() {
		this.mTexture = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mSplashTextureRegion = TextureRegionFactory.createFromAsset(
				this.mTexture, this, "gfx/Splashscreen.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(this.mTexture);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		final Scene scene = new Scene(1);
		final int centerX = (CAMERA_WIDTH - this.mSplashTextureRegion
				.getWidth()) / 2;
		final int centerY = (CAMERA_HEIGHT - this.mSplashTextureRegion
				.getHeight()) / 2;
		final Sprite splash = new Sprite(centerX, centerY,
				this.mSplashTextureRegion);
		scene.getLastChild().attachChild(splash);
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
	}

}
