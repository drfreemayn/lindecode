package reactionserver;

import java.util.HashMap;

import io.javalin.Javalin;

public class Server {

	public static void main(String[] args) {
		Scoreboard m_scoreboard = new Scoreboard();

		Javalin app = Javalin.create().start(7000);

		app.get("/", ctx -> ctx.result("Welcome to Reactiongame backend"));

		app.post("/scoreboard/setscore", ctx -> {
			// TODO Lägga till backend / Fil i server för highscore?
			m_scoreboard.setScore(ctx.formParam("name"), ctx.formParam("score"));
			System.out.println("Set scoreboard result: " + ctx.formParam("score"));
			ctx.status(201);
		});

		app.get("/scoreboard/getplayerscore/:name", ctx -> {
			System.out.println("Get scoreboard result for player: " + ctx.pathParam("name"));
			ctx.result("User: " + ctx.pathParam("name") + " Result: " + m_scoreboard.getScore(ctx.pathParam("name")));
		});

		app.get("/scoreboard/getscoreboard/", ctx -> {
			ctx.result("Pass all player data");
			m_scoreboard.getScoreboard().forEach((name, score) -> {
				System.out.println("Result: " + name + " - " + score);
			});

		});

		TestPoster.testPost2();

		// TESTAR LÄGGA IN EXTRA DATA
		m_scoreboard.getScoreboard().put("Sniddan", "123");
		m_scoreboard.getScoreboard().put("Olle", "564");
	}

}
