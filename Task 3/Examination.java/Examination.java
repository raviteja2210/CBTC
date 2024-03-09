import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    private String username;
    private String password;
    private String profile;

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getProfile() {
        return profile;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateProfile(String newProfile) {
        this.profile = newProfile;
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class OnlineExamination {
    private static Map<String, User> users = new HashMap<>();
    private static Map<Integer, Question> questions = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Timer timer;

    public static void main(String[] args) {
        // Initialize users and questions
        initializeUsers();
        initializeQuestions();

        // Login
        User currentUser = login();

        if (currentUser != null) {
            System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
            // Update Profile and Password
            updateProfileAndPassword(currentUser);

            // Selecting answers for MCQs
            selectAnswers();

            // Start Timer and auto submit
            startTimer();

            // Closing session and Logout
            logout();
        } else {
            System.out.println("Invalid username or password. Exiting.");
        }
    }

    private static void initializeUsers() {
        users.put("user1", new User("user1", "password1", "Student"));
        users.put("user2", new User("user2", "password2", "Student"));
        users.put("admin", new User("admin", "adminpassword", "Admin"));
    }

    private static void initializeQuestions() {
        questions.put(1, new Question("What is the capital of France?", 
            new String[]{"A. London", "B. Paris", "C. Rome", "D. Berlin"}, 2));
        questions.put(2, new Question("What is the largest planet in our solar system?", 
            new String[]{"A. Earth", "B. Jupiter", "C. Mars", "D. Saturn"}, 1));
    }

    private static User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).checkPassword(password)) {
            return users.get(username);
        } else {
            return null;
        }
    }

    private static void updateProfileAndPassword(User user) {
        if (user.getProfile().equals("Admin")) {
            System.out.println("You are an admin. You cannot update profile or password.");
            return;
        }
        System.out.print("Do you want to update your profile? (Y/N): ");
        String choice = scanner.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            System.out.print("Enter new profile: ");
            String newProfile = scanner.nextLine();
            user.updateProfile(newProfile);
            System.out.println("Profile updated successfully.");
        }

        System.out.print("Do you want to update your password? (Y/N): ");
        choice = scanner.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            System.out.print("Enter current password: ");
            String currentPassword = scanner.nextLine();
            if (user.checkPassword(currentPassword)) {
                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();
                user.updatePassword(newPassword);
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Incorrect password. Password not updated.");
            }
        }
    }

    private static void selectAnswers() {
        System.out.println("Select your answers for the following questions:");
        for (int i = 1; i <= questions.size(); i++) {
            System.out.println("Question " + i + ": " + questions.get(i).getQuestion());
            for (String option : questions.get(i).getOptions()) {
                System.out.println(option);
            }
            System.out.print("Enter your answer (A/B/C/D): ");
            String answer = scanner.nextLine().toUpperCase();
            // Do something with the answer (e.g., store it)
        }
    }

    private static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Auto-submitting the test...");
                submitTest();
            }
        }, 30 * 60 * 1000); // 30 minutes timer
        System.out.println("Timer started. You have 30 minutes to complete the exam.");
    }

    private static void submitTest() {
        // Implement submission of the test
        System.out.println("Test submitted successfully.");
        timer.cancel(); // Cancel the timer after submission
    }

    private static void logout() {
        System.out.println("Logging out...");
        // Implement logout logic here
    }
}
