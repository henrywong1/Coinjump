package com.henry.coinjump.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.soap.Text;

public class Coinjump extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] man;
	Texture bomb;
	int state;
	int pause = 0;
	float gravity = 0.2f;
	float velocity = 0;
	int manY;
	Random random;


	ArrayList<Integer> coinXs = new ArrayList<Integer>();
	ArrayList<Integer> coinYs = new ArrayList<Integer>();

	ArrayList<Integer> bombXs = new ArrayList<Integer>();
	ArrayList<Integer> bombYs = new ArrayList<Integer>();
	Texture coin;

	int coinCount;
	int bombCount;
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		man = new Texture[4];
		bomb = new Texture("bomb.png");
		man[0] = new Texture("frame-1.png");
		man[1] = new Texture("frame-2.png");
		man[2] = new Texture("frame-3.png");
		man[3] = new Texture("frame-4.png");
		manY = Gdx.graphics.getHeight() / 2;

		coin = new Texture("coin.png");
		random = new Random();
	}

	public void makeCoin() {
		float height = random.nextFloat() * Gdx.graphics.getHeight();
		coinYs.add((int)height);
		coinXs.add(Gdx.graphics.getWidth());
	}

	public void makeBomb() {
		float height = random.nextFloat() * Gdx.graphics.getHeight();
		bombYs.add((int) height);
		bombXs.add(Gdx.graphics.getWidth());
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (bombCount < 275) {
			bombCount++;
		} else {
			bombCount = 0;
			makeBomb();
		}

		for (int i = 0; i < bombXs.size(); i++) {
			batch.draw(bomb, bombXs.get(i), bombYs.get(i));
			bombXs.set(i, bombXs.get(i) - 4);
		}


		 if (coinCount < 100) {
		 	coinCount++;
		 } else {
		 	coinCount = 0;
		 	makeCoin();
		 }

		 for (int i = 0; i < coinXs.size(); i++) {
		 	batch.draw(coin, coinXs.get(i), coinYs.get(i));
		 	coinXs.set(i, coinXs.get(i) - 4);
		 }

		if (Gdx.input.justTouched()) {
			velocity = -10;
		}

		if (pause < 8) {
			pause++;
		} else {
			pause = 0;
			if (state < 3) {
				state++;
			} else {
				state = 0;
			}
		}
		velocity += gravity;
		manY -= velocity;
		if (manY <= 0 ) {
			manY = 0;
		}

		batch.draw(man[state], Gdx.graphics.getWidth() / 2 - man[state].getWidth() / 2, manY);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
