import java.util.ArrayList;
import java.util.List;

/**
 * Created by 远志 on 12/22/2016.
 */
public class Solution {
    List<Integer> path=new ArrayList<>();
    int length;

    public Solution(List<Integer> path, int length) {
        this.path=path;
        this.length=length;
    }
    public Solution(int length) {
        this(new ArrayList<Integer>(),length);
    }
}
