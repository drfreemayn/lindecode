package reactionserver;

import io.javalin.Javalin;
import java.net.*;
import java.io.*;
import java.lang.Object.*;


public class App 
{
    static Scoreboard m_scoreboard;

    void setScore()
    {

    }

    public static void main( String[] args )
    {
        m_scoreboard = new Scoreboard();

        Javalin app = Javalin.create().start(7000);

        app.post("/scoreboard/set_score/:name", ctx -> { System.out.println(ctx.pathParam("name"));});
        m_scoreboard.setScore("apa", 120.0);
        app.get("/scoreboard/get_score/:name", ctx -> { ctx.result(m_scoreboard.getScore(ctx.pathParam("name")).toString()); } );
}
