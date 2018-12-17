package reactionserver;

import io.javalin.Javalin;

public class Server {
	
	public static void main(String[] args) {

		Javalin app = Javalin.create().start(7000);

		app.get("/", ctx -> ctx.result("Welcome to Reactiongame backend"));

		//Add player score to json
		app.post("/scoreboard/setscore", ctx -> {
			JSONUtil.writeResultsToResults(ctx.formParam("name"), ctx.formParam("score"));
			System.out.println("Set scoreboard result: " + ctx.formParam("score"));
			ctx.status(201);
		});

		//Return jsonArray with all players
		app.get("/scoreboard/getscoreboard/", ctx -> {
			ctx.json(JSONUtil.readJsonFile());
			System.out.println(JSONUtil.readJsonFile());
			ctx.status(200);
		});

	}

}
