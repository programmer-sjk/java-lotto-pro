import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @DisplayName("1,2를 comma(,)로 split 하면 1과 2를 포함한 배열이 반환된다")
    @Test
    void split_분리_테스트() {
        assertThat("1,2".split(",")).containsExactly("1", "2");
    }

    @DisplayName("1을 comma(,)로 split 하면 1만을 포함하는 배열이 반환된다")
    @Test
    void split_단일_분리_테스트() {
        assertThat("1".split(",")).containsExactly("1");
    }

    @DisplayName("(1,2) 값을 substring 메소드를 이용해 1,2를 반환할 수 있다")
    @Test
    void substring_테스트() {
        String input = "(1,2)";
        assertThat(input.substring(1, input.length() - 1)).isEqualTo("1,2");
    }
}
