package hlc.shudu.src;

/*
 * 数独的帮助类，里面提供数据所需的所有算法
 */
public class ShuduHelper {
	private static int[][] maps = new int[9][9];

	private static int[] canPutSum = new int[9];
	static int[] used = new int[9];
	static boolean isOk = true;

	/*
	 * 得到数独地图数组
	 */
	public static int[][] getMap() {

		do{
			isOk = true;
			initMaps();
		}while(!isOk);
		return maps;
	}

	/*
	 * 初始化maps
	 */
	private static void initMaps() {
		// 初始化地图数组中没有填入任何数字
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				maps[i][j] = -1;
			}
		}

		// 依次填入1~9
		for (int num = 1; num <= 9; num++) {
			for (int i = 0; i < 9; i++) {
				used[i] = -1;
				canPutSum[i] = -1;
			}
			// 遍历大九宫格中的每个小九宫格
			for (int i = 0; i < 9; i++) {
				if (canPutSum[i]==-1) {
					canPutSum[i] = getCanPutSum(i, num);
				}
				if (canPutSum[i]==1) {
					used[i] = -1;
				}

				if (canPutSum[i] == 0) {
					canPutSum[i] = -1;
					used[i] = -1;
					// 如果当前小九宫格中不能放入数字num，则回到前一个小九宫格
					if (i > 0) {
						// 将前一个九宫格中放num的位置清空
						if (used[i-1]!=-1) {
							//maps[(int) (Math.floor(used[i-1]/3)+Math.floor((i-1)/3)*3)][used[i-1]%3+((i-1)%3)*3]=-1;
							clearNum(i - 1, num);
						}
						// i回退一个，因为等会for循环灰给i加一，所以这里减2
						i -= 2;
						continue;
					} else {
						isOk = false;
						return;
					}
				} else {
					// 将num放入当前小九宫格中
					boolean flag = false;
					while (!flag) {
						int j = (int) (Math.random() * 9);
						// 当前小方格横坐标
						int ii = (i / 3) * 3 + j / 3;
						// 当前小方格纵坐标
						int jj = (i % 3) * 3 + j % 3;
						//System.out.println("num:"+num+"\tii:"+ii+"\tjj:"+jj);
						// 如果可以放置num则放置
						if (maps[ii][jj] == -1 && j!=used[i] && isCanPut(ii, jj, num)) {
							maps[ii][jj] = num;
							used[i] = j;
							canPutSum[i] -= 1;
							flag = true;
						}

					}
				}

			}
		}

	}

	/*
	 * 清空第i个小九宫格中的num
	 */
	private static void clearNum(int i, int num) {
		for (int j = 0; j < 9; j++) {
			// 当前小方格横坐标
			int ii = (i / 3) * 3 + j / 3;
			// 当前小方格纵坐标
			int jj = (i % 3) * 3 + j % 3;
			// 判断当前小方格是否可以放置
			if (maps[ii][jj] == num) {
				maps[ii][jj] = -1;
			}
		}

	}

	/*
	 * 得到当前小九宫格可以放入数字num的位置数目
	 */
	private static int getCanPutSum(int i, int num) {
		int sum = 0;
		// 遍历小九宫格
		for (int j = 0; j < 9; j++) {
			// 当前小方格横坐标
			int ii = (i / 3) * 3 + j / 3;
			// 当前小方格纵坐标
			int jj = i % 3 * 3 + j % 3;
			// 判断当前小方格是否可以放置
			if (maps[ii][jj] == -1 && isCanPut(ii, jj, num)) {
				++sum;
			}
		}

		return sum;

	}

	/*
	 * 指定横纵坐标点是否可以防止num
	 */
	private static boolean isCanPut(int ii, int jj, int num) {
		// 判断指定坐标点的同行或同列是否有相同数字，要是有则为false
		for (int i = 0; i < 9; i++) {
			if (maps[ii][i] == num) {
				return false;
			}
			if (maps[i][jj] == num) {
				return false;
			}
		}
		return true;
	}
}
