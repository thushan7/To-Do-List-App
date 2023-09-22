import java.util.ArrayList;
import java.util.Random;

public class MagicEightBall {

    private ArrayList<String> responses = new ArrayList<>();
    private Random rand = new Random();
    private int maxSize;

    public MagicEightBall(){
        this(3);  //eight ball defaults to three responses
    }

    public MagicEightBall(int max){
        maxSize = max;
    }

    public int getMaxSize(){
	return maxSize;
    }

    public int getNumResponses(){
	return responses.size();
    }

    public void addResponse(String response) {
	responses.add(response);
    }

    @Override
    public String toString(){
        int responseIndex = rand.nextInt(responses.size());
        return(responses.get(responseIndex));
    }

}
