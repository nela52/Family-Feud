package com.alexnelson.ff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.HashMap;

public class SuddenDeathRound
{
	private boolean isStarted = false;

	private String person1;
	private String person2;

	private String buzzerWinner = null;
	private Family currentFamily = null;

	private Question question;

	private BufferedImage ans1 = null;
	private BufferedImage ans1f = null;
	private boolean answ1 = false;
	private boolean rans1 = false;

	private boolean answer1 = false;
	private boolean renderAnswer1 = false;

	private BufferedImage strike = null;

	private boolean renderQuestion = false;

	private boolean tick = false;
	private boolean render = false;

	private boolean renderRed = false;
	private int renderedRedTimes = 1;

	private long renderedStrikeTime;

	private int strikes = 0;

	private boolean strike1, strike2, strike3 = false;

	public String answer = "";

	private HashMap<Integer, Answer> answerNums = new HashMap<Integer, Answer>();

	private boolean roundOver = false;
	private boolean roundDone = false;

	private boolean stopKeyBoardInput = false;

	public SuddenDeathRound()
	{
		question = FamilyFued.getQuestion();

		for (int i = 0; i < question.getAnswers().size(); i++)
		{
			answerNums.put(i + 1, question.getAnswers().get(i));
		}

		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			ans1 = loader.loadImage("/1.png");

			ans1f = loader.loadImage("/1f.png");

			strike = loader.loadImage("/strike.png");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		for (int i = 1; i <= question.getAnswers().size(); i++)
		{
			if (i == 1)
			{
				answ1 = true;
				answer1 = true;
			}
		}
	}

	public void start()
	{
		FamilyFued.stage = Stage.BUZZER;

		isStarted = true;
		tick = true;
		render = true;

		FamilyFued.getBuzzerMembers();
		setBuzzerMember1(FamilyFued.buzzerMember1);
		setBuzzerMember2(FamilyFued.buzzerMember2);
	}

	public void stop()
	{
		tick = false;
		render = false;

		isStarted = false;

		person1 = null;
		person2 = null;

		buzzerWinner = null;
		currentFamily = null;

		question = null;

		answ1 = false;

		rans1 = false;

		answer1 = false;

		renderAnswer1 = false;

		renderQuestion = false;

		tick = false;
		render = false;

		renderRed = false;
		renderedRedTimes = 1;

		renderedStrikeTime = 0;

		strikes = 0;

		strike1 = false;
		strike2 = false;
		strike3 = false;

		answer = "";

		answerNums.clear();

		roundOver = false;
		roundDone = false;

		stopKeyBoardInput = false;

		time = 0;

		overTime = 0;
	}

	int time = 0;

	public void tick()
	{
		if (!tick)
		{
			return;
		}

		if (roundOver)
		{
			onRoundOver();
		}

		time++;
		if (time < 10)
		{
			return;
		}

		time = 0;

		if (answ1)
		{
			KeyInput.canType = false;

			rans1 = true;
			answ1 = false;
			FamilyFued.playSound("Answer.wav");
			FamilyFued.stage = Stage.BUZZER;
			return;
		}

		renderQuestion = true;

		if (stopKeyBoardInput)
		{
			KeyInput.canType = false;
		} else
		{
			KeyInput.canType = true;
		}

		if (buzzerWinner != null)
		{
			if (renderedRedTimes >= 5)
			{
				if (renderRed)
				{
					renderRed = false;
				}
				return;
			}

			renderedRedTimes++;

			renderRed = !renderRed;
			return;
		}
	}

	public void render(Graphics g, ImageObserver ob)
	{
		if (!render)
		{
			return;
		}

		if (rans1)
		{
			g.drawImage(ans1, 0, 0, ob);
		}

		if (!renderQuestion)
		{
			return;
		}

		g.setColor(Color.white);
		FamilyFued.drawHorizontalCenteredString(g, question.getQuestion(), new Rectangle(0, 0, 1920, 1080), 250, FamilyFued.getTTFont().deriveFont(60F));

		g.setColor(Color.red);
		g.drawString(FamilyFued.family1.getName(), 10, 60);
		g.setColor(Color.white);
		FamilyFued.drawHorizontalCenteredString(g, String.valueOf(FamilyFued.family1.getPoints()), new Rectangle(0, 0, g.getFontMetrics(FamilyFued.getTTFont()).stringWidth(FamilyFued.family1.getName()), 1080), 120, FamilyFued.getTTFont());

		g.setColor(Color.blue);
		FamilyFued.drawRightAlinedString(g, FamilyFued.family2.getName(), 10, 60);
		g.setColor(Color.white);
		FamilyFued.drawRightAlinedString(g, String.valueOf(FamilyFued.family2.getPoints()), g.getFontMetrics(FamilyFued.getTTFont()).stringWidth(FamilyFued.family2.getName()) / 2, 120);

		FamilyFued.drawHorizontalCenteredString(g, "Sudden Death", new Rectangle(0, 0, 1920, 1080), 150, FamilyFued.getTTFont());

		g.setColor(Color.white);

		if (!roundOver)
		{
			if (person1 != null)
			{
				if (buzzerWinner == null)
				{
					g.drawString(person1, 10, 700);
				} else if (buzzerWinner == person1)
				{
					if (currentFamily.getMembers().contains(person1))
					{
						if (renderRed)
						{
							g.setColor(Color.red);
							g.drawString(person1, 10, 700);
						} else
						{
							g.setColor(Color.white);
							g.drawString(person1, 10, 700);
						}
					}
				}

				if (buzzerWinner != null)
				{
					if (currentFamily.getMembers().contains(person1))
					{
						g.drawString(person1, 10, 700);
					}
				}
			}

			if (person2 != null)
			{
				if (buzzerWinner == null)
				{
					FamilyFued.drawRightAlinedString(g, person2, 10, 700);
				} else if (buzzerWinner == person2)
				{
					if (currentFamily.getMembers().contains(person2))
					{
						if (renderRed)
						{
							g.setColor(Color.red);
							FamilyFued.drawRightAlinedString(g, person2, 10, 700);
						} else
						{
							g.setColor(Color.white);
							FamilyFued.drawRightAlinedString(g, person2, 10, 700);
						}
					}
				}

				if (buzzerWinner != null)
				{
					if (currentFamily.getMembers().contains(person2))
					{
						FamilyFued.drawRightAlinedString(g, person2, 10, 700);
					}
				}
			}
		}

		FamilyFued.drawHorizontalCenteredString(g, answer, new Rectangle(0, 0, 1920, 1080), 1060, FamilyFued.getTTFont());

		if (renderAnswer1)
		{
			g.drawImage(ans1f, 0, 0, ob);

			Answer answer = question.getAnswers().get(0);

			if (String.valueOf(answer.getPoints()).length() == 2)
			{
				g.drawString(String.valueOf(answer.getPoints()), 852, 420);
			} else
			{
				g.drawString(String.valueOf(answer.getPoints()), 857, 420);
			}

			if (FamilyFued.getWidth(g, answer.getAnswer()) > 454)
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(48F));
				g.drawString(answer.getAnswer(), 405, 420);
			} else
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(60F));
				g.drawString(answer.getAnswer(), 405, 420);
			}
		}

		g.setFont(FamilyFued.getTTFont().deriveFont(60F));

		if (strike1)
		{
			if (renderedStrikeTime > System.currentTimeMillis())
			{
				g.drawImage(strike, 295, 390, ob);
			}
		}

		if (strike2)
		{
			if (renderedStrikeTime > System.currentTimeMillis())
			{
				g.drawImage(strike, 768, 390, ob);
			}
		}

		if (strike3)
		{
			if (renderedStrikeTime > System.currentTimeMillis())
			{
				g.drawImage(strike, 1232, 390, ob);
			}
		}
	}

	public boolean isStarted()
	{
		return isStarted;
	}

	public void setBuzzerMember1(String member)
	{
		this.person1 = member;
	}

	public void setBuzzerMember2(String member)
	{
		this.person2 = member;
	}

	public void setBuzzerWinner(String winner)
	{
		buzzerWinner = winner;

		if (FamilyFued.family1.getMembers().contains(buzzerWinner))
		{
			currentFamily = FamilyFued.family1;
			FamilyFued.usedFamily1.add(winner);
		}

		if (FamilyFued.family2.getMembers().contains(buzzerWinner))
		{
			currentFamily = FamilyFued.family2;
			FamilyFued.usedFamily2.add(winner);
		}

		FamilyFued.stage = Stage.ENTERING;
	}

	public Family getOtherFamily()
	{
		if (currentFamily == FamilyFued.family1)
		{
			return FamilyFued.family2;
		}

		if (currentFamily == FamilyFued.family2)
		{
			return FamilyFued.family1;
		}

		return null;
	}

	public void addStrike()
	{
		strikes++;

		renderedStrikeTime = System.currentTimeMillis() + 500;

		strike2 = true;

		currentFamily = getOtherFamily();

		FamilyFued.playSound("Wrong.wav");
	}

	public void submitAnswer(String answer)
	{
		boolean correct = false;

		for (Answer a : question.getAnswers())
		{
			Answer ans = a;
			if (a.getAnswer().toLowerCase().contains(answer.toLowerCase()))
			{
				int answerNum = 0;

				for (int i : answerNums.keySet())
				{
					if (answerNums.get(i).getAnswer().toLowerCase().equals(ans.getAnswer().toLowerCase()))
					{
						answerNum = i;
					}
				}

				if (answerNum != 1)
				{
					continue;
				}

				correct = true;

				FamilyFued.winner = currentFamily;

				if (answerNum == 1)
				{
					rans1 = false;
					renderAnswer1 = true;
				}

				roundOver = true;

				FamilyFued.playSound("Ding.wav");
				break;
			}
		}

		if (!correct)
		{
			addStrike();
		}

		if (currentFamily == FamilyFued.family1)
		{
			person1 = FamilyFued.getFamily1Member();
		}

		if (currentFamily == FamilyFued.family2)
		{
			person2 = FamilyFued.getFamily2Member();
		}

		this.answer = "";
	}

	int overTime = 0;

	public void onRoundOver()
	{
		overTime++;
		if (overTime < 30)
		{
			return;
		}

		overTime = 0;

		stopKeyBoardInput = true;

		roundDone = true;
	}

	public boolean isDone()
	{
		return roundDone == true;
	}
}