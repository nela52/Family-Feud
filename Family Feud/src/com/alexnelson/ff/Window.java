package com.alexnelson.ff;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Window extends Canvas
{
	private static final long serialVersionUID = 5333043612360177485L;

	private BufferedImage icon = null;

	private BufferedImage getIcon()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			icon = loader.loadImage("/icon.png");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return icon;
	}

	public Window(int width, int height, String title, FamilyFued game)
	{
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(getIcon());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}