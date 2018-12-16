package reactionserver;

import java.util.Map;
import java.util.HashMap;

public class Scoreboard 
{
    private static Map<String, Double> m_scoreboard;

    public Scoreboard()
    {
        m_scoreboard = new HashMap<String , Double>();
    }

    public void setScore(String name, Double score)
    {
        m_scoreboard.put(name, score);
    }

    public Double getScore(String name)
    {
        return m_scoreboard.get(name);
    }

    public Map<String, Double> getScoreboard()
    {
        return m_scoreboard;
    }
}