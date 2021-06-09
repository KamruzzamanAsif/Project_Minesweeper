package game_test.game.high_score;

import game_test.game.SimpleButton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Write_object {
    private String fileName;
    private int size;
    public Write_object(String fileName){
        this.fileName = fileName;
    }

    public void writeDetails(String name, int time, String timeStr) {
        try {
            Read_score score = new Read_score(fileName);
            User allUser[] = score.userDetails();
            size = score.getSize();

            allUser[size] = new User(name, time,timeStr);        //new user in game

            sort(allUser);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            List<User> userList = new ArrayList<>();

            for (int i = 0; i <= size && i < 5; i++)
                userList.add(allUser[i]);


            out.writeObject(userList);
            out.close();

            for (int i = 0; i <= size; i++) {
                System.out.println(allUser[i].getName() + "    " + allUser[i].getTime());
            }
        }catch (IOException e){
            System.out.println(e);
        }catch (ClassNotFoundException e){
            System.out.println(e);
        } catch (FileIsEmpty fileIsEmpty) {
            fileIsEmpty.printStackTrace();
        }
    }

    public void sort(User allUser[]){

        for(int i=0;i<=size;i++){
            for(int j=0;j<=size;j++){
                if (allUser[i].getTime() < allUser[j].getTime()) {
                    allUser[6] = allUser[i];
                    allUser[i] = allUser[j];
                    allUser[j] = allUser[6];
                }
            }
        }
    }

}
