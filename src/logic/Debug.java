package logic;

import java.util.Scanner;

public abstract class Debug {
	public static String ln = "line";
	public static String prompt = "enter";
	public static String noln = "noline";
	public static boolean debugOn = true;
	private static Scanner voidObject;
	
	public static void toggleDebug(){
		debugOn = !debugOn;
	}
	
	public static boolean isDebugOn(){
		return debugOn;
	}
	
	public static void $(){
		if(debugOn) System.out.println();
		}
	
	public static void $(String text){
		if(debugOn) System.out.println(text);
	}
	
	public static void $(
			String part1,
			String part2){
		
		if(debugOn) switch(part1){
		case "line":
			System.out.println();
			System.out.println(part2);
			break;
			
		default:
			switch(part2){
				case "line":
					System.out.println(part1);
					System.out.println();
					break;
					
				case "enter":
					System.out.println(part1);
					voidObject.nextLine();
					break;
					
				case "noline":
					System.out.print(part1);
			}
		}	
	}	
}
