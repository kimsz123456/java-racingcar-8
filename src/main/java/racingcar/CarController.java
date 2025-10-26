package racingcar;

import java.util.ArrayList;
import java.util.List;

public class CarController {
    private final InputView inputView;
    private final OutputView outputView;

    public CarController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Car> carList = createCars();
        int count = getRaceCount();

        runRace(carList, count);
        announceWinner(carList);
    }

    private List<Car> createCars() {
        String[] carNames = readCarNames();
        List<Car> carList = new ArrayList<>();
        for (String name : carNames) {
            carList.add(new Car(name));
        }
        return carList;
    }

    private String[] readCarNames() {
        outputView.printCarNamePrompt();
        String input = inputView.readCarName();
        Validator.validateCarName(input);
        return input.split(",");
    }

    private int getRaceCount() {
        outputView.printCountPrompt();
        String input = inputView.readCount();
        Validator.validateCount(input);
        return Integer.parseInt(input);
    }

    private void runRace(List<Car> carList, int count) {
        outputView.printRaceResultPrompt();
        while (count > 0) {
            moveCars(carList);
            outputView.printRoundResult(carList);
            count--;
        }
    }

    private void moveCars(List<Car> carList) {
        for (Car car : carList) {
            car.tryMove();
        }
    }

    private void announceWinner(List<Car> carList) {
        List<String> winners = findWinners(carList);
        outputView.printWinnerAnouncementPrompt(winners);
    }

    private List<String> findWinners(List<Car> carList) {
        int maxPosition = findMaxPosition(carList);
        return getWinnerNames(carList, maxPosition);
    }

    private int findMaxPosition(List<Car> carList) {
        int maxPosition = 0;
        for (Car car : carList) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }
        return maxPosition;
    }

    private List<String> getWinnerNames(List<Car> carList, int maxPosition) {
        List<String> winners = new ArrayList<>();
        for (Car car : carList) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }
        return winners;
    }
}