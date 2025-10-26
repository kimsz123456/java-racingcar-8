package racingcar;

public class Validator {
    private static final int MAX_NAME_LENGTH = 5;

    public static void validateCarName(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름 입력값이 없습니다.");
        }

        String[] names = input.split(",");
        for (String name : names) {
            validateName(name);
        }
    }

    private static void validateName(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 1자 이상 5자 이하여야 합니다");
        }

        if (!name.matches("[a-zA-Z0-9가-힣]+")) {
            throw new IllegalArgumentException("자동차 이름은 알파벳, 숫자, 한글만 가능합니다");
        }
    }

    public static void validateCount(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("시도 횟수 입력값이 없습니다.");
        }

        if (!input.trim().matches("\\d+")) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }

        long count = Long.parseLong(input.trim());
        if (count <= 0) {
            throw new IllegalArgumentException("시도 횟수는 양수여야 합니다.");
        }
        if (count > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("시도 횟수가 정수 범위를 초과했습니다.");
        }
    }
}