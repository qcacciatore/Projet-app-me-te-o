package pack;

import javax.swing.JFrame;

public class DashboardFrame extends JFrame {

	private static int mWidth;
	private static int mHeight;
	
	public DashboardFrame(int width, int height)
	{
		mWidth = width;
		mHeight = height;
		setSize(width,height);
	}
}
