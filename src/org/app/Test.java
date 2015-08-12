package org.app;

import java.text.DecimalFormat;
import java.text.Normalizer.Form;

public class Test {
	
	private static double[] weight1 = {1, 1, 1};
	private static double[] weight2 = {1, 1, 1};
	
	private static int[][] f = {{1, 2, 4}, {2, 6, 2}, {2, 2, 1}};	
	private static int[][] g = {{2, 3, 4}, {4, 4, 1}, {1, 6, 3}};
	
	private static void form(double[] w)
	{
		double sum = 0;
		for (double i: w)
			sum = sum + i;
		for (int i = 0; i < w.length; i++)
			w[i] = w[i] / sum;
	}
	
	public static void main(String[] args)
	{
		for (int i = 0; i < 101; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				double temp = 0;
				for (int k = 0; k < 3; k++)
				{
					temp = temp + weight1[k] * f[k][j];
				}
				weight2[j] = temp;
			}
			
			form(weight2);
			
			for (int j = 0; j < 3; j++)
			{
				double temp = 0;
				for (int k = 0; k < 3; k++)
				{
					temp = temp + weight2[k] * g[k][j];
				}
				weight1[j] = temp;
			}
			
			form(weight1);
			
			DecimalFormat format = new DecimalFormat("0.0000000");
			System.out.println(i);
			for (int j = 0; j < 3; j++)
				System.out.println("weight1 " + j + " = " + format.format(weight1[j]));
			for (int j = 0; j < 3; j++)
				System.out.println("weight2 " + j + " = " + format.format(weight2[j]));
		}
			
		
		
		
	}
}
