//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    var iterations = 100_000;

    var results = new ArrayList<GameResults>(iterations);

    for (int i = 1; i <= iterations; i++) {
        var game = new OrchardGame();
        var result = game.play();
        results.add(result);
    }

    var teamWins = results.stream().filter(GameResults::teamWin).count() / Double.valueOf(iterations);
    var averageTurns = results.stream().mapToInt(GameResults::totalTurns).average();

    IO.println("Team win: %s - average turns: %s".formatted(teamWins, averageTurns));
}
