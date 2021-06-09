package game_test.game.high_score;

import java.io.*;
import java.util.List;

public class Read_score {
        private String fileName;
        private int size=0;
        private User allUser[] = new User[7];
        private File file;
        public Read_score(String fileName){
            this.fileName = fileName;
        }

        public User[] userDetails() throws IOException, ClassNotFoundException, FileIsEmpty {
            file = new File(fileName);

            if(!(file.isFile())){         //there is no history
                return allUser;
            }
            else if (file.length() == 0){
                throw new FileIsEmpty(fileName);
            }
            else {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
                List<User> userList = (List<User>) in.readObject();
                size = userList.size();
                in.close();

                for (int i = 0; i < userList.size(); i++)
                    allUser[i] = userList.get(i);

                return allUser;
            }
        }

        public int getSize(){
            return  size;
        }

}
