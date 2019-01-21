package Model;

public class Participant {
    public static final int NOT_ATTEMPTED = -1;
    private int id;
    private String name;
    private String email;
    private String password;
    private int webberStatus;
    private int cocStatus;
    private int tvTriviaStatus;
    private int mockPlacement;

    public Participant(int id, String name, String email, String password, int webberStatus, int cocStatus, int tvTriviaStatus, int mockPlacement) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.webberStatus = webberStatus;
        this.cocStatus = cocStatus;
        this.tvTriviaStatus = tvTriviaStatus;
        this.mockPlacement = mockPlacement;
    }

    public Participant(String name, String email, String password, int webberStatus, int cocStatus, int tvTriviaStatus, int mockPlacement) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.webberStatus = webberStatus;
        this.cocStatus = cocStatus;
        this.tvTriviaStatus = tvTriviaStatus;
        this.mockPlacement = mockPlacement;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getWebberStatus() {
        return webberStatus;
    }

    public void setWebberStatus(int webberStatus) {
        this.webberStatus = webberStatus;
    }

    public int getCocStatus() {
        return cocStatus;
    }

    public void setCocStatus(int cocStatus) {
        this.cocStatus = cocStatus;
    }

    public int getTvTriviaStatus() {
        return tvTriviaStatus;
    }

    public void setTvTriviaStatus(int tvTriviaStatus) {
        this.tvTriviaStatus = tvTriviaStatus;
    }

    public int getMockPlacement() {
        return mockPlacement;
    }

    public void setMockPlacement(int mockPlacement) {
        this.mockPlacement = mockPlacement;
    }
}
