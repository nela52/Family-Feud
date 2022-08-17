package com.alexnelson.ff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;

public class FastMoneyRound
{
	private boolean render = false;
	private boolean tick = false;

	private boolean isStarted = false;

	public static ArrayList<Question> questions = new ArrayList<Question>();
	public static ArrayList<String> submittedAnswers = new ArrayList<String>();
	public static HashMap<String, Answer> answers = new HashMap<String, Answer>();

	public static ArrayList<Answer> foundAnswers = new ArrayList<Answer>();

	private ArrayList<Integer> answerPoints = new ArrayList<Integer>();

	private static Question currentQuestion = null;
	private static int currentQuestionNum = 0;

	public String answer = "";

	public static String currentMember = null;

	public static String member1 = null;
	public static String member2 = null;

	private int timer = 20;
	private boolean timerStopped = false;

	private int member1Points = 0;
	private int member2Points = 0;

	private boolean checkAnswers = false;

	private boolean frs1, frs2, frs3, frs4, frs5 = false;

	private boolean isDone = false;

	public void start()
	{
		FamilyFued.stage = Stage.FAST_MONEY;

		for (int i = 1; i <= 5; i++)
		{
			questions.add(FamilyFued.getQuestion());
		}

		currentQuestion = questions.get(currentQuestionNum);

		currentQuestionNum++;

		if (FamilyFued.winner == FamilyFued.family1)
		{
			currentMember = FamilyFued.getFamily1Member();

			member1 = currentMember;
			member2 = FamilyFued.getFamily1Member();
		}

		if (FamilyFued.winner == FamilyFued.family2)
		{
			currentMember = FamilyFued.getFamily2Member();

			member1 = currentMember;
			member2 = FamilyFued.getFamily2Member();
		}

		isStarted = true;
		tick = true;
		render = true;

		KeyInput.canType = true;
	}

	public void tick()
	{
		if (!tick)
		{
			return;
		}

		updateTimer();

		if (checkAnswers)
		{
			onCheckAnswers();
		}
	}

	public void render(Graphics g, ImageObserver ob)
	{
		if (!render)
		{
			return;
		}

		g.setColor(Color.red);
		g.drawString(member1, 10, 60);
		g.setColor(Color.white);
		FamilyFued.drawHorizontalCenteredString(g, String.valueOf(member1Points), new Rectangle(0, 0, g.getFontMetrics(FamilyFued.getTTFont()).stringWidth(member1), 1080), 120, FamilyFued.getTTFont());

		g.setColor(Color.blue);
		FamilyFued.drawRightAlinedString(g, member2, 10, 60);
		g.setColor(Color.white);
		FamilyFued.drawRightAlinedString(g, String.valueOf(member2Points), g.getFontMetrics(FamilyFued.getTTFont()).stringWidth(member2) / 2, 120);

		FamilyFued.drawHorizontalCenteredString(g, String.valueOf(timer), new Rectangle(0, 0, 1920, 1080), 100, FamilyFued.getTTFont());

		FamilyFued.drawHorizontalCenteredString(g, answer, new Rectangle(0, 0, 1920, 1080), 1060, FamilyFued.getTTFont());

		if (currentQuestion == null)
		{
			return;
		}

		FamilyFued.drawHorizontalCenteredString(g, currentQuestion.getQuestion(), new Rectangle(0, 0, 1920, 1080), 250, FamilyFued.getTTFont().deriveFont(60F));

		for (int i = 0; i < submittedAnswers.size(); i++)
		{
			String answer = submittedAnswers.get(i);

			if (FamilyFued.fastMoneyRoundNum == 1)
			{
				if (i == 0)
				{
					g.drawString(answer, 330, 335);
				}

				if (i == 1)
				{
					g.drawString(answer, 330, 435);
				}

				if (i == 2)
				{
					g.drawString(answer, 330, 535);
				}

				if (i == 3)
				{
					g.drawString(answer, 330, 635);
				}

				if (i == 4)
				{
					g.drawString(answer, 330, 735);
				}
			}

			if (FamilyFued.fastMoneyRoundNum == 2)
			{
				if (i == 0)
				{
					g.drawString(answer, 980, 335);
				}

				if (i == 1)
				{
					g.drawString(answer, 980, 435);
				}

				if (i == 2)
				{
					g.drawString(answer, 980, 535);
				}

				if (i == 3)
				{
					g.drawString(answer, 980, 635);
				}

				if (i == 4)
				{
					g.drawString(answer, 980, 735);
				}
			}
		}

		int ran = 0;
		for (String a : answers.keySet())
		{
			if (FamilyFued.fastMoneyRoundNum == 1)
			{
				if (ran == 0)
				{
					int points = answerPoints.get(0);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 870, 340);
					} else
					{
						g.drawString(String.valueOf(points), 855, 340);
					}
				}

				if (ran == 1)
				{
					int points = answerPoints.get(1);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 870, 435);
					} else
					{
						g.drawString(String.valueOf(points), 855, 435);
					}
				}

				if (ran == 2)
				{
					int points = answerPoints.get(2);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 870, 535);
					} else
					{
						g.drawString(String.valueOf(points), 855, 535);
					}
				}

				if (ran == 3)
				{
					int points = answerPoints.get(3);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 870, 635);
					} else
					{
						g.drawString(String.valueOf(points), 855, 635);
					}

				}

				if (ran == 4)
				{
					int points = answerPoints.get(4);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 870, 735);
					} else
					{
						g.drawString(String.valueOf(points), 855, 735);
					}
				}
			}

			if (FamilyFued.fastMoneyRoundNum == 2)
			{
				if (ran == 0)
				{
					int points = answerPoints.get(0);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 1515, 340);
					} else
					{
						g.drawString(String.valueOf(points), 1500, 340);
					}
				}

				if (ran == 1)
				{
					int points = answerPoints.get(1);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 1515, 435);
					} else
					{
						g.drawString(String.valueOf(points), 1500, 435);
					}
				}

				if (ran == 2)
				{
					int points = answerPoints.get(2);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 1515, 535);
					} else
					{
						g.drawString(String.valueOf(points), 1500, 535);
					}
				}

				if (ran == 3)
				{
					int points = answerPoints.get(3);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 1515, 635);
					} else
					{
						g.drawString(String.valueOf(points), 1500, 635);
					}

				}

				if (ran == 4)
				{
					int points = answerPoints.get(4);

					if (String.valueOf(points).length() < 2)
					{
						g.drawString(String.valueOf(points), 1515, 735);
					} else
					{
						g.drawString(String.valueOf(points), 1500, 735);
					}
				}
			}

			ran++;
		}

		if (FamilyFued.fastMoneyRoundNum == 1)
		{
			if (frs1)
			{
				g.setColor(Color.RED);
				g.fillRect(855, 278, 81, 78);
			}

			if (frs2)
			{
				g.setColor(Color.RED);
				g.fillRect(855, 378, 81, 78);
			}

			if (frs3)
			{
				g.setColor(Color.RED);
				g.fillRect(855, 478, 81, 78);
			}

			if (frs4)
			{
				g.setColor(Color.RED);
				g.fillRect(855, 578, 81, 78);
			}

			if (frs5)
			{
				g.setColor(Color.RED);
				g.fillRect(855, 678, 81, 78);
			}
		}

		if (FamilyFued.fastMoneyRoundNum == 2)
		{
			if (frs1)
			{
				g.setColor(Color.RED);
				g.fillRect(1500, 278, 81, 78);
			}

			if (frs2)
			{
				g.setColor(Color.RED);
				g.fillRect(1500, 378, 81, 78);
			}

			if (frs3)
			{
				g.setColor(Color.RED);
				g.fillRect(1500, 478, 81, 78);
			}

			if (frs4)
			{
				g.setColor(Color.RED);
				g.fillRect(1500, 578, 81, 78);
			}

			if (frs5)
			{
				g.setColor(Color.RED);
				g.fillRect(1500, 678, 81, 78);
			}
		}

		FamilyFued.setFontSize(48F);

		g.setColor(Color.WHITE);

		g.drawString("Total:", 630, 850);
		FamilyFued.drawRightAlinedString(g, String.valueOf(FamilyFued.fastMoneyRound1Total), 990, 850);

		if (FamilyFued.fastMoneyRoundNum == 2)
		{
			g.drawString("Total:", 1280, 850);
			FamilyFued.drawRightAlinedString(g, String.valueOf(FamilyFued.fastMoneyTotal), 345, 850);
		}
	}

	private void stop()
	{
		tick = false;
		render = false;

		questions.clear();
		submittedAnswers.clear();
		answers.clear();

		foundAnswers.clear();

		answerPoints.clear();

		currentQuestion = null;
		currentQuestionNum = 0;

		answer = "";

		currentMember = null;

		member1 = null;
		member2 = null;

		timer = 20;
		timerStopped = true;

		member1Points = 0;
		member2Points = 0;

		checkAnswers = false;

		frs1 = false;
		frs2 = false;
		frs3 = false;
		frs4 = false;
		frs5 = false;

		if (FamilyFued.fastMoneyTotal < 200)
		{
			FamilyFued.winner.addPoints(FamilyFued.fastMoneyTotal);
			FamilyFued.moneyWon = FamilyFued.winner.getPoints();
		} else
		{
			FamilyFued.moneyWon = 20000;
		}

		isDone = true;
	}

	private int time = 0;

	public void updateTimer()
	{
		if (timerStopped)
		{
			return;
		}

		time++;
		if (time < 60)
		{
			return;
		}

		time = 0;

		if (timer == 0)
		{
			checkAnswers = true;
			timerStopped = true;
			this.answer = "";
			KeyInput.canType = false;
			FamilyFued.playSound("Wrong.wav");
			return;
		}

		timer--;
	}

	private int flashRedTimes = 5;

	public void onFlashRed(int num)
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				for (int i = 1; i <= flashRedTimes; i++)
				{
					if (num == 1)
					{
						frs1 = !frs1;
					}

					if (num == 2)
					{
						frs2 = !frs2;
					}

					if (num == 3)
					{
						frs3 = !frs3;
					}

					if (num == 4)
					{
						frs4 = !frs4;
					}

					if (num == 5)
					{
						frs5 = !frs5;
					}

					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}

				if (frs1)
				{
					frs1 = false;
				}

				if (frs2)
				{
					frs2 = false;
				}

				if (frs3)
				{
					frs3 = false;
				}

				if (frs4)
				{
					frs4 = false;
				}

				if (frs5)
				{
					frs5 = false;
				}
			}
		}).start();
	}

	public boolean isStarted()
	{
		return isStarted;
	}

	public void submitAnswer(String answer)
	{
		for (Answer a : currentQuestion.getAnswers())
		{
			if (a.getAnswer().toLowerCase().contains(answer.toLowerCase()))
			{
				if (foundAnswers.contains(a))
				{
					FamilyFued.playSound("FMS.wav");
					return;
				}
			}
		}

		submittedAnswers.add(answer);

		if (submittedAnswers.size() == 5)
		{
			checkAnswers = true;
			timerStopped = true;
			this.answer = "";
			KeyInput.canType = false;
			return;
		}

		currentQuestion = questions.get(currentQuestionNum++);

		this.answer = "";
	}

	public void checkAnswer(String answer)
	{
		boolean correct = false;

		if (answer.isEmpty())
		{
			answers.put(answer, null);
			answerPoints.add(0);
			FamilyFued.playSound("Wrong.wav");
			return;
		}

		for (Answer a : currentQuestion.getAnswers())
		{
			if (a.getAnswer().toLowerCase().contains(answer.toLowerCase()))
			{
				correct = true;
				answers.put(answer, a);
				foundAnswers.add(a);

				answerPoints.add(a.getPoints());

				if (FamilyFued.fastMoneyRoundNum == 1)
				{
					FamilyFued.fastMoneyRound1Total += a.getPoints();
				} else
				{
					FamilyFued.fastMoneyTotal += a.getPoints();
				}

				FamilyFued.playSound("FMD.wav");

				break;
			}
		}

		if (!correct)
		{
			answers.put(answer, null);
			answerPoints.add(0);
			FamilyFued.playSound("Wrong.wav");
		}
	}

	private int cat = 0;

	private int checkedAnswersNum = 0;

	public void onCheckAnswers()
	{
		cat++;

		if (cat < 300)
		{
			return;
		}

		cat = 0;

		currentQuestionNum = 0;
		if (submittedAnswers.size() < 5)
		{
			while (submittedAnswers.size() < 5)
			{
				submittedAnswers.add("");
			}
		}

		new Thread(new Runnable()
		{
			public void run()
			{
				for (String answer : submittedAnswers)
				{
					currentQuestion = questions.get(currentQuestionNum);

					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}

					FamilyFued.playSound("FMA.wav");

					if (currentQuestionNum == 0)
					{
						frs1 = true;
						onFlashRed(1);
						frs1 = false;
					}

					if (currentQuestionNum == 1)
					{
						frs2 = true;
						onFlashRed(2);
						frs2 = false;
					}

					if (currentQuestionNum == 2)
					{
						frs3 = true;
						onFlashRed(3);
						frs3 = false;
					}

					if (currentQuestionNum == 3)
					{
						frs4 = true;
						onFlashRed(4);
						frs4 = false;
					}

					if (currentQuestionNum == 4)
					{
						frs5 = true;
						onFlashRed(5);
						frs5 = false;
					}

					try
					{
						Thread.sleep(3000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}

					checkAnswer(answer);
					checkedAnswersNum++;
					currentQuestionNum++;

					try
					{
						Thread.sleep(2500);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}

					if (checkedAnswersNum == 10)
					{
						stop();
						break;
					}

					if (checkedAnswersNum == 5)
					{
						startNextRound();
						break;
					}
				}
			}
		}).start();

		KeyInput.canType = false;
		checkAnswers = false;
	}

	public void startNextRound()
	{
		if (FamilyFued.fastMoneyRoundNum == 1)
		{
			FamilyFued.fastMoneyTotal += FamilyFued.fastMoneyRound1Total;
		}

		FamilyFued.fastMoneyRoundNum = 2;

		submittedAnswers.clear();
		answers.clear();
		answerPoints.clear();

		currentQuestion = questions.get(0);

		currentQuestionNum = 1;

		timer = 25;
		timerStopped = false;

		KeyInput.canType = true;
	}

	public boolean isDone()
	{
		return isDone == true;
	}
}