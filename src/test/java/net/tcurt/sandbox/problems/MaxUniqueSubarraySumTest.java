package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MaxUniqueSubarraySumTest {

  private MaxUniqueSubarraySum underTest = new MaxUniqueSubarraySum();

  @Test
  void input_1_2_3_4_5() {
    int[] input = new int[] {1, 2, 3, 4, 5};
    assertThat(underTest.maxSum(input)).isEqualTo(15);
  }

  @Test
  void input_1_1_0_1_1() {
    int[] input = new int[] {1, 1, 0, 1, 1};
    assertThat(underTest.maxSum(input)).isEqualTo(1);
  }

  @Test
  void input_1_2_min1_min2_1_0_min1() {
    int[] input = new int[] {1, 2, -1, -2, 1, 0, -1};
    assertThat(underTest.maxSum(input)).isEqualTo(3);
  }

  @Test
  void input_1_2_3_min2_100_0_min1() {
    int[] input = new int[] {1, 2, 3, -2, 100, 0, -1};
    assertThat(underTest.maxSum(input)).isEqualTo(106);
  }
}
