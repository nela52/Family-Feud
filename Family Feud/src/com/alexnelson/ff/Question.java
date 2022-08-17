package com.alexnelson.ff;

import java.util.ArrayList;

public class Question
{
	private String question;
	private ArrayList<Answer> answers = new ArrayList<Answer>();

	public Question(String question)
	{
		this.question = question;
	}

	public String getQuestion()
	{
		return question;
	}

	public ArrayList<Answer> getAnswers()
	{
		return answers;
	}

	public void addAnswer(Answer answer)
	{
		answers.add(answer);
	}
}