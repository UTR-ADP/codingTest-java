package level_1;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
	
/**
 * @codingTest 크레인 인형뽑기 게임
 * 1. 바구니 역할을 해줄 stack를 준비하고, 0을 넣는다.
 * 2. moves의 길이 만큼 for문을 돌린다.
 * 3. answer을 리턴한다.
 *
 */
public class CranePuppetGame {
	
	public int craneGame(int[][] board, int[] moves) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        
        for (int move : moves) {
        	for (int j = 0; j < board.length; j++) {
				if (board[j][move - 1] != 0) {
					if (stack.peek() == board[j][move - 1]) {
						stack.pop();
						answer += 2;
					} else {
						stack.push(board[j][move - 1]);
					}
					board[j][move - 1] = 0;
					break;
				}
			}
        }
        return answer;
    }
	
	
	
	public int craneGame1(int[][] board, int[] moves) {
		int answer = 0;
		
		Stack<Integer> stack = new Stack<>();
		stack.push(0); //stack의 맨 위 값과 비교 (아무것도 없으면 오류)
		
		for (int move : moves) {
			for (int j = 0; j < board.length; j++) {
				if (board[j][move - 1] != 0) { //Stack(바구니)의 가장 윗 요소의 board[j][move-1]와 같은지 비교 //board에 물건이 있다면
					if (stack.peek() == board[j][move - 1]) { //같다면 인형이 터지기 때문에 pop -> answer += 2
						stack.pop();
						answer += 2;
					} else {
						stack.push(board[j][move - 1]); //다르다면 Stack에 board[j][move - 1]을 push //다르다면 바구니에 넣기  
					}
					board[j][move - 1] = 0;
					break;
				}
			}
		}
		
		return answer;
	}


	
	public int craneGame2(int[][] board, int[] moves) {
		int answer = 0;
		
		List<Integer> nums = new ArrayList<Integer>();
		
		for (int i = 0; i < moves.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[j][moves[i]-1] != 0) {
					nums.add(board[j][moves[i]-1]);
					board[j][moves[i]-1] = 0;
					break;
				}
			}
		}
		
		for (int k = 0; k < nums.size(); k++) {
			if (k != nums.size()-1) {
				if (nums.get(k) == nums.get(k+1)) {
					nums.remove(k);
					nums.remove(k);
					answer++;
					k = -1;
				}
			}
		}
		
		return answer*2;
	}
	
	
	static Stack<Integer> stack = new Stack<>();
	static int answer3 = 0;
	
	public int craneGame3(int[][] board, int[] moves) {
		
		for (int idx : moves) {
			board = pick(board, idx - 1);
		}
		
		
		return answer3;
	}
	
	//크레인 3번 사용
	static int[][] pick(int[][] board, int idx) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][idx] == 0) continue;
			
			if (!stack.isEmpty() && stack.peek() == board[i][idx]) {
				answer3 += 2;
				stack.pop();
				board[i][idx] = 0;
				break;
			}
			stack.push(board[i][idx]);
			board[i][idx] = 0;
			break;
		}
		
		return board;
	}
	
	
	public static void main(String[] args) {
		CranePuppetGame cpg = new CranePuppetGame();
		int[][] boardInput = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] movesInput = {1,5,3,5,1,2,1,4};
		
		
		//동시에 실행하면 4,0,0,... 이 나오므로 하나씩 실행해서 테스트
		//answer = 4
		System.out.println(cpg.craneGame(boardInput, movesInput));
		System.out.println(cpg.craneGame1(boardInput, movesInput));
		System.out.println(cpg.craneGame2(boardInput, movesInput)); //craneGame1과 같이 출력시도하면 0으로 출력
		System.out.println(cpg.craneGame3(boardInput, movesInput));
		
	}

}
