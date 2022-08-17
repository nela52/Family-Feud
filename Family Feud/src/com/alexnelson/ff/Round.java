package com.alexnelson.ff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Round
{
	private boolean isStarted = false;

	private String person1;
	private String person2;

	private String buzzerWinner = null;
	private Family currentFamily = null;

	private Question question;

	private BufferedImage ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8 = null;
	private BufferedImage ans1f, ans2f, ans3f, ans4f, ans5f, ans6f, ans7f, ans8f = null;
	private boolean answ1, answ2, answ3, answ4, answ5, answ6, answ7, answ8 = false;
	private boolean rans1, rans2, rans3, rans4, rans5, rans6, rans7, rans8 = false;

	private boolean answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8 = false;
	private boolean renderAnswer1, renderAnswer2, renderAnswer3, renderAnswer4, renderAnswer5, renderAnswer6, renderAnswer7, renderAnswer8 = false;

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

	private int roundPoints = 0;

	private HashMap<Integer, Answer> answerNums = new HashMap<Integer, Answer>();

	private ArrayList<Answer> foundAnswers = new ArrayList<Answer>();

	private boolean steal = false;

	private boolean roundOver = false;
	private boolean roundDone = false;

	private boolean stopKeyBoardInput = false;

	public Round()
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
			ans2 = loader.loadImage("/2.png");
			ans3 = loader.loadImage("/3.png");
			ans4 = loader.loadImage("/4.png");
			ans5 = loader.loadImage("/5.png");
			ans6 = loader.loadImage("/6.png");
			ans7 = loader.loadImage("/7.png");
			ans8 = loader.loadImage("/8.png");

			ans1f = loader.loadImage("/1f.png");
			ans2f = loader.loadImage("/2f.png");
			ans3f = loader.loadImage("/3f.png");
			ans4f = loader.loadImage("/4f.png");
			ans5f = loader.loadImage("/5f.png");
			ans6f = loader.loadImage("/6f.png");
			ans7f = loader.loadImage("/7f.png");
			ans8f = loader.loadImage("/8f.png");

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

			if (i == 2)
			{
				answ2 = true;
				answer2 = true;
			}

			if (i == 3)
			{
				answ3 = true;
				answer3 = true;
			}

			if (i == 4)
			{
				answ4 = true;
				answer4 = true;
			}

			if (i == 5)
			{
				answ5 = true;
				answer5 = true;
			}

			if (i == 6)
			{
				answ6 = true;
				answer6 = true;
			}

			if (i == 7)
			{
				answ7 = true;
				answer7 = true;
			}

			if (i == 8)
			{
				answ8 = true;
				answer8 = true;
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
		answ2 = false;
		answ3 = false;
		answ4 = false;
		answ5 = false;
		answ6 = false;
		answ7 = false;
		answ8 = false;

		rans1 = false;
		rans2 = false;
		rans3 = false;
		rans4 = false;
		rans5 = false;
		rans6 = false;
		rans7 = false;
		rans8 = false;

		answer1 = false;
		answer2 = false;
		answer3 = false;
		answer4 = false;
		answer5 = false;
		answer6 = false;
		answer7 = false;
		answer8 = false;

		renderAnswer1 = false;
		renderAnswer2 = false;
		renderAnswer3 = false;
		renderAnswer4 = false;
		renderAnswer5 = false;
		renderAnswer6 = false;
		renderAnswer7 = false;
		renderAnswer8 = false;

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

		roundPoints = 0;

		answerNums.clear();

		foundAnswers.clear();

		steal = false;

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
			return;
		}

		if (answ2)
		{
			rans2 = true;
			answ2 = false;
			FamilyFued.playSound("Answer.wav");
			return;
		}

		if (answ3)
		{
			rans3 = true;
			answ3 = false;
			FamilyFued.playSound("Answer.wav");
			return;
		}

		if (answ4)
		{
			rans4 = true;
			answ4 = false;
			FamilyFued.playSound("Answer.wav");
			return;
		}

		if (answ5)
		{
			rans5 = true;
			answ5 = false;
			FamilyFued.playSound("Answer.wav");
			return;
		}

		if (answ6)
		{
			rans6 = true;
			answ6 = false;
			FamilyFued.playSound("Answer.wav");
			return;
		}

		if (answ7)
		{
			rans7 = true;
			answ7 = false;
			FamilyFued.playSound("Answer.wav");
			return;
		}

		if (answ8)
		{
			rans8 = true;
			answ8 = false;
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

		if (rans2)
		{
			g.drawImage(ans2, 0, 0, ob);
		}

		if (rans3)
		{
			g.drawImage(ans3, 0, 0, ob);
		}

		if (rans4)
		{
			g.drawImage(ans4, 0, 0, ob);
		}

		if (rans5)
		{
			g.drawImage(ans5, 0, 0, ob);
		}

		if (rans6)
		{
			g.drawImage(ans6, 0, 0, ob);
		}

		if (rans7)
		{
			g.drawImage(ans7, 0, 0, ob);
		}

		if (rans8)
		{
			g.drawImage(ans8, 0, 0, ob);
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

		if (FamilyFued.pointMultiplier == 1)
		{
			FamilyFued.drawHorizontalCenteredString(g, "Single", new Rectangle(0, 0, 1920, 1080), 150, FamilyFued.getTTFont());
		}

		if (FamilyFued.pointMultiplier == 2)
		{
			FamilyFued.drawHorizontalCenteredString(g, "Double", new Rectangle(0, 0, 1920, 1080), 150, FamilyFued.getTTFont());
		}

		if (FamilyFued.pointMultiplier == 3)
		{
			FamilyFued.drawHorizontalCenteredString(g, "Triple", new Rectangle(0, 0, 1920, 1080), 150, FamilyFued.getTTFont());
		}

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

		if (renderAnswer2)
		{
			g.drawImage(ans2f, 0, 0, ob);

			Answer answer = question.getAnswers().get(1);

			if (String.valueOf(answer.getPoints()).length() == 2)
			{
				g.drawString(String.valueOf(answer.getPoints()), 852, 590);
			} else
			{
				g.drawString(String.valueOf(answer.getPoints()), 875, 590);
			}

			if (FamilyFued.getWidth(g, answer.getAnswer()) > 454)
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(48F));
				g.drawString(answer.getAnswer(), 405, 590);
			} else
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(60F));
				g.drawString(answer.getAnswer(), 405, 590);
			}
		}

		g.setFont(FamilyFued.getTTFont().deriveFont(60F));

		if (renderAnswer3)
		{
			g.drawImage(ans3f, 0, 0, ob);

			Answer answer = question.getAnswers().get(2);

			if (String.valueOf(answer.getPoints()).length() == 2)
			{
				g.drawString(String.valueOf(answer.getPoints()), 852, 750);
			} else
			{
				g.drawString(String.valueOf(answer.getPoints()), 875, 750);
			}

			if (FamilyFued.getWidth(g, answer.getAnswer()) > 454)
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(48F));
				g.drawString(answer.getAnswer(), 405, 750);
			} else
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(60F));
				g.drawString(answer.getAnswer(), 405, 750);
			}
		}

		g.setFont(FamilyFued.getTTFont().deriveFont(60F));

		if (renderAnswer4)
		{
			g.drawImage(ans4f, 0, 0, ob);

			Answer answer = question.getAnswers().get(3);

			if (String.valueOf(answer.getPoints()).length() == 2)
			{
				g.drawString(String.valueOf(answer.getPoints()), 852, 910);
			} else
			{
				g.drawString(String.valueOf(answer.getPoints()), 875, 910);
			}

			if (FamilyFued.getWidth(g, answer.getAnswer()) > 454)
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(48F));
				g.drawString(answer.getAnswer(), 405, 910);
			} else
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(60F));
				g.drawString(answer.getAnswer(), 405, 910);
			}
		}

		g.setFont(FamilyFued.getTTFont().deriveFont(60F));

		if (renderAnswer5)
		{
			g.drawImage(ans5f, 0, 0, ob);

			Answer answer = question.getAnswers().get(4);

			if (String.valueOf(answer.getPoints()).length() == 2)
			{
				g.drawString(String.valueOf(answer.getPoints()), 1455, 420);
			} else
			{
				g.drawString(String.valueOf(answer.getPoints()), 1465, 420);
			}

			if (FamilyFued.getWidth(g, answer.getAnswer()) > 454)
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(48F));
				g.drawString(answer.getAnswer(), 993, 420);
			} else
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(60F));
				g.drawString(answer.getAnswer(), 993, 420);
			}
		}

		g.setFont(FamilyFued.getTTFont().deriveFont(60F));

		if (renderAnswer6)
		{
			g.drawImage(ans6f, 0, 0, ob);

			Answer answer = question.getAnswers().get(5);

			if (String.valueOf(answer.getPoints()).length() == 2)
			{
				g.drawString(String.valueOf(answer.getPoints()), 1455, 590);
			} else
			{
				g.drawString(String.valueOf(answer.getPoints()), 1465, 590);
			}

			if (FamilyFued.getWidth(g, answer.getAnswer()) > 454)
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(48F));
				g.drawString(answer.getAnswer(), 993, 590);
			} else
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(60F));
				g.drawString(answer.getAnswer(), 993, 590);
			}
		}

		g.setFont(FamilyFued.getTTFont().deriveFont(60F));

		if (renderAnswer7)
		{
			g.drawImage(ans7f, 0, 0, ob);

			Answer answer = question.getAnswers().get(6);

			if (String.valueOf(answer.getPoints()).length() == 2)
			{
				g.drawString(String.valueOf(answer.getPoints()), 1455, 750);
			} else
			{
				g.drawString(String.valueOf(answer.getPoints()), 1465, 750);
			}

			if (FamilyFued.getWidth(g, answer.getAnswer()) > 454)
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(48F));
				g.drawString(answer.getAnswer(), 993, 750);
			} else
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(60F));
				g.drawString(answer.getAnswer(), 993, 750);
			}
		}

		g.setFont(FamilyFued.getTTFont().deriveFont(60F));

		if (renderAnswer8)
		{
			g.drawImage(ans8f, 0, 0, ob);

			Answer answer = question.getAnswers().get(7);

			if (String.valueOf(answer.getPoints()).length() == 2)
			{
				g.drawString(String.valueOf(answer.getPoints()), 1455, 910);
			} else
			{
				g.drawString(String.valueOf(answer.getPoints()), 1465, 910);
			}

			if (FamilyFued.getWidth(g, answer.getAnswer()) > 454)
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(48F));
				g.drawString(answer.getAnswer(), 993, 910);
			} else
			{
				g.setFont(FamilyFued.getTTFont().deriveFont(60F));
				g.drawString(answer.getAnswer(), 993, 910);
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
		if (strike1)
		{
			if (steal)
			{
				roundOver = true;
				this.answer = "";
			}
		}

		strikes++;

		renderedStrikeTime = System.currentTimeMillis() + 500;

		if (strikes == 1)
		{
			strike1 = true;
		}

		if (strikes == 2)
		{
			strike2 = true;
		}

		if (strikes == 3)
		{
			strike3 = true;

			currentFamily = getOtherFamily();

			steal = true;
		}

		FamilyFued.playSound("Wrong.wav");

		if (strikes > 3)
		{
			strikes = 1;
			strike1 = true;
			strike2 = false;
			strike3 = false;
		}
	}

	public void submitAnswer(String answer)
	{
		boolean correct = false;

		for (Answer a : question.getAnswers())
		{
			Answer ans = a;
			if (a.getAnswer().toLowerCase().contains(answer.toLowerCase()))
			{
				if (foundAnswers.contains(a))
				{
					continue;
				}

				correct = true;
				foundAnswers.add(ans);

				int points = ans.getPoints() * FamilyFued.pointMultiplier;

				roundPoints += points;
				currentFamily.addPoints(points);

				if (steal)
				{
					getOtherFamily().setPoints(getOtherFamily().getPoints() - (roundPoints - points));
					currentFamily.addPoints((roundPoints - points));
				}

				int answerNum = 0;

				for (int i : answerNums.keySet())
				{
					if (answerNums.get(i).getAnswer().toLowerCase().equals(ans.getAnswer().toLowerCase()))
					{
						answerNum = i;
					}
				}

				if (answerNum == 1)
				{
					rans1 = false;
					renderAnswer1 = true;
				}

				if (answerNum == 2)
				{
					rans2 = false;
					renderAnswer2 = true;
				}

				if (answerNum == 3)
				{
					rans3 = false;
					renderAnswer3 = true;
				}

				if (answerNum == 4)
				{
					rans4 = false;
					renderAnswer4 = true;
				}

				if (answerNum == 5)
				{
					rans5 = false;
					renderAnswer5 = true;
				}

				if (answerNum == 6)
				{
					rans6 = false;
					renderAnswer6 = true;
				}

				if (answerNum == 7)
				{
					rans7 = false;
					renderAnswer7 = true;
				}

				if (answerNum == 8)
				{
					rans8 = false;
					renderAnswer8 = true;
				}

				if (foundAnswers.size() == question.getAnswers().size())
				{
					roundOver = true;
				}

				FamilyFued.playSound("Ding.wav");
				break;
			}
		}

		if (!correct)
		{
			addStrike();
		} else
		{
			if (steal)
			{
				roundOver = true;
				this.answer = "";
				return;
			}
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

		if (answer1)
		{
			if (!renderAnswer1)
			{
				renderAnswer1 = true;
				FamilyFued.playSound("Ding.wav");
			}
			answer1 = false;
			return;
		}

		if (answer2)
		{
			if (!renderAnswer2)
			{
				renderAnswer2 = true;
				FamilyFued.playSound("Ding.wav");
			}
			answer2 = false;
			return;
		}

		if (answer3)
		{
			if (!renderAnswer3)
			{
				renderAnswer3 = true;
				FamilyFued.playSound("Ding.wav");
			}
			answer3 = false;
			return;
		}

		if (answer4)
		{
			if (!renderAnswer4)
			{
				renderAnswer4 = true;
				FamilyFued.playSound("Ding.wav");
			}
			answer4 = false;
			return;
		}

		if (answer5)
		{
			if (!renderAnswer5)
			{
				renderAnswer5 = true;
				FamilyFued.playSound("Ding.wav");
			}
			answer5 = false;
			return;
		}

		if (answer6)
		{
			if (!renderAnswer6)
			{
				renderAnswer6 = true;
				FamilyFued.playSound("Ding.wav");
			}
			answer6 = false;
			return;
		}

		if (answer7)
		{
			if (!renderAnswer7)
			{
				renderAnswer7 = true;
				FamilyFued.playSound("Ding.wav");
			}
			answer7 = false;
			return;
		}

		if (answer8)
		{
			if (!renderAnswer8)
			{
				renderAnswer8 = true;
				FamilyFued.playSound("Ding.wav");
			}
			answer8 = false;
			return;
		}

		roundDone = true;
	}

	public boolean isDone()
	{
		return roundDone == true;
	}
}