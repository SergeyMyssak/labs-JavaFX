package start;

import java.util.HashMap;

public class Repository {
    private static HashMap<Integer, String> hashMap = new HashMap<>();

    public void setHashMap(int num, String answer) {
        hashMap.put(num, answer);
    }
    public HashMap<Integer, String> getHashMap() {
        return hashMap;
    }
    public void removeHashMap(int num) {
        hashMap.remove(num);
    }
}
