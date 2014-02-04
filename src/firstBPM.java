import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;


public class firstBPM {
	static int width, height;
	static int left = 4;
	static int mid = 0;
	static int right = 2;
	
	
	public static void main(String args[]) throws IOException { 
		LinkedList<Integer> ListX = new LinkedList<Integer>();
		LinkedList<Integer> ListY = new LinkedList<Integer>();
		LinkedList<String> ListD = new LinkedList<String>();
		int[][] array = seeBMPImage("/resources/complexRoom.ppm");
		//Code for how to location in any 2D building as of 2/3/14
		for (int i = 0; i < height; i++){
			for(int j= 0; j< width; j++){
				if(array[i][j] == 0){
					//System.out.print("("+i+","+j+")");
					int countU = 0;
					int countR = 0;
					int countD = 0;
					int countL = 0;
					for(int a = i-1; a >= 0; a--){
						if(array[a][j] == 0){
							countU++;
						}
						else break;
					}
					for(int a = j+1; a < width; a++){
						if(array[i][a] == 0){
							countR++;
						}
						else break;
					}
					for(int a = i+1; a < height; a++){
						if(array[a][j] == 0){
							countD++;
						}
						else break;
					}
					for(int a = j-1; a >= 0; a--){
						if(array[i][a] == 0){
							countL++;
						}
						else break;
					}
					//System.out.println(" " +countU+" "+countR+" "+countD+" "+countL+" ");
					if(countU == left && countR == mid && countD == right){
						ListX.add(i);
						ListY.add(j);
						ListD.add("R");
						System.out.println("("+i+ ", " + j+") facing Right");
					}
					if(countR == left && countD == mid && countL == right){
						ListX.add(i);
						ListY.add(j);
						ListD.add("D");
						System.out.println("("+i+ ", " + j+") facing Down");
					}
					if(countD == left && countL == mid && countU == right){
						ListX.add(i);
						ListY.add(j);
						ListD.add("L");
						System.out.println("("+i+ ", " + j+") facing Left");
					}
					if(countL == left && countU == mid && countR == right){
						ListX.add(i);
						ListY.add(j);
						ListD.add("U");
						System.out.println("("+i+ ", " + j+") facing Up");
					}
				}
			}
		}
		int iterateList = 0;
		for (int i = 0; i < height; i++){
			for(int j= 0; j< width; j++){
				if(ListX.size() > iterateList && i == ListX.get(iterateList) && j == ListY.get(iterateList)){
					String printBold = (char)27 +"[1m testing bold";
					//String printBold = "\033[1m0 \033[0m";
					System.out.print(printBold);
					iterateList ++;
				}
				else{
					System.out.print(array[i][j] + " ");
				}
			}
			System.out.println();

		}
		
		//Code for how to compute a single room, as of 2/3/14
//		int newW = width - 2;
//		int newH = height - 2;
//		System.out.println("New diameter is " + newW + "x" + newH);
//		System.out.println("The quadcopter reads: ("+left+","+mid+","+right+")");
//		//First step, add left, right, and 1 to get length of room horizontal to quad
//		int lengthH = left + right + 1; //4
//		int x1 = 1 + left;
//		int y1 = 1 + mid;
//		int x2 = lengthH - left;
//		int y2 = newH - mid;
//		if(lengthH == newW){
//			System.out.println("Facing N or S");
//			System.out.println("("+x1+", "+y1+") "+"("+x2+", "+y2+")");
//		}	
//		else{
//			System.out.println("Facing E or W");
//			System.out.println("("+y1+", "+x2+") "+"("+x1+", "+y2+")");
//		}
	}
	
	public static int[][] seeBMPImage(String BMPFileName) throws IOException {
		System.out.println(firstBPM.class.getResource(BMPFileName).toString());
		//BufferedReader br = new BufferedReader(new FileReader(firstBPM.class.getResource(BMPFileName).toString()));
		BufferedReader br = new BufferedReader(new FileReader("/Users/andrewpl/Documents/workspace/Capstone/src/resources/complexRoom.ppm"));
		String currentline;		
		currentline = br.readLine();
		currentline = br.readLine();
		currentline = br.readLine();
		StringTokenizer st = new StringTokenizer(currentline);
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		
		
		int[][] array2D = new int[height][width];

	    for (int xPixel = 0; xPixel < height; xPixel++)
	        {
	    		currentline = br.readLine();
	    		st = new StringTokenizer(currentline);
	            for (int yPixel = 0; yPixel < width; yPixel++)
	            {
	                int color = Integer.parseInt(st.nextToken());
	                //System.out.print(color + " ");
	                array2D[xPixel][yPixel] = color; 
	            }
	            //System.out.println();
	        }
	    return array2D;
	    }
	
	
}
