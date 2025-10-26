package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 입력값_빈_문자열_예외() {
        assertThatThrownBy(() -> Validator.validateCarName(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값이 없습니다");
    }

    @Test
    void 자동차_이름_빈_문자열_예외() {
        assertThatThrownBy(() -> Validator.validateCarName("pobi,,woni"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1자 이상");
    }


    @Test
    void 자동차_이름_6자_이상_예외() {
        assertThatThrownBy(() -> Validator.validateCarName("pobi,toolong"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 이하");
    }

    @Test
    void 자동차_이름_앞뒤_공백_예외() {
        assertThatThrownBy(() -> Validator.validateCarName("pobi, woni"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("알파벳, 숫자, 한글");
    }

    @Test
    void 자동차_이름_특수문자_예외() {
        assertThatThrownBy(() -> Validator.validateCarName("pobi,wo@ni"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("알파벳, 숫자, 한글");
    }

    // validateCount 최상위 검증
    @Test
    void 시도_횟수_빈_문자열_예외() {
        assertThatThrownBy(() -> Validator.validateCount(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값이 없습니다");
    }

    @Test
    void 시도_횟수_문자_예외() {
        assertThatThrownBy(() -> Validator.validateCount("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자");
    }

    @Test
    void 시도_횟수_0_예외() {
        assertThatThrownBy(() -> Validator.validateCount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1회 이상");
    }

    @Test
    void 시도_횟수_음수_예외() {
        assertThatThrownBy(() -> Validator.validateCount("-5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자");
    }

    // validateCount 정수 범위 검증
    @Test
    void 시도_횟수_정수_범위_초과_예외() {
        assertThatThrownBy(() -> Validator.validateCount("2147483648"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정수 범위");
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
