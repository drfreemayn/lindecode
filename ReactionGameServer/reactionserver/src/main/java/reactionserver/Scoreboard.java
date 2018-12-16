package reactionserver;

import java.util.Map;
import java.util.HashMap;

public class Scoreboard 
{
    private static HashMap<String, String> m_scoreboard;

    public Scoreboard()
    {
        m_scoreboard = new HashMap<>();
    }

    public void setScore(String name, String string)
    {
        m_scoreboard.put(name, string);
    }

    public String getScore(String name)
    {
        return m_scoreboard.get(name);
    }

    public HashMap<String, String> getScoreboard()
    {
        return m_scoreboard;
    }
}