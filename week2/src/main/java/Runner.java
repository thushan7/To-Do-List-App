import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

	Scanner input = new Scanner(System.in);
	input.useDelimiter(":");

	int num;
	MagicEightBall ball;
	String response;

	System.out.print("Enter a number: ");
	num = Integer.parseInt(input.nextLine());

	ball = new MagicEightBall(num);

	while(ball.getNumResponses() < ball.getMaxSize()) {
		System.out.print("Enter a response: ");
		response = input.nextLine();
		ball.addResponse(response);
	}

	System.out.println(ball.toString());
    }
}
