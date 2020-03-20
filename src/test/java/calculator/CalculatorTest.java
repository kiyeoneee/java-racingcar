package calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("입력 값이 null 이거나 빈 공백 문자일 경우 IllegalArgumentException throw 테스트")
    public void emptyInputThrowExceptionTest(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            calculator.splitInput(input);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"a + 3"})
    @DisplayName("입력 값이 숫자가 아닌 문자가 있는 경우 IllegalArgumentException throw 테스트")
    public void IllegalValueInputThrowExceptionTest(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            calculator.splitInput(input);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 % 2", "1 | 2"})
    @DisplayName("입력 값이 사칙연산 기호가 아닌 경우 IllegalArgumentException throw 테스트")
    public void illegalOperatorInputThrowExceptionTest(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            calculator.splitInput(input);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"1 + 2=3", "1 + 2 + 3=6"}, delimiter = '=')
    @DisplayName("덧셈 테스트")
    public void additionTest(String input, int expected) {
        int result = calculator.calculate(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1 - 2=-1", "5 - 3 - 1=1", "3 - 6=-3"}, delimiter = '=')
    @DisplayName("뺄셈 테스트")
    public void subtractionTest(String input, int expected) {
        int result = calculator.calculate(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1 * 3=3", "1 * 3 * 2=6", "2 * 5 * 3=30"}, delimiter = '=')
    @DisplayName("곱셈 테스트")
    public void multiplicationTest(String input, int expected) {
        int result = calculator.calculate(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1 / 3=0", "3 / 1=3", "5 / 5=1"}, delimiter = '=')
    @DisplayName("나눗셈 테스트")
    public void divisionTest(String input, int expected) {
        int result = calculator.calculate(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1 + 2 * 3=9", "4 / 2 + 1 * 0=0"}, delimiter = '=')
    @DisplayName("일반적인 연산 우선순위를 따르는 게 아닌 순차적 연산 여부 테스트")
    public void CalculateTest(String input, int expected) {
        int result = calculator.calculate(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 / 0 + 3"})
    @DisplayName("연산이 불가능 한 경우 exception throw 여부 테스트")
    public void calculatorThrowExceptionTest(String input) {
        assertThatExceptionOfType(CalculateException.class).isThrownBy(() -> {
            calculator.splitInput(input);
        });
    }
}
