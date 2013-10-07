/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package movieTheater;

/**
 *
 * @author Loai Ghoraba
 */
public class Main
{
    public static Output out;
    public static Theater th=new Theater();
    public static void main(String[]args)
    {
        out=new Output();
        out.setVisible(true);
        int movieNum;
	Spectator spect;
        for(int i=0;i<500;i++)
	{
            movieNum=(int)(20*Math.random());
            spect=new Spectator(movieNum);
        }
    }
}
