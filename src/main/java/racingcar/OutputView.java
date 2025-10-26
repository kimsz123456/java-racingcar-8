package racingcar;

import java.util.List;

public class OutputView {
    private static final String CAR_NAME_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String COUNT_PROMPT = "시도할 횟수는 몇 회인가요?";
    private static final String RACE_RESULT = "실행 결과";
    private static final String WINNER_ANNOUNCEMENT = "최종 우승자 : ";

    public void printCarNamePrompt() {
        System.out.println(CAR_NAME_PROMPT);
    }

    public void printCountPrompt() {
        System.out.println(COUNT_PROMPT);
    }

    public void printRaceResultPrompt() {
        System.out.println(RACE_RESULT);
    }

    public void printRoundResult(List<Car> carList) {
        for (Car car : carList) {
            System.out.printf("%s : %s%n", car.getName(), "-".repeat(car.getPosition()));
        }
        System.out.println();
    }

    public void printWinnerAnnouncementPrompt(List<String> winners) {
        String winnerNames = String.join(", ", winners);
        System.out.println(WINNER_ANNOUNCEMENT + winnerNames);
    }
}
