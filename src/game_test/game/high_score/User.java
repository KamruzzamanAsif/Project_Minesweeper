package game_test.game.high_score;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int time;
    private String timeStr;

    public User(String name,int time,String timeStr){
        this.name = name;
        this.time = time;
        this.timeStr = timeStr;
    }

    public String getName(){
        return name;
    }
    public int getTime(){
        return time;
    }
    public String getTimeStr(){
        return timeStr;
    }

}
