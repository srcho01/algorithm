# [Gold I] Brainf**k 인터프리터 - 3954 

[문제 링크](https://www.acmicpc.net/problem/3954) 

### 성능 요약

메모리: 118016 KB, 시간: 19396 ms

### 분류

자료 구조, 구현, 시뮬레이션, 스택

### 제출 일자

2025년 7월 29일 01:40:24

### 문제 설명

<p>Brainf**k 프로그램이 주어졌을 때, 이 프로그램이 끝나는지, 무한 루프에 빠지는지 알아내는 프로그램을 작성하시오.</p>

<p>무한 루프란, 특정 시점부터 탈출하지 않고 무한히 반복 실행되는 루프를 말한다.</p>

<p>Brainf**k 인터프리터는 정수를 담는 하나의 배열(unsigned 8-bit 정수)과, 그 배열의 칸 하나를 가리키는 포인터로 이루어져 있다. Brainf**k 프로그램은 다음과 같이 8개의 명령어로 이루어져 있다.</p>

<table class="table table-bordered" style="width:100%">
	<tbody>
		<tr>
			<th style="width:20%"><code>-</code></th>
			<td style="width:80%">포인터가 가리키는 수를 1 감소시킨다. (modulo 2<sup>8</sup>)</td>
		</tr>
		<tr>
			<th><code>+</code></th>
			<td>포인터가 가리키는 수를 1 증가시킨다. (modulo 2<sup>8</sup>)</td>
		</tr>
		<tr>
			<th><code><</code></th>
			<td>포인터를 왼쪽으로 한 칸 움직인다.</td>
		</tr>
		<tr>
			<th><code>></code></th>
			<td>포인터를 오른쪽으로 한 칸 움직인다.</td>
		</tr>
		<tr>
			<th><code>[</code></th>
			<td>만약 포인터가 가리키는 수가 0이라면, <code data-stringify-type="code">[</code> 와 짝을 이루는 <code data-stringify-type="code">]</code> 의 다음 명령으로 점프한다.</td>
		</tr>
		<tr>
			<th><code>]</code></th>
			<td>만약 포인터가 가리키는 수가 0이 아니라면, <code data-stringify-type="code">]</code> 와 짝을 이루는 <code data-stringify-type="code">[</code> 의 다음 명령으로 점프한다.</td>
		</tr>
		<tr>
			<th>.</th>
			<td>포인터가 가리키는 수를 출력한다.</td>
		</tr>
		<tr>
			<th>,</th>
			<td>문자 하나를 읽고 포인터가 가리키는 곳에 저장한다. 입력의 마지막(EOF)인 경우에는 255를 저장한다.</td>
		</tr>
	</tbody>
</table>

<p>인터프리터는 Brainf**k 프로그램의 첫 번째 명령부터 수행하고, 더이상 수행할 명령이 없다면, 프로그램을 종료한다. 각 명령을 수행하고 나면, 다음 명령을 수행한다. 물론 <code>[</code>이나 <code>]</code>인 경우에는 다음 명령으로 이동하는 것이 아니라 점프를 한다.</p>

<p>데이터 배열의 크기는 문제에서 주어지는 값을 사용해야 한다. 또, 데이터 배열의 값이 underflow나 overflow를 일으켰을 때는 일반적인 방법을 따르면 된다. 프로그램을 수행하기 전에, 데이터 배열의 값은 0으로 초기화되어 있고, 포인터가 가리키는 칸은 0번 칸이다.</p>

<p>포인터를 왼쪽이나 오른쪽으로 움직일 때, 데이터 배열의 범위를 넘어간다면, 반대쪽으로 넘어가게 된다. 즉, 포인터가 0을 가리킬 때, 1을 감소시킨다면, 배열의 크기 - 1번째를 가리키게 된다.</p>

<p><code>[</code>와 <code>]</code>는 루프를 의미하며, 중첩될 수 있다. 입력으로 주어진 프로그램은 잘 짜여 있음이 보장된다. 즉 프로그램을 왼쪽에서 오른쪽으로 훑으면서 <code>[</code>의 개수에서 <code>]</code>의 개수를 빼면 항상 0보다 크거나 같고, 맨 끝까지 훑으면 그 값은 0이 된다.</p>

<p>이 문제는 Brainf**k 프로그램이 무한 루프에 빠지는지 안 빠지는지를 검사하기만 하면 된다. 따라서, 출력은 무시한다.</p>

### 입력 

 <p>첫째 줄에 테스트 케이스의 개수 t (0 < t ≤ 20)가 주어진다. 각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 s<sub>m</sub>, s<sub>c</sub>, s<sub>i</sub>가 주어진다. s<sub>m</sub>은 메모리(배열)의 크기이고, s<sub>c</sub>는 프로그램 코드의 크기, s<sub>i</sub>는 입력의 크기이다. (0 < s<sub>m</sub> ≤ 100,000, 0 < s<sub>c</sub>, s<sub>i</sub> < 4096)</p>

<p>둘째 줄에는 Brainf**k 프로그램이 주어진다. 프로그램은 s<sub>c</sub>개의 문자로 이루어져 있다.</p>

<p>셋째 줄에는 프로그램의 입력이 주어진다. (공백이 아니면서 출력할 수 있는 문자만 주어진다)</p>

### 출력 

 <p>각 테스트 케이스에 대해서, 프로그램이 종료된다면 "Terminates"를, 무한 루프에 빠지게 된다면 "Loops"를 출력한다. 무한 루프에 빠졌을 때는, 프로그램의 어느 부분이 무한 루프인지를 출력한다. (<code>[</code>와 <code>]</code>의 위치) 프로그램이 명령어를 50,000,000번 이상 수행한 경우, 프로그램은 항상 종료되었거나 무한 루프에 빠져있다. 무한 루프일 경우, 해당 루프는 적어도 한 번 실행이 완료된 상태이며, 한 번의 무한 루프에서 실행되는 명령어의 개수는 50,000,000개 이하이다.</p>

