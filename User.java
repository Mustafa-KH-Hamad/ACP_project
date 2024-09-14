import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private int userID;
    private String email;
    private String password;

    private static Map<Integer, String> userIDs = new HashMap<>();
    private static Map<String, String> userCredentials = new HashMap<>();
    
    public User() {
    	
    }
        public User(int userID, String email, String password) {
              this.userID = userID;
             this.email = email;
             this.password = password; 

         if (!isIDEmailDuplicate(userID, email)) {
             this.password = password;
             userIDs.put(userID, email); 
             userCredentials.put(email, password); 
             System.out.println("User added successfully.");
         } 
         else {
             System.out.println("ID or email combination is already in use. Please choose different credentials.");
         }
         }

         private static boolean isIDEmailDuplicate(int userID, String email) {
             return userIDs.containsKey(userID) || userCredentials.containsKey(email);
         }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean Checklogin(int inputID , String inputEmail) {
        return userID == inputID && email.equals(inputEmail);
    }
    
    public void readFromJsonFile(String fileName) {
        StringBuilder jsonText = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonText.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = jsonText.toString();
        this.userID = Integer.parseInt(extractValue(jsonString, "userID"));
        this.email = extractValue(jsonString, "email");
        this.password = extractValue(jsonString, "password");
    }

    private String extractValue(String json, String key) {
        String keySearch = "\"" + key + "\":";
        int startIndex = json.indexOf(keySearch);
        if (startIndex != -1) {
            startIndex += keySearch.length();
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = json.indexOf("}", startIndex);
            }
            return json.substring(startIndex, endIndex).replaceAll("\"", "").trim();
        }
        return null;
    }

    public static void writeToJsonFile(String fileName, List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("[\n");
            for (int i = 0; i < users.size(); i++) {
                writer.write("    {\n");
                writer.write("        \"userID\": " + users.get(i).userID + ",\n");
                writer.write("        \"email\": \"" + users.get(i).email + "\",\n");
                writer.write("        \"password\": \"" + users.get(i).password + "\"\n");
                writer.write("    }");
                if (i < users.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            System.out.println("User data has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



   
}
