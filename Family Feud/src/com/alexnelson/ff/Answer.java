package com.alexnelson.ff;

public class Answer
{
	private String answer;
	private int points;

	public Answer(String answer, int points)
	{
		this.answer = answer;
		this.points = points;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public String getAnswer()
	{
		return answer;
	}

	public int getPoints()
	{
		return points;
	}
}